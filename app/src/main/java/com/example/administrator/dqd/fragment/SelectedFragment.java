package com.example.administrator.dqd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.dqd.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class SelectedFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.circle_selected_fragment, container, false);
        ListView listView;
        listView = new ListView(getContext());
        listView = view.findViewById(R.id.selected_listView);
        listView.setAdapter(new ListViewAdapter());

        return view;
    }
    class ListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 100;
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

            RoundedImageView post_head;
            TextView post_name;
            TextView post_content;
//            ImageView post_pic;

            View view;
            if(convertView==null){
                convertView =  View.inflate(getContext(),R.layout.item_post,null);
                post_head = new RoundedImageView(getContext());
                post_head = convertView.findViewById(R.id.post_head);
                post_name = new TextView(getContext());
                post_name = convertView.findViewById(R.id.post_name);
                post_content = new TextView(getContext());
                post_content = convertView.findViewById(R.id.post_content);
//                post_pic = new ImageView(getContext());
//                post_pic = convertView.findViewById(R.id.post_pic);

            }else{
                post_head = convertView.findViewById(R.id.post_head);
                post_name = convertView.findViewById(R.id.post_name);
                post_content = convertView.findViewById(R.id.post_content);
                //  post_pic = convertView.findViewById(R.id.post_pic);
            }
            int i = position % 6;
            post_head.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.head,null));
            post_name.setText(position+"一只老刺猬");
            post_content.setText(position+"【深夜闲聊】 如果你最好的朋友生意失败破产，向你借一万救急，你会愿意借给他吗？每晚10点来D站，睡前我们一起聊一聊");
            // post_pic.setImageDrawable(ResourcesCompat.getDrawable(getResources(),pictureId[i],null));

            return convertView;
        }
    }
}
