package radion.app.bot_v1_0.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/templates/default_message.properties")
@Data
public class DefaultMessage {
    @Value("${greeting}")
    private String greeting;
}
