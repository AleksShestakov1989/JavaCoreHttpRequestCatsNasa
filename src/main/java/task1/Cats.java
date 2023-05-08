package task1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cats {
    private String id;// уникальный идентификатор записи
    private String text;//  сообщение
    private String type; // тип животного
    private String user; // имя пользователя
    private int upvotes; // голоса

    public Cats(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") int upvotes
    ) {
        this.id = id;
        this.upvotes = upvotes;
        this.user = user;
        this.type = type;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "task1.Cats" +
                "\n id = " + id +
                "\n text = " + text +
                "\n type = " + type +
                "\n user = " + user +
                "\n upvotes = " + upvotes+
                "\n";
    }
}
