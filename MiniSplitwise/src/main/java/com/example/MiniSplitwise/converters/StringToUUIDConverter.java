package com.example.MiniSplitwise.converters;
import org.springframework.core.convert.converter.Converter;
import java.util.UUID;

public class StringToUUIDConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String source) {
        try {
            return UUID.fromString(source);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID string: " + source);
        }
    }
}
