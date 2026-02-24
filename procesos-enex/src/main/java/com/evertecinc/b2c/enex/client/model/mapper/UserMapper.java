package com.evertecinc.b2c.enex.client.model.mapper;

import com.evertecinc.b2c.enex.client.model.dto.UserDTO;
import com.evertecinc.b2c.enex.client.model.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setRut(user.getRut());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(user.getRole() != null ? user.getRole().longValue() : null);
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setNumEntries(user.getNumRetries() != null ? user.getNumRetries().longValue() : null);
        userDTO.setDateLastLogin(user.getDateLastLogin());
        userDTO.setStatus(user.getStatus());
        userDTO.setNewPass(user.getNewPass() != null ? user.getNewPass().longValue() : null);
        userDTO.setIsAdmin(user.getRole() != null && user.getRole() == 1); // Ejemplo de lógica
        // Otros campos se pueden mapear aquí según la lógica de negocio.

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) return null;

        User user = new User();
        user.setIdUser(userDTO.getIdUser());
        user.setRut(userDTO.getRut());
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole() != null ? userDTO.getRole().intValue() : null);
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setNumRetries(userDTO.getNumEntries() != null ? userDTO.getNumEntries().intValue() : null);
        user.setDateLastLogin(userDTO.getDateLastLogin());
        user.setStatus(userDTO.getStatus());
        user.setNewPass(userDTO.getNewPass() != null ? userDTO.getNewPass().intValue() : null);
        // Otros campos se pueden mapear aquí según la lógica de negocio.

        return user;
    }
}