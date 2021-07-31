package com.example.woddy.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.woddy.R;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener{

        private Context context;
        private List list;

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
        }

        class ViewHolder {
            public TextView tv_name;
            public ImageView iv_img;
        }

        public AlbumAdapter(Context context, ArrayList list){
            super(context, 0, list);
            this.context = context;
            this.list = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;

            if (convertView == null){
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                convertView = layoutInflater.inflate(R.layout.album_row_item, parent, false);
            }

            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.iv_img = (ImageView) convertView.findViewById(R.id.imageView);

            final AlbumBoardFragment.ImgPost imgPost = (AlbumBoardFragment.ImgPost) list.get(position);
            viewHolder.tv_name.setText(imgPost.getText());
            Glide
                    .with(context)
                    .load(imgPost.getImg_url())
                    .centerCrop()
                    .apply(new RequestOptions().override(250, 350))
                    .into(viewHolder.iv_img);
            viewHolder.tv_name.setTag(imgPost.getText());


//        //아이템 클릭 방법2 - 클릭시 아이템 반전 효과가 안 먹힘
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, " " + actor.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

            //Return the completed view to render on screen
            return convertView;
        }
    }