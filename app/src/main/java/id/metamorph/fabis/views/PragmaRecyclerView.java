package id.metamorph.fabis.views;

import android.content.Context;

import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.metamorph.fabis.adapters.EmptyAdapter;
import id.metamorph.fabis.adapters.ProgressAdapter;

public class PragmaRecyclerView extends RecyclerView {
    Context context;
    public PragmaRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PragmaRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void vertical(){
        this.setLayoutManager(new LinearLayoutManager(context));
    }

    public void showEmpty() {
        EmptyAdapter adapter = new EmptyAdapter(context);
        this.setAdapter(adapter);
    }

    public void showProgress() {
        ProgressAdapter adapter = new ProgressAdapter(context);
        this.setAdapter(adapter);
    }

    public void showProgress(int type) {
        ProgressAdapter adapter = new ProgressAdapter(context,type);
        this.setAdapter(adapter);
    }
}
