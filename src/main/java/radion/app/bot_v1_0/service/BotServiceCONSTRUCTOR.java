package radion.app.bot_v1_0.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.bot_v1_0.constant.ConstantCONSTRUCTOR;

import java.util.HashMap;
import java.util.List;

/**
 * Этот класс собирает сущность
 */

@Service
@AllArgsConstructor
public class BotServiceCONSTRUCTOR {
    private final ConstantCONSTRUCTOR constantCONSTRUCTOR;
    private final BotServiceJSON botServiceJSON;


    public String buildEntity(String textJSON){
        List<String> keys = botServiceJSON.getAllForeignKey(textJSON);
        HashMap<String, String> mapKeysAndTypes = botServiceJSON.getTypesKey(keys, botServiceJSON.getNodeJson(textJSON));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(constantCONSTRUCTOR.ANNOTATION_ENTITY)
                .append(constantCONSTRUCTOR.ANNOTATION_DATA)
                .append(constantCONSTRUCTOR.ANNOTATION_TABLE)
                .append(constantCONSTRUCTOR.START_CLASS)
                .append(" NewEntity")
                .append(constantCONSTRUCTOR.BRACKET_IN)
                .append("\n");


        for (String key: keys){
            if (key.trim().matches("\\b(?:id|Id|ID|name_id)(?:_[a-zA-Z0-9]+)?\\b|\\b[a-zA-Z0-9]+_id\\b")){
                stringBuilder
                        .append(constantCONSTRUCTOR.ANNOTATION_ID)
                        .append(constantCONSTRUCTOR.ANNOTATION_GENERATED_VALUE)
                        .append("private ");
            }
            stringBuilder.append("private ").append(mapKeysAndTypes.get(key)).append(" ").append(key).append(";\n");
        }
        stringBuilder.append(constantCONSTRUCTOR.BRACKET_OUT).append("\n");

        return stringBuilder.toString();
    }
}
