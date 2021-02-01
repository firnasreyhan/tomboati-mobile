package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.ChatModel;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatModel> list;

    public ChatAdapter(List<ChatModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).isAdmin()) {
            holder.cardViewOutgoing.setVisibility(View.GONE);
            holder.cardViewIncoming.setVisibility(View.VISIBLE);
        } else {
            holder.cardViewOutgoing.setVisibility(View.VISIBLE);
            holder.cardViewIncoming.setVisibility(View.GONE);

            if (list.get(position).isSeen()) {
                holder.imageViewIsSeenOutgoing.setImageResource(R.drawable.ic_check_read);
            } else {
                holder.imageViewIsSeenOutgoing.setImageResource(R.drawable.ic_check_sent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardViewOutgoing, cardViewIncoming;
        private ImageView imageViewOutgoing, imageViewIncoming, imageViewIsSeenOutgoing;
        private TextView textViewMessageOutgoing, textViewMessageIncoming, textViewDateOutgoing, textViewDateIncoming;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewOutgoing = itemView.findViewById(R.id.cardViewOutgoing);
            cardViewIncoming = itemView.findViewById(R.id.cardViewIncoming);
            imageViewOutgoing = itemView.findViewById(R.id.imageViewOutgoing);
            imageViewIncoming = itemView.findViewById(R.id.imageViewIncoming);
            imageViewIsSeenOutgoing = itemView.findViewById(R.id.imageViewIsSeenOutgoing);
            textViewMessageOutgoing = itemView.findViewById(R.id.textViewMessageOutgoing);
            textViewMessageIncoming = itemView.findViewById(R.id.textViewMessageIncoming);
            textViewDateOutgoing = itemView.findViewById(R.id.textViewDateOutgoing);
            textViewDateIncoming = itemView.findViewById(R.id.textViewDateIncoming);
        }
    }
}
