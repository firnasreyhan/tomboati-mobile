package com.android.tomboati.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment {
    private ChatViewModel chatViewModel;
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerViewChat;
    private TextInputEditText textInputEditTextChat;
    private TextInputLayout textInputLayoutChat;
    private FloatingActionButton floatingActionButtonSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chatViewModel = ViewModelProviders.of(getActivity()).get(ChatViewModel.class);
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        recyclerViewChat = view.findViewById(R.id.recyclerViewChat);
        textInputEditTextChat = view.findViewById(R.id.textInputEditTextChat);
        textInputLayoutChat = view.findViewById(R.id.textInputLayoutChat);
        floatingActionButtonSend = view.findViewById(R.id.floatingActionButtonSend);

        if (AppPreference.getUser(getContext()) != null) {
            chatViewModel.getChat().observe(getActivity(), new Observer<ChatResponse>() {
                @Override
                public void onChanged(ChatResponse chatResponse) {
                    if (!chatResponse.isError()) {
                        if (!chatResponse.getData().isEmpty()) {
                            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            linearLayoutManager.setStackFromEnd(true);
                            chatAdapter = new ChatAdapter(chatResponse.getData());
                            recyclerViewChat.setLayoutManager(linearLayoutManager);
                            recyclerViewChat.setAdapter(chatAdapter);
                            recyclerViewChat.smoothScrollToPosition(chatAdapter.getItemCount() -1);
                        }
                    }
                }
            });

            floatingActionButtonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!textInputEditTextChat.getText().toString().isEmpty()) {
                        chatViewModel.sendChat(
                                textInputEditTextChat.getText().toString()
                        ).observe(getActivity(), new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                if (!baseResponse.isError()) {
//                                    ChatResponse.ChatModel model = new ChatResponse.ChatModel();
//                                    model.setMessage(textInputEditTextChat.getText().toString());
//                                    model.setIsAdmin(0);
//                                    model.setIsSeen(0);
//                                    chatAdapter.addData(model);
//                                    recyclerViewChat.scrollToPosition(chatAdapter.getItemCount() - 1);
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
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AppPreference.getUser(getContext()) != null) {
            chatViewModel.getChat().observe(getActivity(), new Observer<ChatResponse>() {
                @Override
                public void onChanged(ChatResponse chatResponse) {
                    if (!chatResponse.isError()) {
                        if (!chatResponse.getData().isEmpty()) {
                            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            linearLayoutManager.setStackFromEnd(true);
                            chatAdapter = new ChatAdapter(chatResponse.getData());
                            recyclerViewChat.setLayoutManager(linearLayoutManager);
                            recyclerViewChat.setAdapter(chatAdapter);
                            recyclerViewChat.smoothScrollToPosition(chatAdapter.getItemCount() -1);
                        }
                    }
                }
            });
        }
    }
}