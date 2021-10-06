package com.android.tomboati.view.fragment.homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.adapter.ChatAdapter;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.view.activity.homepage.ImageChatActivity;
import com.android.tomboati.viewmodel.tomboati.homepage.ChatViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChatFragment extends Fragment {
    private ChatViewModel chatViewModel;
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerViewChat;
    private TextInputEditText textInputEditTextChat;
    private TextInputLayout textInputLayoutChat;
    private FloatingActionButton floatingActionButtonSend;
    private LinearLayout linearLayoutNoSignIn, linearLayoutYesSignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewChat = view.findViewById(R.id.recyclerViewChat);
        textInputEditTextChat = view.findViewById(R.id.textInputEditTextChat);
        textInputLayoutChat = view.findViewById(R.id.textInputLayoutChat);
        floatingActionButtonSend = view.findViewById(R.id.floatingActionButtonSend);
        linearLayoutNoSignIn = view.findViewById(R.id.linearLayoutNoSignIn);
        linearLayoutYesSignIn = view.findViewById(R.id.linearLayoutYesSignIn);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewChat.setLayoutManager(linearLayoutManager);
        recyclerViewChat.setNestedScrollingEnabled(false);
        recyclerViewChat.setHasFixedSize(true);

        linearLayoutYesSignIn.setVisibility(View.VISIBLE);
        linearLayoutNoSignIn.setVisibility(View.GONE);

        floatingActionButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textInputEditTextChat.getText().toString().isEmpty()) {
                    chatViewModel.sendChat(textInputEditTextChat.getText().toString()).observe(getActivity(), new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if (!baseResponse.isError()) {
                                textInputEditTextChat.getText().clear();
                                onStart();
                            }
                        }
                    });
                }
            }
        });

        textInputLayoutChat.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageChatActivity.class);
                intent.putExtra("CHAT", textInputEditTextChat.getText().toString());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        Log.d("TAG", "onResume: ");
        super.onResume();
    }

    @Override
    public void onStart() {
        chatViewModel.getChat().observe(this, new Observer<ChatResponse>() {
            @Override
            public void onChanged(ChatResponse chatResponse) {
                if (!chatResponse.isError()) {
                    if (!chatResponse.getData().isEmpty()) {
                        chatAdapter = new ChatAdapter(chatResponse.getData());
                        recyclerViewChat.setAdapter(chatAdapter);
                        recyclerViewChat.smoothScrollToPosition(chatAdapter.getItemCount() -1);
                    }
                }
            }
        });
        super.onStart();
    }
}