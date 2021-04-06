package com.android.tomboati.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.adapter.ChatAdapter;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.view.activity.ImageChatActivity;
import com.android.tomboati.viewmodel.ChatViewModel;
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

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

        if (AppPreference.getUser(view.getContext()) != null) {
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
                                    onResume();
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
        } else {
            linearLayoutYesSignIn.setVisibility(View.GONE);
            linearLayoutNoSignIn.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AppPreference.getUser(getActivity()) != null) {
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
        }
    }
}