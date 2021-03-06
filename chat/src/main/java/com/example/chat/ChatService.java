package com.example.chat;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.JsonObject;

import java.net.URISyntaxException;
import java.util.Arrays;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class ChatService extends Service {
    private final String TAG = "ChatService";
    private Socket socket;
    public static final String ARG_CHAT_CONTENT = "chat.content";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initSocket();
        registerBroadcastManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    private void initSocket(){
        try {
            socket = IO.socket("http://192.168.12.68:3000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "call-Connect: "+ Arrays.toString(args));
                    socket.emit("chat","Hello");
                    //socket.disconnect();
                }
            }).on("chat", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "call-chat: "+Arrays.toString(args));
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "call-Disconnect: "+ Arrays.toString(args));
                }
            });
            socket.connect();
            Log.d(TAG, "initSocket: Done - "+ socket);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void registerBroadcastManager(){
        IntentFilter intentFilter = new IntentFilter(Constant.ACTION_SEND_MESSAGE);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(Constant.ACTION_SEND_MESSAGE.equals(intent.getAction())){
                    sendMessage(intent.getStringExtra(ARG_CHAT_CONTENT));
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,intentFilter);
        Log.d(TAG, "registerBroadcastManager: Done");
    }

    private void sendMessage(String content){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", content);
        socket.emit("connection", jsonObject);
    }
}
