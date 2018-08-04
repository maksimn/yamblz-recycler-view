package ru.yandex.yamblz.ui.animations;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

public class NumColumnsAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder,
                                 RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX,
                                 int toY) {
        return false;
    }
}