package com.example.evilsay.wechatson.PLZ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.evilsay.wechatson.DAO.ChatDao;
import com.example.evilsay.wechatson.Model.ChatResult;
import com.example.evilsay.wechatson.Utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatBlz {
    private ChatDao chatDao = new ChatDao();
    public void loadFromDb(final Context context,boolean userCache, final String user, final String chat ,final Callback callback){
        @SuppressLint("StaticFieldLeak") AsyncTask<Boolean,Void,List<ChatResult>> asyncTask = new AsyncTask<Boolean, Void, List<ChatResult>>() {
            private Exception ex;
            @Override
            protected List<ChatResult> doInBackground(Boolean... booleans) {
                boolean checkNormal = booleans[0];
//                    选择数据加载模式
                List<ChatResult> chatResults = new ArrayList<>();
                if (checkNormal){
                    List<ChatResult> chatResults1 = chatDao.LoadFromLocal(context);
                    chatResults.addAll(chatResults1);
                }
//                网络缓存数据
                if (chatResults.isEmpty()){
                    try {
                        List<ChatResult> chatResults1 = loadFromNetWork(context,user,chat);
                        chatDao.LoadFromNetWork(context,chatResults1);
                        chatResults.addAll(chatResults1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        this.ex = e;
                    }
                }
                return chatResults;
            }
            @Override
            protected void onPostExecute(List<ChatResult> chatResults) {
                if (ex != null){
                    callback.UnSuccess(ex);
                }
                callback.OnSuccess(chatResults);
            }
        };
        asyncTask.execute(userCache);
    }
    private List<ChatResult> loadFromNetWork(Context context, String user, String chat) throws IOException {
        List<ChatResult> chatResults = new ArrayList<>();
        String content = HttpUtils.WeChatList(user,chat);
        if (content != null){
            chatResults = JsonAnalysis(content);
        }
        return chatResults;
    }
    private List<ChatResult> JsonAnalysis(String data){
        JsonParser jsonParser = new JsonParser();
        JsonArray  array      = jsonParser.parse(data).getAsJsonArray();
        List<ChatResult > chatResults = new ArrayList<>();
        Gson gson = new Gson();
        for (JsonElement element : array) {
             ChatResult chatResult = gson.fromJson(element,ChatResult.class);
             chatResults.add(chatResult);
        }
        return chatResults;
    }
    public  interface Callback{
        void OnSuccess(List<ChatResult> results);
        void UnSuccess(Exception ex);
    }
}
