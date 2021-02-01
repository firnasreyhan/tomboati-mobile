package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.ChatAdapter;
import com.android.tomboati.model.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment {
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerViewChat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        recyclerViewChat = view.findViewById(R.id.recyclerViewChat);

        List<ChatModel> list = new ArrayList<>();
        list.add(new ChatModel(
                "",
                "",
                "",
                "",
                "",
                true,
                true
        ));
        list.add(new ChatModel(
                "",
                "",
                "",
                "",
                "",
                true,
                false
        ));
        list.add(new ChatModel(
                "",
                "",
                "",
                "",
                "",
                true,
                true
        ));
        list.add(new ChatModel(
                "",
                "",
                "",
                "",
                "",
                false,
                false
        ));

        chatAdapter = new ChatAdapter(list);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewChat.setAdapter(chatAdapter);

        return view;
    }
}