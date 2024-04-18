package radion.app.bot_v1_0.constant;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class ConstantCONSTRUCTOR {

    /**
     * Элементы для конструктора
     */

    public final String START_CLASS = "public class";
    public final String BRACKET_IN = "{";
    public final String BRACKET_OUT = "}";

    public final String ANNOTATION_DATA = "@Data\n";
    public final String ANNOTATION_ENTITY = "@Entity\n";
    public final String ANNOTATION_ID = "@Id\n";
    public final String ANNOTATION_GENERATED_VALUE = "@GeneratedValue(strategy = GenerationType.IDENTITY)\n";
    public final String ANNOTATION_TABLE = "@Table(name = \"new_table\")\n";

    public final String GETTER = "@Getter\n";
    public final String SETTER = "@Setter\n";
}
