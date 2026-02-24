package com.evertecinc.b2c.enex.keycloak.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.EventRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.constants.UserConstants;
import com.evertecinc.b2c.enex.client.constants.PropertiesConstants;
import com.evertecinc.b2c.enex.client.dao.repositories.ClientsUsersRelRepo;
import com.evertecinc.b2c.enex.client.dao.repositories.UsersRepo;
import com.evertecinc.b2c.enex.client.exceptions.VariableException;
import com.evertecinc.b2c.enex.client.model.entities.ClientsUsersRel;
import com.evertecinc.b2c.enex.client.model.entities.Users;
import com.evertecinc.b2c.enex.client.service.IVariableService;
import com.evertecinc.b2c.enex.keycloak.config.KeyCloakPropertyConfig;
import com.evertecinc.b2c.enex.keycloak.exceptions.KeycloakException;
import com.evertecinc.b2c.enex.keycloak.exceptions.KeycloakUserNotFoundException;
import com.evertecinc.b2c.enex.keycloak.utils.ConstantsKeycloak;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Data
public class KeycloakServiceImpl implements IKeycloakService {

    private final Keycloak keycloak;
    private final KeyCloakPropertyConfig keyCloakPropertyConfig;
    private @Autowired UsersRepo usersRepoJPA;
    private @Autowired ClientsUsersRelRepo clientsUsersRelRepoJPA;
    private @Autowired IVariableService variableServiceImpl;

	private static final Logger logger 			= LoggerFactory.getLogger("LoggerProcesoUsuariosKeycloak");
    
    private @Value("${api.keycloak.auth-client-id}") 					String 	clientIdKeycloak;
    private @Value("${api.keycloak.realm}") 					String 	realmKeycloak;
    
    private static final ZoneId ZONE = ZoneId.of("America/Santiago");
    
    private void addOrUpdateKCUser(Users receiveUser, Map<String, List<String>> clientTypeUpisDeUsuarioBD) throws Exception {
    	RoleRepresentation selectedRole = null;
    	try {
    		String rolRecibido = "" + receiveUser.getRole();
    		// Obtener todos los roles del realm
    		List<RoleRepresentation> roles = keycloak.realm(keyCloakPropertyConfig.getRealm()).roles().list();

    		for (RoleRepresentation role : roles) {
    			if (rolRecibido.equalsIgnoreCase(role.getName())) {
    				selectedRole = role;
    				break;
    			}
    		}

    		if (Functions.hasEmptyOrNull(selectedRole)) {
    			throw new Exception("No se encontró el rol " + receiveUser.getRole() + " en Keycloak para crear o actualizar el usuario");
    		}
    	} catch (Exception e) {
    		log.error("Ocurrió un error al obtener los roles: " + e.getMessage());
    		return;
    	}

    	try {
			List<UserRepresentation> users = keycloak.realm(keyCloakPropertyConfig.getRealm())
			.users().search(receiveUser.getUsername(), null, null, null, null, null);

    		if (users.isEmpty()) {

    			List<UserRepresentation> usersUsername = keycloak.realm(keyCloakPropertyConfig.getRealm()).users().search(receiveUser.getUsername(), true);
    			List<UserRepresentation> usersEmail = keycloak.realm(keyCloakPropertyConfig.getRealm())
    					.users().search(null, null, null, receiveUser.getEmail(), null, null);
    			
				if ((usersUsername != null && !usersUsername.isEmpty()) || (usersEmail != null && !usersEmail.isEmpty())) {
					if (usersUsername != null && !usersUsername.isEmpty()) {
						throw new RuntimeException("Ya existe un usuario con username: " + receiveUser.getUsername());
					}

					if (usersEmail != null && !usersEmail.isEmpty()) {
						throw new RuntimeException("Ya existe un usuario con email: " + receiveUser.getEmail());
					}
				}

    			// Crear el usuario si no existe
    			UserRepresentation user = new UserRepresentation();
    			user.setUsername(receiveUser.getUsername());
    			user.setFirstName(receiveUser.getName());
    			user.setLastName(receiveUser.getFirstName());
    			user.setEmail(receiveUser.getEmail());
    			user.setEnabled(true);

    			List<String> requiredActions = new ArrayList<>();
    			requiredActions.add("UPDATE_PASSWORD");
    			user.setRequiredActions(requiredActions);

    			user = this.setAttributesUserKeycloak(receiveUser, clientTypeUpisDeUsuarioBD, user);

    			// Crear el usuario
    			log.info("Llamada para crear usuario en Keycloak");
    			Response response = keycloak.realm(keyCloakPropertyConfig.getRealm()).users().create(user);

    			if (response.getStatus() != 201) {
    				throw new RuntimeException("Error creando usuario: " + response.getStatus() + " - " + response.readEntity(String.class));
    			}

    			// Obtener el ID del usuario creado
    			String userId = CreatedResponseUtil.getCreatedId(response);

    			// *********** PASSWORD ***********
//    			// Establecer la contraseña del usuario
    			CredentialRepresentation credential = new CredentialRepresentation();
    			credential.setType(CredentialRepresentation.PASSWORD);
    			log.info("PASSWORD MOMENTANEA: Enex{}", LocalDate.now().getYear());
    			credential.setValue("Enex" + LocalDate.now().getYear());
    			credential.setTemporary(true);

    			keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(userId).resetPassword(credential);
    			// *********** PASSWORD ***********

    			// Asignar rol al usuario
    			keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(userId).roles().realmLevel().add(Arrays.asList(selectedRole));
    			log.info("Rol " + selectedRole.getName() + " asignado al usuario con ID: " + userId);

    			logger.info("KEYCLOAK INSERT - Usuario " + receiveUser.getUsername() + " agregado correctamente.");

    		} else {
    			// Actualizar el usuario si ya existe
    			UserRepresentation user = users.get(0);
    			UserResource userResource = keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(user.getId());

    			//				List<UserRepresentation> existingUsersByUsername = keycloak.realm(keyCloakPropertyConfig.getRealm())
    			//						.users().search(receiveUser.getUsername(), true);
    			//
    			//				List<UserRepresentation> existingUsersByEmail = keycloak.realm(keyCloakPropertyConfig.getRealm())
    			//						.users().search(null, null, null, receiveUser.getEmail(), null, null);
    			//
    			//    			if (!existingUsersByUsername.get(0).getId().equals(user.getId()))
    			//    				throw new RuntimeException("El nombre de usuario " + receiveUser.getUsername() + " ya está en uso por otro usuario.");
    			//
    			//    			if (!existingUsersByEmail.get(0).getId().equals(user.getId()))
    			//    				throw new RuntimeException("El correo electrónico " + receiveUser.getEmail() + " ya está en uso por otro usuario.");

    			this.gestionarRoleUsuarioKeycloak(user.getId(), selectedRole);

    			// Actualizar solo los campos necesarios
    			user.setFirstName(receiveUser.getName());
    			user.setLastName(receiveUser.getFirstName());
    			user.setEmail(receiveUser.getEmail());
    			user.setAttributes(this.setAttributesUserKeycloak(receiveUser, clientTypeUpisDeUsuarioBD, user).getAttributes());

    			userResource.update(user);

    			logger.info("KEYCLOAK UPDATE - Usuario " + receiveUser.getUsername() + " actualizado correctamente.");
    		}
    	} catch (Exception e) {
    		log.error("Ocurrió un error al agregar o actualizar usuario " + receiveUser.getUsername() + " e ID: "
    				+ receiveUser.getIdUser() + " en Keycloak. ERROR: " + e.getMessage());
    		receiveUser.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_ERROR);
    		receiveUser.setData(LocalDate.now() + " - " + e.getMessage());
			this.usersRepoJPA.save(receiveUser);
    		return;
    	}

    	receiveUser.setStatus("A");
    	receiveUser.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_SENDED);
    	receiveUser.setData(null);
    	this.usersRepoJPA.save(receiveUser);
    }

	private UserRepresentation setAttributesUserKeycloak(Users usuarioBD, Map<String, List<String>> clientTypeUpisDeUsuarioBD, UserRepresentation user) {
		Map<String, List<String>> mapAtributosKeycloak =  !Functions.hasEmptyOrNull(user.getAttributes()) ? user.getAttributes() : new HashMap<>();

		List<String> rutUsuarioList = new ArrayList<>();
		rutUsuarioList.add(usuarioBD.getRut());

		if(!Functions.hasEmptyOrNull(rutUsuarioList))
			mapAtributosKeycloak.put("rutUsuario", rutUsuarioList);

		if (!Functions.hasEmptyOrNull(clientTypeUpisDeUsuarioBD)) {
		clientTypeUpisDeUsuarioBD.forEach((clientType, upisDeClientTypeClienteBD) -> {
			String keyClientTypeKeycloak = "rut" + clientType;
			List<String> valorAtributoUpisKeycloak = mapAtributosKeycloak.get(keyClientTypeKeycloak);

			List<String> updatedUpisList = new ArrayList<>();
			updatedUpisList.addAll(upisDeClientTypeClienteBD);

			List<String> upisActualizadosKeycloak = new ArrayList<>(new LinkedHashSet<>(updatedUpisList));

			mapAtributosKeycloak.put(keyClientTypeKeycloak, Arrays.asList(String.join(",", upisActualizadosKeycloak)));
		});
	}

		// ********** ASIGNACION ATRIBUTO USUARIO BO **********
		try {
			List<RoleRepresentation> userKeycloakRoles = keycloak.realm(keyCloakPropertyConfig.getRealm()).users()
					.get(user.getId()).roles().realmLevel().listAll();

			boolean tieneRolesBO = userKeycloakRoles.stream().filter(role -> {
				try {
					int roleId = Integer.parseInt(role.getName());
					return roleId != 1 && roleId != 2;
				} catch (NumberFormatException e) {
					return false;
				}
			})
				.findAny()
				.isPresent();

			List<String> usuarioBOList = new ArrayList<>();
			usuarioBOList.add(tieneRolesBO ? "true" : "false");
			mapAtributosKeycloak.put("BO", usuarioBOList);

		} catch (Exception e) {
			log.error("ERROR: " + e.getMessage());
		}
		// ********** ASIGNACION ATRIBUTO USUARIO BO END **********

		user.setAttributes(mapAtributosKeycloak);
		
		return user;
	}
    
	private void gestionarRoleUsuarioKeycloak(String userId, RoleRepresentation selectedRole) {
		try {
			List<RoleRepresentation> userKeycloakRoles = keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(userId).roles().realmLevel().listAll();

			if (!Functions.hasEmptyOrNull(selectedRole)) {
				int selectedRoleId = Integer.parseInt(selectedRole.getName());

				if (selectedRoleId == 1 || selectedRoleId == 2) {
					// Eliminar el rol contrario si existe
					int roleToRemove = (selectedRoleId == 1) ? 2 : 1;

					userKeycloakRoles.stream()
					.filter(role -> role.getName().equals(String.valueOf(roleToRemove)))
					.findFirst()
					.ifPresent(role -> {
						keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(userId).roles().realmLevel().remove(Arrays.asList(role));
						log.info("Rol eliminado: ID: " + role.getId() + ", Nombre: " + role.getName());
					});
				} else {
					// Eliminar todos los roles numéricos que no sean 1, 2 o selectedRole
					Stream<RoleRepresentation> rolesBO = userKeycloakRoles.stream()
					.filter(role -> {
						try {
							int roleId = Integer.parseInt(role.getName());
							return roleId != 1 && roleId != 2 && roleId != selectedRoleId;
						} catch (NumberFormatException e) {
							return false; // Ignorar roles no numéricos
						}
					});

					rolesBO.forEach(role -> {
						keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(userId).roles().realmLevel().remove(Arrays.asList(role));
						log.info("Rol eliminado: ID: " + role.getId() + ", Nombre: " + role.getName());
					});

				}

				// Asignar selectedRole al usuario si no está ya asignado
				boolean roleYaExiste = userKeycloakRoles.stream().anyMatch(role -> role.getName().equals(selectedRole.getName()));

				if (!roleYaExiste) {
					keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(userId).roles().realmLevel().add(Arrays.asList(selectedRole));
					log.info("Rol " + selectedRole.getName() + " asignado al usuario con ID: " + userId);
				}

			} /*else {
				// Si selectedRole es null, eliminar todos los roles
				userRoles.forEach(role -> {
					keycloak.realm(keyCloakPropertyConfig.getRealm())
					.users().get(userId).roles().realmLevel()
					.remove(Arrays.asList(role));
					log.info("Rol eliminado: ID: " + role.getId() + ", Nombre: " + role.getName());
				});
			}*/
		} catch (Exception e) {
			log.error("Ocurrió un error al eliminar roles del usuario: " + e.getMessage());
		}
	}
    
//	private RoleRepresentation createRoleKeycloak(String roleName, String description) {
//
//		try {
//			RolesResource rolesResource = keycloak.realm(keyCloakPropertyConfig.getRealm()).roles();
//			RoleRepresentation newRole = new RoleRepresentation();
//			newRole.setName(roleName);
//			newRole.setDescription(description);
//
//			rolesResource.create(newRole);
//			log.info("Rol creado: " + roleName);
//		} catch (Exception e) {
//			throw new RuntimeException("Error creando rol: " + e.getMessage());
//		}
//
//		return null;
//	}
	
	private void removeKeycloakUser(Users user) throws Exception {
		try {
			// Buscar el usuario en Keycloak
			List<UserRepresentation> users = keycloak.realm(keyCloakPropertyConfig.getRealm()).users().search(user.getUsername(), true);

			if (users.isEmpty()) {
				log.warn("No se encontró el usuario con nombre de usuario: " + user.getUsername());
				throw new KeycloakUserNotFoundException("No se encontro usuario en Keycloak con username " + user.getUsername());
			}

			String userId = users.get(0).getId();
			UserResource userResource = keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(userId);

			// Obtener los roles actuales del usuario
			List<RoleRepresentation> currentRoles = userResource.roles().realmLevel().listAll();

			// Verificar el rol del usuario
			int userRole = user.getRole();

			if (userRole == 1 || userRole == 2) {
				// Eliminar roles 1 y 2 si existen
				List<RoleRepresentation> rolesToRemove = currentRoles.stream()
						.filter(role -> role.getName().equals("1") || role.getName().equals("2"))
						.collect(Collectors.toList());

				if (!rolesToRemove.isEmpty()) {
					userResource.roles().realmLevel().remove(rolesToRemove);
					log.info("Roles 1 y 2 eliminados del usuario con ID: " + userId);
				}
			} else {
				// Eliminar el rol específico si existe
				List<RoleRepresentation> roleToRemove = currentRoles.stream()
						.filter(role -> role.getName().equals(String.valueOf(userRole)))
						.collect(Collectors.toList());

				if (!roleToRemove.isEmpty()) {
					userResource.roles().realmLevel().remove(roleToRemove);
					log.info("Rol " + userRole + " eliminado del usuario con ID: " + userId);
				}
			}

			// Verificar si el usuario tiene roles restantes
			List<RoleRepresentation> remainingRoles = userResource.roles().realmLevel().listAll();

			if (remainingRoles.isEmpty()) {
				userResource.remove();
				log.info("Usuario con ID: " + userId + " ha sido eliminado exitosamente.");
				user.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_SENDED);
				user.setData(null);
			} else {
				boolean existeRolDefault = remainingRoles.size() == 1 && remainingRoles.get(0).getName().equals("default-roles-" + this.realmKeycloak.trim());

				if (existeRolDefault) {
					userResource.remove();
					log.info("Usuario con ID: " + userId + " ha sido eliminado exitosamente (solo tenía el rol default-roles-" + this.realmKeycloak.trim());
					user.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_SENDED);
					user.setData(null);
				} else {
					// Verificar si hay roles diferentes de 1 o 2
					boolean existeRolBackOffice = remainingRoles.stream().anyMatch(role -> !role.getName().equals("1")
							&& !role.getName().equals("2") && !role.getName().equals("default-roles-" + this.realmKeycloak.trim()));
					UserRepresentation userRep = userResource.toRepresentation();
					
					String valorAtributoBOKeycloak = existeRolBackOffice ? "true" : "false";
					userRep.singleAttribute("BO", valorAtributoBOKeycloak);
					log.info("Atributo BO del usuario con ID: " + userId + " ha sido establecido como " + valorAtributoBOKeycloak);

					userResource.update(userRep);
					user.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_SENDED);
					user.setData(null);
				}
			}

			this.usersRepoJPA.save(user);

		} catch (KeycloakUserNotFoundException e) {
			log.error("Ocurrió un error al procesar el usuario " + user.getUsername() + ". ERROR: " + e.getMessage());
			user.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_ERROR);
    		user.setData(LocalDate.now() + " - " + e.getMessage());
			this.usersRepoJPA.save(user);
		} catch (Exception e) {
			log.error("Ocurrió un error al procesar el usuario " + user.getUsername() + ". ERROR: " + e.getMessage());
			throw new Exception("Error al procesar el usuario: " + e.getMessage(), e);
		}
	}
	
	@Override
	public void addOrUpdateUsersKeycloak() throws Exception {
		this.addUsersFOKeycloak();
		this.addUsersBOKeycloak();
	}

	@Override
	public void removeUsersKeycloak() throws Exception {
		this.deleteUsersKeycloak();
	}

	@Override
	public void disableUsersKeycloak() throws Exception {
		this.disableUsers();
	}
	
	private void addUsersFOKeycloak() {
		logger.info(">>>>>>>> AGREGANDO USUARIOS FO BD A KEYCLOAK <<<<<<<<");
		try {
			List<Long> roles = Arrays.asList(1L, 2L);

			List<ClientsUsersRel> usuariosFOActivosParaAgregar = this.clientsUsersRelRepoJPA
					.findByUserStatusAndUserStatuskcAndUserRoleInAndUserUsernameNotNullAndUserEmailNotNull(
							"A",
							ConstantsKeycloak.KEYCLOAK_STATUS_PENDING,
							roles
							);

			if(!Functions.hasEmptyOrNull(usuariosFOActivosParaAgregar)) {
				
				Map<Users, Map<String, List<String>>> clientTypeUpisDeUsuarioBD = this.procesarClientesToMap(usuariosFOActivosParaAgregar);
				
				clientTypeUpisDeUsuarioBD.forEach((user, upisClientTypeMap) -> {
					logger.info("******** addOrUpdateKCUser USUARIO FO " + user.getIdUser() + " ********");
					try {
						this.addOrUpdateKCUser(user, upisClientTypeMap);
					} catch (Exception e) {
						log.error("Error al agregar usuario. " + e.getMessage());
					}
					logger.info("******** addOrUpdateKCUser USUARIO FO " + user.getIdUser() + " FIN ********");
					logger.info("");
				});
				
			}
		} catch (Exception e) {
			log.error("Ocurrio un error al agregar los usuarios a keycloak" + e.getMessage());
		}

		logger.info(">>>>>>>> AGREGANDO USUARIOS FO BD A KEYCLOAK END <<<<<<<<");
	}
	
	private Map<Users, Map<String, List<String>>> procesarClientesToMap(List<ClientsUsersRel> clientsUsersRelList) {
		return clientsUsersRelList.stream()
				.collect(Collectors.groupingBy(
						ClientsUsersRel::getUser, // Agrupar por la entidad User
						Collectors.groupingBy(
								rel -> rel.getClient().getClientType(), // Agrupar por clientType
								Collectors.mapping(
										rel -> rel.getClient().getUpi(), // Extraer el UPI
										Collectors.toList() // Coleccionar en una lista
										)
								)
						));
	}

	private void addUsersBOKeycloak() {
		logger.info(">>>>>>>> AGREGANDO USUARIOS BO BD A KEYCLOAK - REALM {} <<<<<<<<", this.realmKeycloak);
		try {
			List<Long> roles = Arrays.asList(1L, 2L);

			List<Users> usuariosBOActivosParaAgregar = this.usersRepoJPA
					.findUsersByStatusAndStatuskcAndRoleNotInAndUsernameNotNullAndEmailNotNull(
							"A",
							ConstantsKeycloak.KEYCLOAK_STATUS_PENDING,
							roles
							);

			if(!Functions.hasEmptyOrNull(usuariosBOActivosParaAgregar)) {
				usuariosBOActivosParaAgregar.forEach(user -> {
					logger.info("******** addOrUpdateKCUser USUARIO BO " + user.getIdUser() + " ********");
					try {
						this.addOrUpdateKCUser(user, null);
					} catch (Exception e) {
						log.error("Error al agregar usuario. " + e.getMessage());
					}
					logger.info("******** addOrUpdateKCUser USUARIO BO " + user.getIdUser() + " FIN ********");
					logger.info("");
				});
			}
		} catch (Exception e) {
			log.error("Ocurrio un error al agregar los usuarios a keycloak" + e.getMessage());
		}

		logger.info(">>>>>>>> AGREGANDO USUARIOS BO BD A KEYCLOAK END - REALM {} <<<<<<<<", this.realmKeycloak);
	}

	private void deleteUsersKeycloak() {
		logger.info(">>>>>>>> ELIMINANDO USUARIOS BO BD A KEYCLOAK <<<<<<<<");
		try {
			List<Users> usuariosBOEliminados = this.usersRepoJPA.findUsersByStatusAndStatuskcAndUsernameNotNullAndEmailNotNull("E", ConstantsKeycloak.KEYCLOAK_STATUS_PENDING);

			if(!Functions.hasEmptyOrNull(usuariosBOEliminados)) {
				usuariosBOEliminados.forEach(u -> {
					log.info("Se elimina usuario " + u.getUsername() + " de keycloak");
					try {
						this.removeKeycloakUser(u);
					} catch (Exception e) {
						log.error("Error al eliminar usuario. " + e.getMessage());
					}
				});
			}
		} catch (Exception e) {
			log.error("Ocurrio un error al eliminar los usuarios de keycloak" + e.getMessage());
		}

		logger.info(">>>>>>>> ELIMINANDO USUARIOS BO BD A KEYCLOAK END <<<<<<<<");
	}

	private void disableUsers() {
		logger.info(">>>>>>>> INACTIVAR USUARIOS BD A KEYCLOAK <<<<<<<<");
		try {
			List<Users> usuariosInactivos = this.usersRepoJPA.findUsersByStatusAndStatuskcAndUsernameNotNullAndEmailNotNull("I", ConstantsKeycloak.KEYCLOAK_STATUS_PENDING);

			if(!Functions.hasEmptyOrNull(usuariosInactivos)) {
				usuariosInactivos.forEach(u -> {
					log.info("Se inactiva usuario " + u.getUsername() + " ID: " + u.getIdUser() + " de keycloak");
					try {
						this.disableKeycloakUser(u);
					} catch (Exception e) {
						log.error("Error al inactivar usuario. " + e.getMessage());
					}
				});
			}
		} catch (Exception e) {
			log.error("Ocurrio un error al eliminar los usuarios de keycloak" + e.getMessage());
		}

		logger.info(">>>>>>>> INACTIVAR USUARIOS BD A KEYCLOAK END <<<<<<<<");
	}

	private void disableKeycloakUser(Users u) throws Exception {
		try {
			List<UserRepresentation> users = keycloak.realm(keyCloakPropertyConfig.getRealm())
					.users().search(u.getUsername(), null, null, u.getEmail(), null, null);

			if (Functions.hasEmptyOrNull(users)) {
				log.error("No se encontro usuario con username " + u.getUsername() + " y email: " + u.getEmail());
				throw new KeycloakUserNotFoundException("No se encontro usuario con username " + u.getUsername() + " y email: " + u.getEmail());
			}

			UserRepresentation user = users.get(0);

			UserResource userResource = keycloak.realm(keyCloakPropertyConfig.getRealm()).users().get(user.getId());

			List<RoleRepresentation> remainingRoles = userResource.roles().realmLevel().listAll();
			boolean existeRol = remainingRoles.stream().anyMatch(role -> role.getName().equals(""+u.getRole()));

			if (!existeRol) {
				log.error("No existe el rol " + u.getRole() + " para el usuario con username " + u.getUsername() + " en Keycloak");
				throw new KeycloakException("No existe el rol " + u.getRole() + " para el usuario con username " + u.getUsername() + " en Keycloak");
			}

			user.setEnabled(false);
			userResource.update(user);
			log.info("Usuario inactivado con exito");

			u.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_SENDED);
			u.setData(null);
			this.usersRepoJPA.save(u);

		} catch (KeycloakException e) {
    		u.setData(LocalDate.now() + " - " + e.getMessage());
			u.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_ERROR);
			this.usersRepoJPA.save(u);
		} catch (KeycloakUserNotFoundException e) {
    		u.setData(LocalDate.now() + " - " + e.getMessage());
			u.setStatuskc(ConstantsKeycloak.KEYCLOAK_STATUS_ERROR);
			this.usersRepoJPA.save(u);
		}
	}
	
	 @Override
	    public List<UserRepresentation> obtenerUsuariosKeycloakPorRol(Collection<String> roleNames) {
	        try {
	            if (roleNames == null || roleNames.isEmpty()) {
	                log.warn("No se proporcionaron nombres de roles.");
	                return List.of();
	            }
	            
	            RealmResource rr = keycloak.realm(keyCloakPropertyConfig.getRealm());

	            Map<String, UserRepresentation> uniqueUsers = new LinkedHashMap<>();

	            for (String roleName : roleNames) {
	                if (roleName == null || roleName.isBlank()) continue;

	                try {
	                    RoleResource role = rr.roles().get(roleName);

	                    // Paginación defensiva: iterar hasta que no haya más resultados.
	                    int first = 0;
	                    final int pageSize = 200; // ajusta según tu tamaño esperado

	                    while (true) {
	                        List<UserRepresentation> page = role.getUserMembers(first, pageSize);
	                        if (page == null || page.isEmpty()) break;

	                        for (UserRepresentation u : page) {
	                            // deduplicar por id
	                            uniqueUsers.put(u.getId(), u);
	                        }

	                        if (page.size() < pageSize) break; // última página
	                        first += pageSize;
	                    }

	                    log.info("Role '{}' → usuarios acumulados: {}", roleName, uniqueUsers.size());
	                } catch (javax.ws.rs.NotFoundException nf) {
	                    // Si el rol no existe en el realm
	                    log.warn("Role '{}' no encontrado en el realm '{}'. Se continúa con el siguiente.", roleName, rr);
	                } catch (Exception e) {
	                    // Errores específicos por rol se loguean y seguimos
	                    log.error("Error consultando miembros del role '{}': {}", roleName, e.getMessage());
	                }
	            }

	            log.info("Total usuarios con alguno de los roles {}: {}", roleNames, uniqueUsers.size());
	            return new ArrayList<>(uniqueUsers.values());

	        } catch (Exception e) {
	            log.error("Error obteniendo usuarios por roles {}: {}", roleNames, e.getMessage());
	            throw new RuntimeException("Error obteniendo usuarios por roles: " + e.getMessage(), e);
	        }
	    }

		@Override
		public Optional<Instant> obtenerUltimoLoginPorUsuario(String userIdKeycloak) {

			try {
				RealmResource rr = keycloak.realm(keyCloakPropertyConfig.getRealm());
				List<EventRepresentation> events = rr.getEvents(List.of("LOGIN"), null, userIdKeycloak, null, null, null, 0,
						50);

				return events.stream().filter(e -> "LOGIN".equalsIgnoreCase(e.getType()))
						.map(e -> Instant.ofEpochMilli(e.getTime())).max(Comparator.naturalOrder());

			} catch (Exception e) {
				log.error("Error obteniendo último LOGIN para userId {}: {}", userIdKeycloak, e.getMessage());
				return Optional.empty();
			}

		}

		@Override
		public UserRepresentation obtenerUsuarioKeycloakPorRut(String rutUsuario) {
			List<UserRepresentation> users = keycloak.realm(keyCloakPropertyConfig.getRealm())
					.users().search(rutUsuario, null, null, null, null, null);
			
			return users == null || users.isEmpty() ? null : users.get(0);
		}
		
		@Override
		public void validarUsuarioLocalContraUsuarioKeycloakParaDeshabilitar(Users usuario, Integer diasInactividadMaximo) {
			
			//Validar que usuario no sea null
			if(usuario == null || usuario.getRut() == null || usuario.getRut().isBlank()) {
				log.error("usuario viene null en el metodo: validarUsuariosParaDeshabilitar");
				return;
			}
			
			if(diasInactividadMaximo == null || diasInactividadMaximo <= 0) {
				Integer variableDiasInactividad = null;
				
				try {
					variableDiasInactividad = this.variableServiceImpl.getVariableAsInteger(
							PropertiesConstants.PROPERTIES_PORTAL_TODOS,
							PropertiesConstants.DIAS_INACTIVO_DESHABILITAR_USUARIO);
				} catch (VariableException e) {
					log.error("Ha ocurrio un error al traer la variable con el portal: {} y el nombre: {} , error: {}",
							PropertiesConstants.PROPERTIES_PORTAL_TODOS, PropertiesConstants.DIAS_INACTIVO_DESHABILITAR_USUARIO,
							e.getMessage());
					variableDiasInactividad = 180;
				} catch (Exception e) {
					log.error("Ha ocurrio un error al traer la variable con el portal: {} y el nombre: {} , error: {}",
							PropertiesConstants.PROPERTIES_PORTAL_TODOS, PropertiesConstants.DIAS_INACTIVO_DESHABILITAR_USUARIO,
							e.getMessage());
					variableDiasInactividad = 180;
				}
				
				diasInactividadMaximo = variableDiasInactividad;
				
			}
			
			
			//Obtengo la fecha y hora maxima permitida para el usuario
			LocalDateTime fechaHoraMaximoInactividad = LocalDateTime.now(ZONE).minusDays(diasInactividadMaximo);
			
			log.info("Usuario a validar para la deshabilitacion {}",usuario.getRut());
			
			//Obtengo usuario de keycloak
			UserRepresentation usuarioKeycloak = this.obtenerUsuarioKeycloakPorRut(usuario.getRut());
			
			if(usuarioKeycloak == null) {
				log.warn("Usuario no se encontro en keycloak, por lo tanto no se puede validar: {}",usuario.getRut());
				return;
			}
			
			//si la fecha de creacion es nula traigo la de keycloak y la seteo en local
			if(usuario.getFechaCreacion() == null) {
				
				if(usuarioKeycloak.getCreatedTimestamp() == null) {
					log.warn("No se puede validar al usuario porque no tiene fecha de creacion en Keycloak");
					return;
				}
				
				LocalDateTime fechaCreacionKeycloak = LocalDateTime.ofInstant(Instant.ofEpochMilli(usuarioKeycloak.getCreatedTimestamp()), ZONE);
				usuario.setFechaCreacion(fechaCreacionKeycloak);
				this.usersRepoJPA.save(usuario);
			}
			
			
			//si la ultima fecha de login es nula o es menor a la fecha permitida busco en keycloak
			if (usuario.getDateLastLogin() == null || usuario.getDateLastLogin().isBefore(fechaHoraMaximoInactividad)) {

				Optional<Instant> optionalUltimoEventoDeLogin = this
						.obtenerUltimoLoginPorUsuario(usuarioKeycloak.getId());

				// si keycloak no contiene el evento de login usuario no se puede validar
				if (optionalUltimoEventoDeLogin.isPresent()) {

					Instant ultimoEventoDeLogin = optionalUltimoEventoDeLogin.get();
					LocalDateTime fechaLoginKeycloak = LocalDateTime.ofInstant(ultimoEventoDeLogin, ZONE);
					usuario.setDateLastLogin(fechaLoginKeycloak);
					this.usersRepoJPA.save(usuario);

				} else {
					log.warn("Usuario con rut {}, no tiene inicio de sesion en keycloak",usuario.getRut());
				}

			}
			
			//Se compara si el usuario es o no apto para inhabilitarlo comparado con la informacion traida de keycloak
			if(usuario.getDateLastLogin() != null && usuario.getDateLastLogin().isBefore(fechaHoraMaximoInactividad)) {
				log.info(
						"Se deshabilita al usuario con ID: {} y RUT: {} , porque su ultima fecha de conexion fue el dia {} y su fecha de creacion fue {}",
						usuario.getIdUser(), usuario.getRut(), usuario.getDateLastLogin(), usuario.getFechaCreacion());
				usuario.setStatuskc(UserConstants.USER_STATUS_KEYCLOAK_PENDING);
				usuario.setStatus(UserConstants.USER_STATUS_INACTIVO);
				this.usersRepoJPA.save(usuario);
			}else if(usuario.getDateLastLogin() == null && usuario.getFechaCreacion().isBefore(fechaHoraMaximoInactividad)) {
				log.info(
						"Se deshabilita al usuario con ID: {} y RUT: {} , porque su ultima fecha de conexion fue el dia {} y su fecha de creacion fue {}",
						usuario.getIdUser(), usuario.getRut(), usuario.getDateLastLogin(), usuario.getFechaCreacion());
				usuario.setStatuskc(UserConstants.USER_STATUS_KEYCLOAK_PENDING);
				usuario.setStatus(UserConstants.USER_STATUS_INACTIVO);
				this.usersRepoJPA.save(usuario);
			}else {
				log.info("Se actualiza al usuario {}",usuario.getRut());
			}
			
 			
		}
		
		
	
}
