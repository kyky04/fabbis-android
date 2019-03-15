package id.metamorph.fabis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.metamorph.fabis.MainActivity;
import id.metamorph.fabis.R;

public class MenuDetailActivity extends AppCompatActivity {


    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.btn_data_pemain)
    LinearLayout btnDataPemain;
    @BindView(R.id.btn_penilaian)
    LinearLayout btnPenilaian;
    @BindView(R.id.btn_terbaik)
    LinearLayout btnTerbaik;
    @BindView(R.id.btn_hasil_seleksi)
    LinearLayout btnHasilSeleksi;
    @BindView(R.id.btn_center)
    LinearLayout btnCenter;
    @BindView(R.id.btn_semua)
    LinearLayout btnSemua;

    String gender = "";
    int jk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        jk = getIntent().getIntExtra("gender",0);
        tvGender.setText(gender);
        if (jk == 0) {
            tvGender.setText("Laki - Laki");
        } else {
            tvGender.setText("Wanita");
        }
    }

    @OnClick({R.id.tv_gender, R.id.btn_data_pemain, R.id.btn_penilaian, R.id.btn_terbaik, R.id.btn_hasil_seleksi})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_gender:
                break;
            case R.id.btn_data_pemain:
                intent = new Intent(MenuDetailActivity.this, PosisiActivity.class);
                intent.putExtra("gender", jk);
                startActivity(intent);
                break;
            case R.id.btn_penilaian:
                intent = new Intent(MenuDetailActivity.this, PemainPenialaianActivity.class);
                intent.putExtra("gender", jk);
                startActivity(intent);
                break;
            case R.id.btn_terbaik:
                intent = new Intent(MenuDetailActivity.this, PemainTerbaikActivity.class);
                intent.putExtra("gender", jk);
                startActivity(intent);
                break;
            case R.id.btn_hasil_seleksi:
                intent = new Intent(MenuDetailActivity.this, PemainActivity.class);
                intent.putExtra("gender", jk);
                intent.putExtra("seleksi", true);
                startActivity(intent);
                break;
        }
    }
}
