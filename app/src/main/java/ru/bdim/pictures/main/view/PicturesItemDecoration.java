package ru.bdim.pictures.main.view;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicturesItemDecoration extends RecyclerView.ItemDecoration {
    private int offset;

    public PicturesItemDecoration(int offset){
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
int position = parent.getChildAdapterPosition(view);
        if (position%2 == 0){
            outRect.left = offset;
            outRect.right = offset/2;
        } else {
            outRect.left = offset/2;
            outRect.right = offset;
        }
        outRect.top = offset/2;
        outRect.bottom = offset/2;
    }
}