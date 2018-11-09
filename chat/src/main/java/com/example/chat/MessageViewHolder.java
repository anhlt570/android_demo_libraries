package com.example.chat;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    private ChatMessage message;

    private TextView tvContent;

    public MessageViewHolder(View itemView) {
        super(itemView);
        tvContent = itemView.findViewById(R.id.tv_msg_content);

    }

    public void onBind(ChatMessage message) {
        this.message = message;
        switch (message.getType()) {
            case Constant.MSG_TYPE_SENT:
                tvContent.setBackgroundResource(R.drawable.bg_msg_sent);
                tvContent.setTextColor(Color.WHITE);
                break;
            case Constant.MSG_TYPE_RECEIVED:
                tvContent.setBackgroundResource(R.drawable.bg_msg_received);
                tvContent.setTextColor(Color.BLACK);
                break;
        }
    }
}
