package com.laalka.paymentservice.services;

import com.netflix.discovery.converters.wrappers.CodecWrappers;
import org.springframework.stereotype.Service;

@Service
public class JsonService extends CodecWrappers.JacksonJson {

    public String serializeToJson(Object obj) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing event",e);
        }
    }
}
