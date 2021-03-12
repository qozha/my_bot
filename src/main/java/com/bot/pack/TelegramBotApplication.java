package com.bot.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramBotApplication  extends TelegramLongPollingBot{

	public String getBotUsername(){}

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);
	}

}
