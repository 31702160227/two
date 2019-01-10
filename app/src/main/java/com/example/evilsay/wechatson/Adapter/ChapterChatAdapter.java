package com.example.evilsay.wechatson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evilsay.wechatson.Beans.ChapterChat;
import com.example.evilsay.wechatson.Beans.ChapterChatItem;
import com.example.evilsay.wechatson.R;

import java.util.List;

public class ChapterChatAdapter extends BaseExpandableListAdapter {
    private List<ChapterChat> chapterChats;
    private Context context;
    private LayoutInflater layoutInflater;
    public ChapterChatAdapter(List<ChapterChat> chapterChats, Context context) {
        this.chapterChats = chapterChats;
        this.context = context;
//        传入当前上下文的布局
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return chapterChats.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chapterChats.get(groupPosition).getChapterChatItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return chapterChats.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chapterChats.get(childPosition).getChapterChatItems().size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ChapterViewHolder chapterViewHolder = null;
        if (convertView == null){
//            完成布局
            convertView = layoutInflater.inflate(R.layout.parent_food,parent,false);
            chapterViewHolder = new ChapterViewHolder();
            chapterViewHolder.textView = convertView.findViewById(R.id.parent_text);
            chapterViewHolder.imageView= convertView.findViewById(R.id.img);
            convertView.setTag(chapterViewHolder);
        }else{
            chapterViewHolder = (ChapterViewHolder) convertView.getTag();
        }
        ChapterChat chapterChat  = chapterChats.get(groupPosition);
        chapterViewHolder.textView.setText(chapterChat.getName());
        chapterViewHolder.imageView.setImageResource(R.drawable.img_exb);
        chapterViewHolder.imageView.setSelected(isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null){
//            完成子布局
            convertView = layoutInflater.inflate(R.layout.item_child_chapter,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.textView = convertView.findViewById(R.id.id_text_view);
//            childViewHolder.imageView = convertView.findViewById(R.id.iv_user_head);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ChapterChatItem chapterChatItem = chapterChats.get(groupPosition).getChapterChatItems().get(childPosition);
        childViewHolder.textView.setText(chapterChatItem.getName());
//        childViewHolder.imageView.setImageResource(R.mipmap.addressbook);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }
    class ChapterViewHolder {
        TextView textView;
        ImageView imageView;

    }
    class ChildViewHolder {
        TextView textView;
    }
}
