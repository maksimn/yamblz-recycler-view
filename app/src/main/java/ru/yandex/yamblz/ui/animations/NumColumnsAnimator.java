package ru.yandex.yamblz.ui.animations;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class NumColumnsAnimator extends RecyclerView.ItemAnimator {
    ArgbEvaluator mColorEvaluator = new ArgbEvaluator();

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
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder,
                                 @NonNull RecyclerView.ViewHolder newHolder,
                                 @NonNull ItemHolderInfo preLayoutInfo,
                                 @NonNull ItemHolderInfo postLayoutInfo) {
        final int DURATION = 900;
        TextView itemView = (TextView) oldHolder.itemView;

        ObjectAnimator bgrToWhite = ObjectAnimator.ofInt(itemView, "backgroundColor",
                ((ColorDrawable) itemView.getBackground()).getColor(), Color.WHITE);
        bgrToWhite.setEvaluator(mColorEvaluator);

        ObjectAnimator textColorToWhite = ObjectAnimator.ofInt(itemView, "textColor", Color.DKGRAY,
                Color.WHITE);
        textColorToWhite.setEvaluator(mColorEvaluator);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(bgrToWhite, textColorToWhite);
        set.setDuration(DURATION);
        set.start();

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