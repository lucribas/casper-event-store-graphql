package com.lribas.eventstore;

//import java.text.DateFormat;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CasperJson {

    private static Gson gson;

    static {
        gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setVersion(1.0).create();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

}
