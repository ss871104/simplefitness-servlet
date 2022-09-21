package com.common.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.common.adapter.LocalDateTimeAdapter;
import com.common.adapter.LocalTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {
	
	public static final Gson GSON = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd")
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
			.create();
	
	public static final String BASE64 = "data:image/jpeg;base64,";

}
