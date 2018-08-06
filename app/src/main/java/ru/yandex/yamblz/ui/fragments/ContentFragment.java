package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.animations.NumColumnsAnimator;

public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.button1Col)
    Button button1Col;
    @BindView(R.id.button2Col)
    Button button2Col;
    @BindView(R.id.button3Col)
    Button button3Col;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ContentAdapter adapter = new ContentAdapter();
        final ItemTouchHelper ith = createItemTouchHelper(adapter);

        ith.attachToRecyclerView(rv);
        GridLayoutManager glm = new GridLayoutManager(getContext(), 1);
        rv.setLayoutManager(glm);
        rv.setAdapter(adapter);
        rv.setItemAnimator(new NumColumnsAnimator(rv));
        setNumColumnsClickListeners();

        rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView _rv, MotionEvent e) {
                NumColumnsAnimator animator = (NumColumnsAnimator) rv.getItemAnimator();
                return animator.isRunning();
            }
        });
    }

    private void setNumColumnsClickListeners() {
        final Button[] buttons = new Button[] { button1Col, button2Col, button3Col };

        for(int i = 0; i < buttons.length; i++) {
            final int numColumns = i + 1;
            final NumColumnsAnimator animator = (NumColumnsAnimator) rv.getItemAnimator();

            buttons[i].setOnClickListener(v -> {
                if (!animator.isRunning()) {
                    final ContentAdapter adapter = (ContentAdapter) rv.getAdapter();
                    final GridLayoutManager layoutManager = (GridLayoutManager)
                            rv.getLayoutManager();
                    animator.setNumColumns(numColumns);
                    final int start = layoutManager.findFirstVisibleItemPosition();
                    final int end = layoutManager.findLastVisibleItemPosition();
                    adapter.notifyItemRangeChanged(start, end - start + 1);
                }
            });
        }
    }

    private ItemTouchHelper createItemTouchHelper(ContentAdapter adapter) {
        final ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.LEFT;
                int swipeFlags = ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.onItemDismiss(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }
        });

        return ith;
    }
}