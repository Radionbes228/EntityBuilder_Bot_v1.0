package radion.app.bot_v1_0.service;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import radion.app.bot_v1_0.entity.Bot;
import radion.app.bot_v1_0.valid.NotValidMessage;

import java.io.File;


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
        SetChatPhoto setChatPhoto = new SetChatPhoto();
        InputFile inputFile = new InputFile(new File("templates/TelegramPhoto.jpg"));
        setChatPhoto.setPhoto(inputFile);


        log.info("Telegram bot has received a message ");
        if (update.hasMessage() && update.getMessage().hasText() && botServiceJSON.isJSON(update.getMessage().getText())){
            StringBuilder stringBuilder = botServiceCONSTRUCTOR.buildEntity(
                    botServiceJSON.getAllForeignKey(update.getMessage().getText())
            );
            sendMessage(update.getMessage().getChatId(), stringBuilder.toString());

        }else {
            sendMessage(update.getMessage().getChatId(), notValidMessage.getIncorrectMessage());
        }
    }

    private void sendMessage(Long chatId, String text){
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
