package ru.yandex.yamblz.ui.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class NumColumnsAnimator extends RecyclerView.ItemAnimator {
    private static int mNumColumns;
    private RecyclerView recyclerView;

    public NumColumnsAnimator(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setNumColumns(int n) {
        mNumColumns = n;
    }

    public int getNumColumns() {
        return mNumColumns;
    }
    
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
        final ArgbEvaluator colorEvaluator = new ArgbEvaluator();
        final int DURATION = 900;
        TextView itemView = (TextView) oldHolder.itemView;

        final int backgroundColor = ((ColorDrawable) itemView.getBackground()).getColor();
        final int textColor = itemView.getCurrentTextColor();
        ObjectAnimator bgrToWhite = ObjectAnimator.ofInt(itemView, "backgroundColor",
                backgroundColor, Color.WHITE);
        bgrToWhite.setEvaluator(colorEvaluator);

        ObjectAnimator textColorToWhite = ObjectAnimator.ofInt(itemView, "textColor", textColor,
                Color.WHITE);
        textColorToWhite.setEvaluator(colorEvaluator);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(bgrToWhite, textColorToWhite);
        set.setDuration(DURATION);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                dispatchAnimationFinished(newHolder);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(
                        recyclerView.getContext(), getNumColumns());
                itemView.setTextColor(textColor);
                itemView.setBackgroundColor(backgroundColor);
                recyclerView.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
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