package ru.yandex.yamblz.ui.animations;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class NumColumnsAnimator extends RecyclerView.ItemAnimator {

    @Override
    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
        return false;
    }

    @Override
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        return false;
    }

    @Override
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        return false;
    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        final int ANIMATION_DURATION = 1000;
        final View itemView = oldHolder.itemView;

        final int width = itemView.getWidth();
        final int newWidth = width / 2;

        final ValueAnimator animator = ValueAnimator.ofInt(width, newWidth)
                .setDuration(ANIMATION_DURATION);

        animator.addUpdateListener(animation -> {
            final int intermediateWidth = (int) animation.getAnimatedValue();

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = intermediateWidth;

            itemView.setLayoutParams(layoutParams);
        });

        animator.start();

        return true;
    }

    @Override
    public void runPendingAnimations() {

    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}