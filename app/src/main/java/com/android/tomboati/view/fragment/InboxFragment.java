package com.android.tomboati.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.adapter.ChatAdapter;
import com.android.tomboati.model.ChatModel;
import com.android.tomboati.view.activity.ImageChatActivity;
import com.android.tomboati.view.activity.MainActivity;
import com.android.tomboati.view.activity.SignUpActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class InboxFragment extends Fragment {
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerViewChat;
    private TextInputEditText textInputEditTextChat;
    private TextInputLayout textInputLayoutChat;
    private FloatingActionButton floatingActionButtonSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        recyclerViewChat = view.findViewById(R.id.recyclerViewChat);
        textInputEditTextChat = view.findViewById(R.id.textInputEditTextChat);
        textInputLayoutChat = view.findViewById(R.id.textInputLayoutChat);
        floatingActionButtonSend = view.findViewById(R.id.floatingActionButtonSend);

        List<ChatModel> list = new ArrayList<>();
        list.add(new ChatModel(
                "",
                "",
                "Admin",
                "",
                "",
                true,
                true
        ));
        list.add(new ChatModel(
                "",
                "",
                "Pengguna",
                "",
                "",
                true,
                false
        ));
        list.add(new ChatModel(
                "",
                "",
                "Admin",
                "",
                "",
                true,
                true
        ));
        list.add(new ChatModel(
                "",
                "",
                "Pengguna",
                "",
                "",
                false,
                false
        ));

        chatAdapter = new ChatAdapter(list);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewChat.setAdapter(chatAdapter);

        recyclerViewChat.scrollToPosition(list.size() - 1);

        floatingActionButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textInputEditTextChat.getText().toString().isEmpty()) {
                    chatAdapter.addData(
                            new ChatModel(
                                    "",
                                    "",
                                    textInputEditTextChat.getText().toString(),
                                    "",
                                    "",
                                    false,
                                    false
                            )
                    );
                    recyclerViewChat.scrollToPosition(list.size() - 1);
                    textInputEditTextChat.getText().clear();
                }
            }
        });

        textInputLayoutChat.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ImageChatActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}