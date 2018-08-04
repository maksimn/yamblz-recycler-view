package ru.yandex.yamblz.ui.animations;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

public class CustomizedGridLayoutManager extends GridLayoutManager {
    public CustomizedGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }
}
