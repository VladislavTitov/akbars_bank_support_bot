package com.dreamproduction;

import com.dreamproduction.neural.AnswerMapper;
import com.dreamproduction.neural.NeuralInitializator;
import com.dreamproduction.neural.NeuralRunner;
import com.dreamproduction.neural.QueryForNNParser;
import com.dreamproduction.utils.SpeechKitProvider;
import com.dreamproduction.utils.XMLParser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.File;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.Voice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public class AkBarsBankSupportBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new AkBarsBankSupportBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        NeuralInitializator.initNN();
    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/start") || message.getText().equals("/help")){
                initAndSendMessage(message, "Здравствуйте!\nВы можете отправлять свои вопросы " +
                        "в виде текстовых или голосовых сообщений.\n" +
                        "Говорите четко, иначе я могу вас не понять.");
                return;
            }

            String answer = AnswerMapper.getAnswer(NeuralRunner.startNN(QueryForNNParser.parse(message.getText().toLowerCase())));
            initAndSendMessage(message, answer);
        }
        if (message != null && message.getVoice() != null) {
            Voice voice = message.getVoice();
            System.out.println(voice.getMimeType());
            java.io.File voiceMessage;
            String xml = "";
            try {
                GetFile getFile = new GetFile();
                getFile.setFileId(voice.getFileId());
                String filepath = null;
                try {
                    // We execute the method using AbsSender::getFile method.
                    File file = getFile(getFile);
                    // We now have the file_path
                    filepath = file.getFilePath();
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                if (filepath != null) {
                    voiceMessage = downloadFile(filepath);
                    xml = "" + SpeechKitProvider.doPost(voiceMessage);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String answer = AnswerMapper.getAnswer(NeuralRunner.startNN(QueryForNNParser.parse(XMLParser.parseXML(xml))));
            initAndSendMessage(message, answer);
        }

    }

    private void initAndSendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setText(text);
        System.out.println(text);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "ak_bars_support_bot";
    }

    public String getBotToken() {
        return "341163437:AAGZBxYOEPJDCOUIIPLfYRXqUliflqoL6iA";
    }

}
