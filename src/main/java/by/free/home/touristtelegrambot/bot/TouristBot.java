package by.free.home.touristtelegrambot.bot;

import by.free.home.touristtelegrambot.entity.City;
import by.free.home.touristtelegrambot.service.CityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
    @Value("${value.bot.name}")
    private String botName;
    @Value("${value.bot.token}")
    private String token;

    @Autowired
    private CityService cityService;

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        log.info("Telegram chat Id: "+chatId);
        String inputText = update.getMessage().getText();
        log.info("Telegram input text: "+inputText);
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        String answerToUser = (String) messageHandler(inputText.trim());
        log.info("Telegram answer to user :"+answerToUser);
        message.setText(answerToUser);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Problems with executing message!");
            exe.shutdown();
            e.printStackTrace();
        }
    }

    public String messageHandler(String inputText) {
        String answerToUser = "";
        String helpText = "My commands: \n"+
                "/start - I start to work.\n" +
                "/help - I tell you about me again, if you will forget! \n"+
                "/end - End of my work.\n"+
                "Write name of some city, and I tell you about it!";
        switch (inputText) {
            case "/start" :
                answerToUser = "Hello. I'am tourist bot, I help to know some info about cities! \n" +
                        helpText;
                log.info("Start case is working!");
                break;
            case "/end" :
                answerToUser = "Bye-bye! See you Again!";
                log.info("End case is working!");
                break;
            case "/help":
                answerToUser = helpText;
                log.info("Help case is working!");
                break;
            default:
                answerToUser = searchCityByTextInDatabase(inputText.toLowerCase());
                log.info("Default case: Request to database for city!");
        }
        return answerToUser;
    }

    public String searchCityByTextInDatabase(String inputText) {
        Optional<City> optionalCityByName = (Optional<City>) cityService.getOptionalCityByName(inputText);
        if (optionalCityByName.isPresent()) {
            log.info("City with name ["+inputText.toUpperCase()+"] has been founded!");
            return optionalCityByName.get().toString();
        } else {
            log.error("City with name ["+inputText.toUpperCase()+"] not found!");
            return "Ooops...I don't find anything by this word :(";
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

}
