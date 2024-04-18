package radion.app.bot_v1_0.service;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import radion.app.bot_v1_0.entity.Bot;
import radion.app.bot_v1_0.entity.DefaultMessage;
import radion.app.bot_v1_0.valid.NotValidMessage;

@Component
@AllArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    /**
     * Главный класс сервиса для телеграм бота, здесь происходит основная логика выполнения.
     */

    private final Bot bot;
    private final BotServiceJSON botServiceJSON;
    private final BotServiceCONSTRUCTOR botServiceCONSTRUCTOR;
    private final NotValidMessage notValidMessage;
    private final DefaultMessage defaultMessage;

    @PostConstruct
    private void initTelegramBot(){
        log.info("Telegram bot init!");
    }

    @Override
    public String getBotUsername() {
        String botName = bot.getBotName();
        log.info("Telegram bot getBotUsername(): " + botName);
        return botName;
    }

    @Override
    public String getBotToken() {
        String botToken = bot.getBotToken();
        log.info("Telegram bot getBotToken(): " + botToken);
        return botToken;
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        log.info("Telegram bot has received a message ");

        if (update.hasMessage() && update.getMessage().hasText() && botServiceJSON.isJSON(update.getMessage().getText())){
            String response = botServiceCONSTRUCTOR.buildEntity(update.getMessage().getText());
            responseMessage(update.getMessage().getChatId(), response);
        } else if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            responseMessage(update.getMessage().getChatId(), defaultMessage.getGreeting());
        } else {
            responseMessage(update.getMessage().getChatId(), notValidMessage.getIncorrectMessage());
        }
    }

    private void responseMessage(Long chatId, String text){
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(chatId));
            sendMessage.setText(text);
            execute(sendMessage);
        }catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
