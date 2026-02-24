package com.evertecinc.b2c.enex.classes.utils.db;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        if (localDateTime != null) {
            jsonWriter.value(localDateTime.format(formatter));
        } else {
            jsonWriter.nullValue();
        }
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        String dateTimeString = jsonReader.nextString();
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
