package ru.yandex.yamblz.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import ru.yandex.yamblz.R;

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
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new ContentAdapter());

        setNumColumnsClickListeners();
    }

    private void setNumColumnsClickListeners() {
        Button[] buttons = new Button[] { button1Col, button2Col, button3Col };
        Context context = getContext();

        for(int i = 0; i < buttons.length; i++) {
            final int numColumns = i + 1;

            buttons[i].setOnClickListener(v -> {
                rv.setLayoutManager(new GridLayoutManager(context, numColumns));
            });
        }
    }
}
