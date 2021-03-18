package com.bot.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class TelegramBotApplication  extends TelegramLongPollingBot {

	/**
	 * Method for receiving messages.
	 * @param update Contains a message from the user.
	 */
	@Override
	public void onUpdateReceived(Update update) {
		String message = update.getMessage().getText();
		processInput(update.getMessage().getChatId().toString(), message);
	}


	/**
	 * Method for creating a message and sending it.
	 * @param chatId chat id
	 * @param s The String that you want to send as a message.
	 */
	public synchronized void sendMsg(String chatId, String s, boolean withButtons, List<String> Buttons) {

		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(chatId);
		sendMessage.setText(s);
		try {
			execute(sendMessage);
			setInline();
			if (withButtons) {
				setButtons(sendMessage, Buttons);
			}
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}


	// Default sendMsg with no buttons
	public synchronized void sendMsg(String chatId, String s){
		List<String> list = new ArrayList<>();
		sendMsg(chatId, s, false, list);
	}


	public synchronized  void processInput(String chatId, String s){

		if(s.contains("help")){
			List<String> buttons = new ArrayList<>();
			buttons.add("bitch");
			sendMsg(chatId, "What do you require help with?", true, buttons);
		}

		else{
			sendMsg(chatId, s);
		}
	}


	private void setInline() {
		List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
		List<InlineKeyboardButton> buttons1 = new ArrayList<>();

		InlineKeyboardButton button = new InlineKeyboardButton();
		button.setCallbackData("17");
		button.setText("Bitch");

		buttons1.add(button);
		buttons.add(buttons1);

		InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
		markupKeyboard.setKeyboard(buttons);
	}


	public synchronized void setButtons(SendMessage sendMessage, List<String> buttons) {

		// Create a keyboard
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		sendMessage.setReplyMarkup(replyKeyboardMarkup);
		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(true);

		// Create a list of keyboard rows
		List<KeyboardRow> keyboard = new ArrayList<>();

		// Add all buttons to keyboard
		for(String button : buttons){
			KeyboardRow keyRow = new KeyboardRow();
			keyRow.add(button);
			keyboard.add(keyRow);
		}

		// and assign this list to our keyboard
		replyKeyboardMarkup.setKeyboard(keyboard);
	}











	/**
	 * This method returns the bot's name, which was specified during registration.
	 * @return bot name
	 */
	@Override
	public String getBotUsername() {
		return "qepic_bot";
	}


	@Override
	public String getBotToken() {
		return "1662580550:AAHuRJYBSp9NePfmy13nTrrcuGk1DeBa7LQ";
	}
}
