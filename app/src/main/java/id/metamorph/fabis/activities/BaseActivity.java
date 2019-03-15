package id.metamorph.fabis.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import id.metamorph.fabis.R;
import id.metamorph.fabis.adapters.EmptyAdapter;
import id.metamorph.fabis.adapters.ProgressAdapter;
import id.metamorph.fabis.utils.DialogUtils;
import okhttp3.Response;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;

import org.json.JSONObject;

public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = "";
    private Toolbar mToolbar;
    JSONObject jsonObject;
    EmptyAdapter emptyAdapter;
    ProgressAdapter progressAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setupToolbar();
        bindViews();
        initView();
    }


     void initView() {
        emptyAdapter = new EmptyAdapter(this);
        progressAdapter = new ProgressAdapter(this, R.raw.loading_animation1);
    }



    public void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    public void showToast(String message) {
        DialogUtils.showToast(this, message);
    }

    public void showSnack(String message) {
        DialogUtils.showSnack(this, message);
    }

    public void showLog(Activity activity, String message) {
        Log.d(activity.getClass().getSimpleName(), message);
    }


    private void bindViews() {
        ButterKnife.bind(this);
    }

    @Nullable
    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setTitleToolbar(String title) {
        mToolbar.setTitle(title);
    }

    public void setTag(Class c) {
        TAG = c.getSimpleName();
    }

    public String getTAG() {
        return TAG;
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract int getLayoutId();

}
