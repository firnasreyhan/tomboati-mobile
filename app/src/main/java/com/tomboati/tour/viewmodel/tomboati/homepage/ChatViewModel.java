package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.api.response.ChatResponse;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.repository.Repository;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ChatViewModel extends AndroidViewModel {
    private Repository repository;
    private Context context;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<ChatResponse> getChat() {
        return repository.getChat(PreferenceAkun.getAkun(context).getIdChatRoom());
    }

    public MutableLiveData<BaseResponse> sendChat(String message_) {
        RequestBody message = RequestBody.create(MediaType.parse("text/plain"), message_);
        RequestBody idChatRoom = RequestBody.create(MediaType.parse("text/plain"), PreferenceAkun.getAkun(context).getIdChatRoom());
        return repository.sendChat(message, idChatRoom, null);
    }
}
