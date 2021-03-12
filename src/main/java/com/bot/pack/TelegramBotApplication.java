package com.bot.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class TelegramBotApplication  extends TelegramLongPollingBot {

	public String getBotUsername(){

		return "Epic bot";
	}



	@Override
	public String getBotToken() {
		return "1662580550:AAHuRJYBSp9NePfmy13nTrrcuGk1DeBa7LQ";
	}

	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage() && update.getMessage().hasText()){
			SendMessage message = new SendMessage();
			message.setChatId(String.valueOf(update.getMessage().getChatId()));
			message.setText(update.getMessage().getText());

			try{
				execute(message);
			} catch (TelegramApiException e){
				e.printStackTrace();
			}
		}
	}
}
