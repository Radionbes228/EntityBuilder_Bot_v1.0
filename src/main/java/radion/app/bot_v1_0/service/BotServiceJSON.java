package radion.app.bot_v1_0.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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
    public List<String> getAllForeignKey(String textJSON){
        List<String> returnKeys = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(textJSON);
/*

        if (rootNode.get("id").getNodeType() == JsonNodeType.NUMBER)
            System.out.println(true);

*/


        // Получаем набор ключей
        Iterator<String> keys = rootNode.fieldNames();
        while (keys.hasNext()){
            returnKeys.add(keys.next());
        }

        return returnKeys;
    }

}
