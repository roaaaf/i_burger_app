package com.example.i_burger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

public class OrderItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public OrderItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (parent.getChildLayoutPosition(view) == 1 || parent.getChildLayoutPosition(view) == 0) {
            outRect.top = 0;
        } else {
            outRect.top =-space;
        }
    }
}