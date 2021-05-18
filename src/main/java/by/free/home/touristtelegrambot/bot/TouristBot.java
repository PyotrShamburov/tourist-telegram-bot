package by.free.home.touristtelegrambot.bot;

import by.free.home.touristtelegrambot.entity.City;
import by.free.home.touristtelegrambot.service.CityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Component
public class TouristBot extends TelegramLongPollingBot {
    private String botName = "tourist_help_bot";
    private String token = "1660590905:AAEtrtqTVzM96x36s_6BfEhNGfs0-M_iJqI";

    @Autowired
    private CityService cityService;

    @Override
    public void onClosing() {
        super.onClosing();
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        String answerToUser = (String) messageHandler(inputText);
        message.setText(answerToUser);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public String messageHandler(String inputText) {
        String answerToUser = "";
        switch (inputText) {
            case "/start" :
                answerToUser = "Hello. This is start message";
                break;
            case "/end" :
                answerToUser = "Bye-bye! See you Again!";
                break;
            case "/help":
                answerToUser = "Write name of city, and I tell you about it!";
                break;
            default:
                answerToUser = searchCityByTextInDatabase(inputText.toLowerCase());
        }
        return answerToUser;
    }

    public String searchCityByTextInDatabase(String inputText) {
        Optional<City> optionalCityByName = (Optional<City>) cityService.getOptionalCityByName(inputText);
        if (optionalCityByName.isPresent()) {
            return optionalCityByName.get().toString();
        } else {
            return "Ooops...I don't find anything by this word( Let's try again!";
        }
    }

}
