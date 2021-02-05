package com.android.tomboati.adapter;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.ChatResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatResponse.ChatModel> list;

    public ChatAdapter(List<ChatResponse.ChatModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getIsAdmin() == 1) {
            Log.e("isAdmin", "true");
            holder.cardViewOutgoing.setVisibility(View.GONE);
            holder.cardViewIncoming.setVisibility(View.VISIBLE);

            holder.textViewMessageIncoming.setText(list.get(position).getMessage());

            if (list.get(position).getImg() != null) {
                holder.imageViewIncoming.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView.getContext())
                        .load(list.get(position).getImg())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true)
                        .dontAnimate()
                        .dontTransform()
                        .priority(Priority.IMMEDIATE)
                        .encodeFormat(Bitmap.CompressFormat.PNG)
                        .format(DecodeFormat.DEFAULT)
                        .placeholder(R.drawable.ic_logo)
                        .into(holder.imageViewIncoming);
                String nmyFormat = "dd/MM/yyyy - hh:mm"; //In which you need put here
                SimpleDateFormat nsdf = new SimpleDateFormat(nmyFormat, new Locale("id", "ID"));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                try {
                    Date date = format.parse(list.get(position).getCreatedAt());
                    holder.textViewDateIncoming.setText(nsdf.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else {
            holder.cardViewOutgoing.setVisibility(View.VISIBLE);
            holder.cardViewIncoming.setVisibility(View.GONE);

            holder.textViewMessageOutgoing.setText(list.get(position).getMessage());

            if (list.get(position).getImg() != null) {
                holder.imageViewOutgoing.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView.getContext())
                        .load(list.get(position).getImg())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true)
                        .dontAnimate()
                        .dontTransform()
                        .priority(Priority.IMMEDIATE)
                        .encodeFormat(Bitmap.CompressFormat.PNG)
                        .format(DecodeFormat.DEFAULT)
                        .placeholder(R.drawable.ic_logo)
                        .into(holder.imageViewOutgoing);
            }

            String nmyFormat = "dd/MM/yyyy - hh:mm"; //In which you need put here
            SimpleDateFormat nsdf = new SimpleDateFormat(nmyFormat, new Locale("id", "ID"));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
            try {
                Date date = format.parse(list.get(position).getCreatedAt());
                holder.textViewDateOutgoing.setText(nsdf.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (list.get(position).getIsSeen() == 1) {
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

    public void addData(ChatResponse.ChatModel model) {
        list.add(model);
        notifyDataSetChanged();
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
