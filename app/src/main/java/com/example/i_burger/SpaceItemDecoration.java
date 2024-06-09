package com.example.i_burger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(){}
    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (parent.getChildLayoutPosition(view)==1){
            outRect.top =space;
        }
        else if ( parent.getChildLayoutPosition(view)==0){
            outRect.top =0;

        }
        else if ( parent.getChildLayoutPosition(view)==2){
            outRect.top = -space*3;
        }
        else {
            outRect.top = -space;

        }

    }
}