package com.common.adapter;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public final class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
	
	@Override
    public JsonElement serialize(LocalTime date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_TIME).substring(0, 5));
    }

    @Override
    public LocalTime deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        String time = element.getAsJsonPrimitive().getAsString();
        return LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
    }

}
