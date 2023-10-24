package com.pfmjg.personalfinancialmanagementjonathangalassi.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomTimeDeserializer extends JsonDeserializer<Time> {

    @Override
    public Time deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = jsonParser.getText();
        try {
            return new Time(format.parse(time).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de hora inválido: " + time);
        }
    }

}
