package com.evertecinc.b2c.enex.utils.functions;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingInitDTO;
import com.evertecinc.b2c.enex.client.model.dto2.paging.PagingSortDTO;

public final class Functions {
	
	public static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    public static final int BASE = ALPHABET.length();

	public static String getSortString(Sort sort, Class<?> clazz) {
		if (sort == null || sort.isUnsorted() || sort.stream().findAny().isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" ORDER BY ");
		for (Sort.Order order : sort) {
			if (sb.length() > 10) { // 10 = " order by ".length()
				sb.append(", ");
			}
			String propertyName = getPropertyName(order.getProperty(), clazz);
			sb.append(propertyName).append(order.isAscending() ? " ASC" : " DESC");
		}
		return sb.toString();
	}

	private static String getPropertyName(String property, Class<?> clazz) {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
			if (propertyDescriptor.getName().equals(property)) {
				return propertyDescriptor.getName();
			}
		}
		throw new RuntimeException("Property " + property + " not found in class " + clazz.getName());
	}
	
	/**
	 * Método que decodifica un número.
	 * @param str
	 * @return
	 */
	 public static int decode(String str) {
		 int num = 0;
	     for (int i = 0; i < str.length(); i++) {
	    	 num = num * BASE + ALPHABET.indexOf(str.charAt(i));
	     }
	     return num;
	 }

	public static Long MBToBytes(Long mb) {
		return mb * 1048576L;
	}

	public static Long bytesToMb(Long bytes) {
		return bytes / 1048576L;
	}

	public static StandardReturnDTO writeFile(String rutaArchivo, byte[] bytearray) {

		/* Declarar variables del método */
		StandardReturnDTO retorno = new StandardReturnDTO();

		/* Validar parámetros de entrada */
		FileOutputStream miFicheroSt;
		if (rutaArchivo == null || rutaArchivo.length() == 0) {
			retorno.setStatus(false);
			retorno.setCode(2l);
			retorno.setMessage("No se ha podido escribir el archivo " + rutaArchivo
					+ " debido a que no viene asignado nombre/ruta del archivo");
			return retorno;
		}
		if (bytearray == null) {
			retorno.setStatus(false);
			retorno.setCode(2l);
			retorno.setMessage("No se ha podido escribir el archivo " + rutaArchivo
					+ " debido a que no vienen datos para escritura");
			return retorno;
		}

		try {
			miFicheroSt = new FileOutputStream(rutaArchivo);
			miFicheroSt.write(bytearray, 0, bytearray.length);
			miFicheroSt.close();
		} catch (Exception e) {
			retorno.setStatus(false);
			retorno.setCode(2l);
			retorno.setMessage("No se ha podido escribir el archivo " + rutaArchivo + " por el siguiente error: "
					+ e.getMessage());
			return retorno;
		}

		retorno.setStatus(true);
		retorno.setCode(0l);
		retorno.setMessage("Archivo " + rutaArchivo + " escrito en " + rutaArchivo);

		return retorno;
	}

	public static boolean hasEmptyOrNull(Object... args) {
		// Itera sobre todos los argumentos pasados al método
		for (Object obj : args) {
			// Si el argumento es null, retorna true
			if (obj == null) {
				return true;
			}

			// Si el argumento es un String y está vacío o solo contiene espacios
			if (obj instanceof String && ((String) obj).trim().isEmpty()) {
				return true;
			}

			// Si el argumento es una lista o conjunto y está vacío
			if (obj instanceof List<?> && ((List<?>) obj).isEmpty()) {
				return true;
			}

			// Si el argumento es un mapa y está vacío
			if (obj instanceof Map<?, ?> && ((Map<?, ?>) obj).isEmpty()) {
				return true;
			}

			// Si el argumento es un arreglo y está vacío
			if (obj.getClass().isArray() && ((Object[]) obj).length == 0) {
				return true;
			}

			// Si el argumento es un Optional y está vacío
			if (obj instanceof Optional<?> && !((Optional<?>) obj).isPresent()) {
				return true;
			}
			
			// Si el argumento es un Boolean y es false
			if (obj instanceof Boolean && !((Boolean) obj)) {
				return true;
			}
		}

		return false; // Retorna false si ninguno es null o vacío
	}

	public static String normalize(String input) {
		if (input == null) {
			return null; // Si la entrada es null, se retorna null
		}

		// Normaliza el texto: elimina acentos y convierte a una forma de texto sin
		// marcas diacríticas
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

		// Elimina los caracteres diacríticos (acentos, tildes, etc.)
		normalized = normalized.replaceAll("[^\\p{ASCII}]", "");

		// Elimina espacios
		normalized = normalized.replaceAll("\\s+", "_");

		// Convierte todo a mayúsculas
		return normalized;
	}

	/**
	 * @param sort
	 * @param paging
	 * @return
	 */
	public static Pageable getPageable(PagingSortDTO sort, PagingInitDTO paging) {
		Pageable pages;
		if(sort != null)
			pages = paging.isPaged() ? PageRequest.of(paging.pageNumber() - 1, paging.pageSize(), PagingSortDTO.getSFSort(sort)) : Pageable.unpaged(PagingSortDTO.getSFSort(sort));
		else
			pages = paging.isPaged() ? PageRequest.of(paging.pageNumber() - 1, paging.pageSize()) : Pageable.unpaged();
		return pages;
	}
	
	
	public static String obtenerNombreBanco(String payType, String bancoDeposito) {
		return switch (payType) {
		case "C" -> "Banco de Chile";
		case "S" -> "Banco Santander";
		case "E" -> "Banco Estado";
		case "T" -> "Webpay";
		case "B" -> "Banco TBanc";
		case "I" -> "BCI";
		case "D" -> "Depósito - " + bancoDeposito;
		default -> "Desconocido";
		};
	}

	public static String obtenerRespuestaActualizada(String payType, String respuesta) {
        return switch (payType) {
            case "Banco de Chile", "Banco Santander" -> 
                "0000".equals(respuesta) ? "Aprobada" : respuesta == null ? " " : "Rechazada " + respuesta;
            case "Banco Estado" -> 
                "OK".equals(respuesta) ? "Aprobada" : respuesta == null ? " " : "Rechazada " + respuesta;
            case "Webpay" -> 
                "0".equals(respuesta) ? "Aprobada" : respuesta == null ? " " : "Rechazada " + respuesta;
            case "Banco TBanc", "BCI" -> 
                "021".equals(respuesta) ? "Aprobada" : respuesta == null ? " " : "Rechazada " + respuesta;
            default -> respuesta;
        };
    }
	
	public static Integer convertBooleanToInteger(Boolean value) {
	    return value == null ? null : value ? 1 : 0;
	}
	
	public static String iLikeOrNull(String params) {
		return (params == null ? null : ('%' + params + '%'));
	}
	
	public static <T> String transformarListaAString(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            return ""; // Retorna un String vacío si la lista es nula o está vacía
        }
        return lista.stream()
                    .map(String::valueOf) // Convierte cada elemento al tipo String
                    .collect(Collectors.joining(",")); // Une los elementos separados por comas
    }
	
	public static String getExtension(String fileName) {
	    if (fileName == null || fileName.isEmpty()) {
	        return ""; // No extension found
	    }
	    int lastDotIndex = fileName.lastIndexOf('.');
	    if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
	        return ""; // No extension found or dot is the last character
	    }
	    return fileName.substring(lastDotIndex + 1);
	}
	
	public static <T> void sanitizeCampos(T entity) {
        if (entity == null) {
            return; // Si el objeto es null, no hace nada
        }
        try {
            // Iterar sobre los campos de la clase
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true); // Permitir acceso a campos privados
                Object value = field.get(entity); // Obtener el valor del campo
                if (hasEmptyOrNull(value)) {
                    field.set(entity, null); // Establecer el valor en null
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error al sanitizar los campos del objeto", e);
        }
    }
	
	private static final Map<String, String> portalMap = new HashMap<>();

	static {
		// Inicializar el mapa con los códigos de portal y sus nombres
		portalMap.put("SCE", "Portal Shellcard Empresa");
		portalMap.put("PRE", "Portal Shellcard Empresa");
		portalMap.put("SCT", "Portal Shellcard Transporte");
		portalMap.put("SCI", "Portal Shellcard Institucional");
		portalMap.put("STO", "Portal Storage");
		portalMap.put("SST", "Portal Shellcard Control Total");
		portalMap.put("BO", "Portal BackOffice");
	}

	public static String getPortalName(String portalCode) {
		// Retornar el nombre del portal o un mensaje por defecto si no se encuentra
		return portalMap.getOrDefault(portalCode, "Código de portal no reconocido");
	}
	
	public static Boolean existePortal(String portal) {
		
		
		switch(portal) {
			case "SCE":break;
			case "PRE":break;
			case "SCT":break;
			case "SCI":break;
			case "STO":break;
			case "SST":break;
			case "BO":break;
			default: return false;
		}
		
		return true;
	}
	
	
}
