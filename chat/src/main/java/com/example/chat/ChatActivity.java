package com.example.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class ChatActivity extends AppCompatActivity implements OnLogInResult {
    public int userID = -1;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ChatAdapter(new ArrayList<ChatMessage>());
        startService(new Intent(this, ChatService.class));
        LoginDialog dialog = new LoginDialog();
        dialog.setOnLogInResult(this);
        dialog.show(getSupportFragmentManager(), "log in");
    }

    /*----------------------------OnLogInResult-------------------------------*/
    @Override
    public void onLogedIn(int userId) {
        this.userID = userId;
        setContentView(R.layout.activity_chat);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.btn_sent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSendMessage();
            }
        });
    }

    /*--------------------------------------------------------------------------*/

    public void requestSendMessage(){
        EditText edtContent = findViewById(R.id.edt_content);
        Intent intent = new Intent(Constant.ACTION_SEND_MESSAGE);
        intent.putExtra(ChatService.ARG_CHAT_CONTENT,edtContent.getText());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
}

}
