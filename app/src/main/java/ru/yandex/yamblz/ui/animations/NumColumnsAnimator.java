package ru.yandex.yamblz.ui.animations;

import android.animation.ValueAnimator;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class NumColumnsAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder,
                                 RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX,
                                 int toY) {
        final int durationMs = 650;
        final View itemView = oldHolder.itemView;

        final int width = itemView.getWidth();
        final int newWidth = width / 2;

        final ValueAnimator animator = ValueAnimator.ofInt(width, newWidth)
                .setDuration(durationMs);

        animator.addUpdateListener(animation -> {
            final int intermediateWidth = (int) animation.getAnimatedValue();

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = intermediateWidth;

            itemView.setLayoutParams(layoutParams);
        });

        animator.start();

        return true;
    }
}