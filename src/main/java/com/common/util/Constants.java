package com.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.common.adapter.LocalDateAdapter;
import com.common.adapter.LocalDateTimeAdapter;
import com.common.adapter.LocalTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {
	
	public static final Gson GSON = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
	
	public static final String BASE64 = "data:image/jpeg;base64,";

}
