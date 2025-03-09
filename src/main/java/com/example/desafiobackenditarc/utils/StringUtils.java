package com.example.desafiobackenditarc.utils;

import com.example.desafiobackenditarc.exception.JsonMapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern NON_ASCII_PATTERN = Pattern.compile("[^\\p{ASCII}]");

    public static String normalizeString(String input) {
        if (input == null) {
            return null;
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return NON_ASCII_PATTERN.matcher(normalized).replaceAll("");
    }

    public static boolean compareStrings(String str1, String str2) {
        String normalizedStr1 = normalizeString(str1);
        String normalizedStr2 = normalizeString(str2);
        return normalizedStr1.equalsIgnoreCase(normalizedStr2);
    }

    public static String toJson(final Object object) throws JsonMapperException {
        try {
            return buildObjectMapper().writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new JsonMapperException(e.getMessage());
        }
    }

    private static ObjectMapper buildObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        return objectMapper;
    }
}
