package bot.service.keyboards.builders;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardMarkupBuilder {

    private Long chat_id;
    private String text;

    private List<KeyboardRow> keyboard = new ArrayList<>();
    private KeyboardRow row;

    private ReplyKeyboardMarkupBuilder(){}

    private void setChatId(Long chat_id) {
        this.chat_id = chat_id;
    }

    public ReplyKeyboardMarkupBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public static ReplyKeyboardMarkupBuilder create() {
        return new ReplyKeyboardMarkupBuilder();
    }

    public static ReplyKeyboardMarkupBuilder create(Long chatId) {
        ReplyKeyboardMarkupBuilder builder = new ReplyKeyboardMarkupBuilder();
        builder.setChatId(chatId);
        return builder;
    }
    public ReplyKeyboardMarkupBuilder row() {
        this.row = new KeyboardRow();
        return this;
    }

    public ReplyKeyboardMarkupBuilder button(String text) {
        row.add(text);
        return this;
    }

    public ReplyKeyboardMarkupBuilder endRow() {
        this.keyboard.add(this.row);
        this.row = null;
        return this;
    }

    public SendMessage build() {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id).setText(text);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true)
                .setResizeKeyboard(true)
                .setOneTimeKeyboard(false)
                .setKeyboard(keyboard);

        message.setReplyMarkup(keyboardMarkup);
        return message;
    }
}