package com.example.evilsay.wechatson.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.evilsay.wechatson.Model.AddressWeChat;
import com.example.evilsay.wechatson.R;
import com.example.evilsay.wechatson.Utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FragmentAddress2 extends android.support.v4.app.Fragment{
    ListView mListView;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),getData((List<AddressWeChat>) msg.obj),R.layout.item_paremt_chapter,new String[]{"name"},new int[]{R.id.text_view});
            mListView.setAdapter(simpleAdapter);
        }
    };
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_address,container,false);
        mListView=view.findViewById(R.id.listView);
        HttpUtils.httpUtils(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("sssssssssssssss");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str=response.body().string();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            JSONArray jsonArray=new JSONArray(str);
                            List<AddressWeChat> list=new ArrayList<AddressWeChat>();
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                AddressWeChat addressWeChat=new AddressWeChat();
                                addressWeChat.setName(jsonObject.getString("name"));
                                list.add(addressWeChat);
                            }
                            Message message=new Message();
                            message.obj=list;
                            mHandler.sendMessage(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        return view;
    }
    List<Map<String,String>> getData(List<AddressWeChat> list){
        Map<String,String> map = null;
        List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
        for (AddressWeChat addressWeChat:list){
            map=new HashMap<String,String>();
            map.put("name",addressWeChat.getName());
            list1.add(map);
        }
        return list1;
    }
}
