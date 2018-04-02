package com.drag_expandable_list_view.src.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.drag_expandable_list_view.R;

/**
 * Created by benholmes on 3/31/18.
 */

public class ListItemView extends RelativeLayout {

    private Context ctx;
    private View rootView;
    private RelativeLayout lyt_parent;
    private TextView primaryText;
    private ImageView icon_left;


    public ListItemView(Context ctx){
        super(ctx);
        this.ctx = ctx;
        SharedConstructor(ctx);
    }


    public ListItemView(Context ctx, AttributeSet attrs){
        super(ctx, attrs);
        this.ctx = ctx;
        SharedConstructor(ctx);

        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.ListItemView);
        for(int i = 0; i < a.length(); i++){
            switch (a.getIndex(i)){
                case R.styleable.ListItemView_primary_text:
                    setPrimaryText(a.getString(i));
                    break;
                case R.styleable.ListItemView_icon_left:
                    setIconLeft(a.getResourceId(R.styleable.ListItemView_icon_left, 0));
                    break;
            }
        }

    }


    private void SharedConstructor(Context ctx){
        rootView = LayoutInflater.from(ctx).inflate(R.layout.list_item_view, this);
//        Edit mode is usually when they are using the drag and drop editor
        if(rootView != null && !isInEditMode()){
            primaryText = rootView.findViewById(R.id.primary_text);
            icon_left = rootView.findViewById(R.id.icon_left);
            setBackgroundColor(rootView);
            setPrimaryTextColor(ContextCompat.getColor(ctx, R.color.colorPrimary));
        }

    }

    public void setBackgroundColor(View view){
        view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorAccent));
    }

    public void setPrimaryTextColor(int color){
        primaryText.setTextColor(color);
    }


    public void setPrimaryText(String text){

        primaryText.setText(text);

    }

    public void setIconLeft(int resource){
        icon_left.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_down_arrow));
    }



}
