package id.metamorph.fabis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import id.metamorph.fabis.MainActivity;
import id.metamorph.fabis.MainPelatihActivity;
import id.metamorph.fabis.R;
import id.metamorph.fabis.data.Session;

public class SplashActivity extends AppCompatActivity {
    private static final int TIME = 3000;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        session = new Session(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openMain();
            }
        }, TIME);
    }

    void openMain() {

        if (session.isLoggedIn()) {
            Intent i = null;
            if (session.getUser().getData().getStatus().equals("0")) {
                i = new Intent(this, MainActivity.class);
            } else if (session.getUser().getData().getStatus().equals("1")) {
                i = new Intent(this, MainPelatihActivity.class);
            }
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }

    }
}
