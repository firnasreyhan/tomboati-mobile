package com.android.tomboati.model;

public class ChatModel {
    private String id_chat;
    private String id_chat_room;
    private String message;
    private String img;
    private String createdAt;
    private boolean isSeen;
    private boolean isAdmin;

    public ChatModel(String id_chat, String id_chat_room, String message, String img, String createdAt, boolean isSeen, boolean isAdmin) {
        this.id_chat = id_chat;
        this.id_chat_room = id_chat_room;
        this.message = message;
        this.img = img;
        this.createdAt = createdAt;
        this.isSeen = isSeen;
        this.isAdmin = isAdmin;
    }

    public String getId_chat() {
        return id_chat;
    }

    public String getId_chat_room() {
        return id_chat_room;
    }

    public String getMessage() {
        return message;
    }

    public String getImg() {
        return img;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
