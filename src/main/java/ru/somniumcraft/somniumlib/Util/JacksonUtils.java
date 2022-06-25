package ru.somniumcraft.somniumlib.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.experimental.UtilityClass;


@UtilityClass
public class JacksonUtils {

    private final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private final ObjectWriter JSON_WRITER = JSON_MAPPER.writerWithDefaultPrettyPrinter();

    private final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());
    private final ObjectWriter YAML_WRITER = YAML_MAPPER.writerWithDefaultPrettyPrinter();

    public String objectToJson(Object value) {
        try {
            return JSON_WRITER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T jsonToObject(String json, Class<T> mapping) {
        try {
            return JSON_MAPPER.readValue(json, mapping);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String objectToYaml(Object value) {
        try {
            return YAML_WRITER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T yamlToObject(String yaml, Class<T> mapping) {
        try {
            return YAML_MAPPER.readValue(yaml, mapping);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
