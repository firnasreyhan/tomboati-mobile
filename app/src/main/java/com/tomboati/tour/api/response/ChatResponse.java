package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatResponse extends BaseResponse {
    @SerializedName("data")
    private List<ChatModel> data;

    public List<ChatModel> getData() {
        return data;
    }

    public static class ChatModel {
        @SerializedName("ID_CHAT")
        private String idChat;

        @SerializedName("ID_CHAT_ROOM")
        private String idChatRoom;

        @SerializedName("MESSAGE")
        private String message;

        @SerializedName("IMG")
        private String img;

        @SerializedName("CREATEDAT")
        private String createdAt;

        @SerializedName("ISSEEN")
        private int isSeen;

        @SerializedName("SEENAT")
        private String senAt;

        @SerializedName("ISADMIN")
        private int isAdmin;

        public String getIdChat() {
            return idChat;
        }

        public void setIdChat(String idChat) {
            this.idChat = idChat;
        }

        public String getIdChatRoom() {
            return idChatRoom;
        }

        public void setIdChatRoom(String idChatRoom) {
            this.idChatRoom = idChatRoom;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getIsSeen() {
            return isSeen;
        }

        public void setIsSeen(int isSeen) {
            this.isSeen = isSeen;
        }

        public String getSenAt() {
            return senAt;
        }

        public void setSenAt(String senAt) {
            this.senAt = senAt;
        }

        public int getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(int isAdmin) {
            this.isAdmin = isAdmin;
        }
    }
}
