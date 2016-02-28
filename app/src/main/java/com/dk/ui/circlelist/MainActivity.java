package com.dk.ui.circlelist;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class MainActivity extends Activity {

    private ListView lv;
    private int[] images = new int[] { R.drawable.p1, R.drawable.p2, R.drawable.p3 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());
        lv.setClipToPadding(false);
        lv.setClipChildren(false);
//        try {
//            changeGroupFlag(lv);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        lv.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (int i = 0; i < lv.getChildCount(); i++) {
                    lv.getChildAt(i).invalidate();
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 9999;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                MatrixView m = (MatrixView)LayoutInflater.from(MainActivity.this).inflate(R.layout.view_list_item, null);
                m.setParentHeight(lv.getHeight());
                convertView = m;
            }
            ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
            imageView.setImageResource(images[position % images.length]);

            return convertView;
        }

    }

    public void changeGroupFlag(Object obj) throws Exception
    {
        Field[] f = obj.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredFields(); // 获得成员映射数组
        for (Field tem : f) 
        {
            if (tem.getName().equals("mGroupFlags")) {
                tem.setAccessible(true);
                Integer mGroupFlags = (Integer)tem.get(obj); 
                int newGroupFlags = mGroupFlags & 0xfffff8;
                tem.set(obj, newGroupFlags);
            }
        }
    }
}
