package id.metamorph.fabis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.metamorph.fabis.R;
import id.metamorph.fabis.fragments.FragmentInputPemain;
import id.metamorph.fabis.utils.ActivityUtils;

public class PosisiActivity extends AppCompatActivity {


    @BindView(R.id.btn_point)
    LinearLayout btnPoint;
    @BindView(R.id.btn_shooting)
    LinearLayout btnShooting;
    @BindView(R.id.btn_power)
    LinearLayout btnPower;
    @BindView(R.id.btn_strong)
    LinearLayout btnStrong;
    @BindView(R.id.btn_center)
    LinearLayout btnCenter;
    @BindView(R.id.btn_semua)
    LinearLayout btnSemua;

    int gender = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posisi);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        gender = getIntent().getIntExtra("gender",0);

    }


    @OnClick({R.id.btn_point, R.id.btn_shooting, R.id.btn_power, R.id.btn_strong, R.id.btn_center, R.id.btn_semua, R.id.fab_add})
    public void onViewClicked(View view) {
        Intent intent = new Intent(PosisiActivity.this, PemainActivity.class);
        intent.putExtra("gender",gender);
        switch (view.getId()) {
            case R.id.btn_point:
                intent.putExtra("posisi", "PG");
                startActivity(intent);
                break;
            case R.id.btn_shooting:
                intent.putExtra("posisi", "SG");
                startActivity(intent);
                break;
            case R.id.btn_power:
                intent.putExtra("posisi", "PF");
                startActivity(intent);
                break;
            case R.id.btn_strong:
                intent.putExtra("posisi", "SF");
                startActivity(intent);
                break;
            case R.id.btn_center:
                intent.putExtra("posisi", "C");
                startActivity(intent);
                break;
            case R.id.btn_semua:
                startActivity(intent);
                break;
            case R.id.fab_add:
                FragmentInputPemain fragment = new FragmentInputPemain();
                ActivityUtils.addFragment(PosisiActivity.this, android.R.id.content, fragment);
                fragment.setListener(new FragmentInputPemain.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentInteraction() {

                    }
                });
                break;
        }
    }
}
