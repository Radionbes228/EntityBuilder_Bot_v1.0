package radion.app.bot_v1_0.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.bot_v1_0.constant.ConstantCONSTRUCTOR;

import java.util.List;

@Service
@AllArgsConstructor
public class BotServiceCONSTRUCTOR {
    private final ConstantCONSTRUCTOR constantCONSTRUCTOR;


    public StringBuilder buildEntity(List<String> listKeys){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(constantCONSTRUCTOR.getSTART_CLASS())
                .append(" NewEntity")
                .append(constantCONSTRUCTOR.getBRACKET_IN())
                .append("\n");

        for(String key: listKeys){
            stringBuilder.append("privet String " + key + ";\n");
        }
        return stringBuilder;
    }
}
