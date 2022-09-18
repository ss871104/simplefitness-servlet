package com.common.util;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {
	
	public static final Gson GSON = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd")
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.create();
	
	public static final String BASE64 = "data:image/jpeg;base64,";

}
