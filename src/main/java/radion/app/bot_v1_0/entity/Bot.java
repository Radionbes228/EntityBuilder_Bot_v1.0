package radion.app.bot_v1_0.entity;

import java.util.logging.Logger;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;



@Configuration
@Data
@PropertySource("classpath:/templates/prop.properties")
@Slf4j
public class Bot {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    @PostMapping
    private void init(){
        log.info("Bot entity create!");
    }
}

