package com.example.user.dailytv.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.dailytv.R;

/**
 * Created by user on 2017-12-25.
 */


//이 어뎁터는 ExoPlayer2Activity 에서 네티서버로부터 들어오는 값에 대한 정보를
// SQLite데이터베이스를 통해 처리하기 위해 사용되는 어뎁터이다.

public class ChattingCusorAdapter extends CursorAdapter {


    public ChattingCusorAdapter(Context context, Cursor c) {
        super(context, c);
    }


    //커서가 가리키는 데이터를 보여줄 새로운 뷰를 리턴 => 즉 뷰를 객체화해서 전달해야함
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.chat_listview_item,parent,false);
        return v;
    }

    //return 된 뷰에서 아이템 뷰들을 할당하고 그 아이템들에게 값을 할당
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //final TextView nickname=view.findViewById(R.id.);
        final TextView nickname=(TextView)view.findViewById(R.id.nickname);
        final TextView text=(TextView)view.findViewById(R.id.text);


        final String nickname_=cursor.getString(cursor.getColumnIndex("nickname"));
        String text_=cursor.getString(cursor.getColumnIndex("text"));
        final String color=cursor.getString(cursor.getColumnIndex("color"));


        nickname.setVisibility(View.VISIBLE);
        nickname.setText(nickname_);
        nickname.setTextColor(Color.parseColor(color));
        text.setTextColor(Color.parseColor(color));


        // 1. 처음 4글자가 [알림]이라면 nickname에 해당하는 뷰를 보이지 않도록 수정한다.


        if(text_.length()>=4) {
            try {
                if (text_.substring(0, 4).equals("[알림]")) {
                    nickname.setVisibility(View.INVISIBLE);
                    //Log.e("ExoPlayer2Activity", "nickname감추기");
                    //Log.e("ExoPlayer2.Activity", text_.substring(0, 4) + "");
                }
                else if(text_.substring(0,4).equals("[추천]"))
                {
                    nickname.setVisibility(View.INVISIBLE);
                    text_=text_.substring(4,text_.length());
                }
            } catch (Exception e) {
                Log.e("[bindview]", "아마 substring 오류?" + e.getMessage());
            }
        }

        text.setText(text_);
    }
}