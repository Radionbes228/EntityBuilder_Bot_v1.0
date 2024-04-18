package radion.app.bot_v1_0.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Клас, который работает с вводимым JSON`ом
 */

@Service

public class BotServiceJSON {

    public Boolean isJSON(String textJSON) throws IOException {;
        JsonFactory factory = new JsonFactory();
        try {
            JsonParser parser = factory.createParser(new StringReader(textJSON));
            while (parser.nextToken() != null);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

    @SneakyThrows
    public JsonNode getNodeJson(String textJSON){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(textJSON);
    }

    @SneakyThrows
    public List<String> getAllForeignKey(String textJSON){
        List<String> returnKeys = new ArrayList<>();
        JsonNode node = getNodeJson(textJSON);

        // Получаем набор ключей
        Iterator<String> keys = node.fieldNames();
        while (keys.hasNext()){
            returnKeys.add(keys.next());
        }

        return returnKeys;
    }


    public HashMap<String, String> getTypesKey(List<String> keys, JsonNode node){
       HashMap<String, String> types = new HashMap<>();

       for (String key: keys){
           if (node.get(key).isInt()){
               if (key.trim().matches("\\b(?:id|Id|ID|name_id)(?:_[a-zA-Z0-9]+)?\\b|\\b[a-zA-Z0-9]+_id\\b")){
                   types.put(key, "Long");
               }
               else types.put(key, "Integer");
           } else if (node.get(key).isTextual()) {
               types.put(key, "String");
           }else if (node.get(key).isDouble()) {
               types.put(key, "Double");
           }else if (node.get(key).isFloat()) {
               types.put(key, "Float");
           }else if (node.get(key).isArray()) {
               types.put(key, "List<Object>");
           }else if (node.get(key).isBoolean()){
               types.put(key, "Boolean");
           }
       }
       return types;
    }
}
