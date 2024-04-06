package radion.app.bot_v1_0.valid;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:/templates/invalid.properties")
public class NotValidMessage {
    @Value("${incorrect.message}")
    private String incorrectMessage;
}
