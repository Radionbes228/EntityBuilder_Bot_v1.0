package radion.app.bot_v1_0.constant;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@Scope(scopeName = "singleton")
public class ConstantCONSTRUCTOR {

    /**
     * Элементы для конструктора
     */

    private final String START_CLASS = "public class";
    private final String BRACKET_IN = "{";
    private final String BRACKET_OUT = "}";

    private final String ANNOTATION_DATA = "@Data";
    private final String ANNOTATION_ENTITY = "@Entity";
    private final String ANNOTATION_TABLE = "@Table";

    private final String GETTER = "@Getter";
    private final String SETTER = "@Setter";
}
