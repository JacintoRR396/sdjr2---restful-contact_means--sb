package com.sdjr2.rest_contact_meanssb.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdjr2.rest_contact_meanssb.models.errors.RespEntityErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * {@link UJsonAdapter} class.
 * <p>
 * <strong>Utilities</strong> - Represents a class to manage object mapping.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Utilities
 * @upgrade 24/06/16
 * @since 24/06/16
 */
public class UJsonAdapter {

    /**
     * Logger object
     */
    private static final Logger LOG = LoggerFactory.getLogger(UJsonAdapter.class);

    /**
     * Mapper object
     */
    private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

    private UJsonAdapter() {

    }

    /**
     * Read a json according to a certain class
     *
     * @param jsonData json object.
     * @param clazz    class object <T>.
     * @return a class type <T> object.
     */
    public static <T> T readValue(final String jsonData, final Class<T> clazz) {
        try {
            if ((jsonData != null) && !jsonData.isEmpty()) {
                return UJsonAdapter.OBJ_MAPPER.readValue(jsonData, clazz);
            } else {
                return null;
            }
        } catch (NullPointerException | IOException e) {
            UJsonAdapter.LOG.error(e.getMessage());
            return null;
        }
    }

    /**
     * Write a class object to string
     *
     * @param modelObject class object <T>.
     * @return a class type <T> object like string.
     */
    public static <T> String writeValueAsString(final T modelObject) {
        try {
            if (modelObject != null) {
                return UJsonAdapter.OBJ_MAPPER.writeValueAsString(modelObject);
            } else {
                return null;
            }
        } catch (NullPointerException | IOException e) {
            UJsonAdapter.LOG.error(e.getMessage());
            return null;
        }
    }
}
