package com.example.evilsay.wechatson.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends BaseExpandableListAdapter {
    //传Object的意思的里面传什么值都可以的滴，比如你List集合里面是String的那就传Sting
    private List<Object> objects = new ArrayList<>();
    private Context context;
    //对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.from()来载入；
    private LayoutInflater inflater;
    //    给它一个构造方法
    public TestAdapter(List<Object> objects,Context context) {
        this.context = context;
        //传当前上下文的视图，不了解context的话可以去Google一下
        this.inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    //获得父列表项的数目
    @Override
    public int getGroupCount() {
        return 0;
    }
    //获得子列表项的数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }
    //拿到集合中第N个数据<个人理解>
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }
    //获取子列表项对应的Item
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }
    //获得父列表项的Id
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }
    //获得子列表项的Id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
    //我不知道是干嘛这个大家可以试一试把他改成true，缘分报bug!
    @Override
    public boolean hasStableIds() {
        return false;
    }
    // 父类列表的视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView;
        //加载视图参数解释--第一个传的父控件的视图<你需要自己创建一个>，第二个参数<使用自己layout中的控件参数>，第三个参数不使用默认控件参数
            convertView = inflater.inflate(0,parent,false);
            textView = convertView.findViewById(0);
        return convertView;
    }
    // 子类列表的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//   同上，不过子布局你要自己创建一个布局
        return null;
    }
    //是否能否触发事件，返回true则为可以响应点击
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
