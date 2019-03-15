package id.metamorph.fabis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.metamorph.fabis.activities.MenuDetailActivity;
import id.metamorph.fabis.activities.PemainActivity;
import id.metamorph.fabis.activities.PosisiActivity;
import id.metamorph.fabis.data.Session;
import id.metamorph.fabis.utils.DialogUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_pemain_laki)
    LinearLayout btnPemainLaki;
    @BindView(R.id.btn_pemain_wanita)
    LinearLayout btnPemainWanita;
    @BindView(R.id.btn_pemain_hasil)
    LinearLayout btnPemainHasil;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        session = new Session(this);
    }

    @OnClick({R.id.btn_pemain_laki, R.id.btn_pemain_wanita, R.id.btn_pemain_hasil, R.id.btn_logout})
    public void onViewClicked(View view) {
        Intent intent = new Intent(MainActivity.this, PosisiActivity.class);
        switch (view.getId()) {
            case R.id.btn_pemain_laki:
                intent.putExtra("gender", 0);
                startActivity(intent);
                break;
            case R.id.btn_pemain_wanita:
                intent.putExtra("gender", 1);
                startActivity(intent);
                break;
            case R.id.btn_pemain_hasil:
                String[] strings = new String[]{"Laki - Laki", "Wanita"};
                DialogUtils.dialogArray(MainActivity.this, strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, PemainActivity.class);
                        if (which == 0) {
                            intent.putExtra("gender", 0);
                        } else {
                            intent.putExtra("gender", 1);
                        }
                        intent.putExtra("seleksi", true);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.btn_logout:
                session.logoutUser();
                finish();
                break;
        }
    }
}
