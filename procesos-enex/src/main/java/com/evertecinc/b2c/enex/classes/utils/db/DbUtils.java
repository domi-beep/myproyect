package com.evertecinc.b2c.enex.classes.utils.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbUtils {


    /**
     * Method to create an object from a list of values and a list of properties names
     * The order of the values must be the same as the order of the properties names
     * The object must have a constructor with the same number of parameters as the number of properties names
     * The parameters of the constructor must have the same name as the properties names
     *
     * @param fieldValues list of values
     * @param fieldNames  list of properties names
     * @param clazz       class of the object to create
     * @param <T>         type of the object to create (must be the same as the class)
     * @return the object created
     */
    public static <T> T objFromFields(Object[] fieldValues, String[] fieldNames, Class<T> clazz) {

        // Create a map with the index of each property name and fill it.
        Map<String, Integer> mapIndex = new HashMap<>();
        Arrays.stream(fieldNames)
                .forEach(name -> mapIndex.put(name, Arrays.asList(fieldNames).indexOf(name)));
        try {

            // Get the constructor with the same number of parameters as the number of properties names
            Constructor<?> constructor = getConstructorByParameterName(clazz, fieldNames);
            if (constructor == null) {
                log.error("Constructor with '{}' parameters not found.", fieldNames.length);
                throw new RuntimeException("Constructor with '%d' parameters not found.".formatted(fieldNames.length));
            }
            if (constructor.getParameterCount() > 0) {
                // Case the constructor has parameters.
                Parameter[] parameters = constructor.getParameters();
                Object[] values = new Object[fieldNames.length];
                for (int i = 0; i < parameters.length; i++) {
                    // Get the value from the list of values and cast it to the type of the parameter.
                    // Need to cast because values might be a different type than the parameter.
                    if (!mapIndex.containsKey(parameters[i].getName())) {
                        log.error("Parameter '{}' not found.", parameters[i].getName());
                        throw new RuntimeException("Parameter '%s' not found.".formatted(parameters[i].getName()));
                    }
                    values[i] = castObjectFromType(fieldValues[mapIndex.get(parameters[i].getName())], parameters[i].getType());
                }
                return clazz.cast(constructor.newInstance(values));
            } else {
                // Case the constructor without parameters.
                return clazz.cast(constructor.newInstance());
            }
        } catch (InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private static Constructor<?> getConstructorByParameterName(Class<?> clazz, String[] fieldNames) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == fieldNames.length) {
                boolean found = true;
                for (Parameter parameter : constructor.getParameters()) {
                    if (!Arrays.asList(fieldNames).contains(parameter.getName())) {
                        log.error("Parameter '{}' not found.", parameter.getName());
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return constructor;
                }
            }
        }
        return null;
    }

    /**
     * Method to cast an object to a type
     *
     * @param value value to cast
     * @param type  type to cast
     * @return the value casted to the type
     */
    private static Object castObjectFromType(Object value, Type type) {
        if (value == null) return null;
        return switch (type.getTypeName()) {
            case "java.lang.String" -> value.toString();
            case "java.lang.Long", "long" -> Long.parseLong(value.toString());
            case "java.lang.Double", "double" -> Double.parseDouble(value.toString());
            case "java.lang.Integer", "int" -> Integer.parseInt(value.toString());
            case "java.lang.Boolean", "boolean" -> Boolean.parseBoolean(value.toString());
            case "java.lang.Float", "float" -> Float.parseFloat(value.toString());
            case "java.lang.Byte", "byte" -> Byte.parseByte(value.toString());
            case "java.lang.Short", "short" -> Short.parseShort(value.toString());
            case "java.math.BigDecimal" -> new java.math.BigDecimal(value.toString());
            case "java.time.LocalDateTime" -> {
                if (value instanceof LocalDate localDate) {
                    yield localDate.atStartOfDay();
                } else if (value instanceof LocalDateTime localDateTime) {
                    yield localDateTime;
                }
                yield null;
            }
            case "java.time.LocalDate" -> {
                if (value instanceof LocalDateTime localDateTime) {
                    yield localDateTime.toLocalDate();
                } else if (value instanceof LocalDate localDate) {
                    yield localDate;
                }
                yield null;
            }
            default -> null;
        };
    }
}


