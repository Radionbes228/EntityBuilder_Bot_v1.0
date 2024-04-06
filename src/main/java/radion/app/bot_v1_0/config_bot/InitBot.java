package radion.app.bot_v1_0.config_bot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import radion.app.bot_v1_0.service.TelegramBot;



@Component
@Slf4j
@AllArgsConstructor
public class InitBot {
    private final TelegramBot telegramBot;

    @EventListener({ContextRefreshedEvent.class})
    public void init()throws TelegramApiException{
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try{
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e){
            log.info("Couldn't connect" + e.getLocalizedMessage());
        }
    }
}
