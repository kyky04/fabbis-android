package id.metamorph.fabis.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.metamorph.fabis.R;
import id.metamorph.fabis.adapters.recycler.PemainAdapter;
import id.metamorph.fabis.algorithm.Topsis;
import id.metamorph.fabis.data.Session;
import id.metamorph.fabis.fragments.FragmentFormasi;
import id.metamorph.fabis.fragments.FragmentInputPemain;
import id.metamorph.fabis.models.pemain.DataItemPemain;
import id.metamorph.fabis.models.pemain.PemainResponse;
import id.metamorph.fabis.utils.ActivityUtils;

import static id.metamorph.fabis.data.Contans.PEMAIN;

public class PemainHasilActivity extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private ProgressDialog progressDialog;

    PemainAdapter adapter;
    String posisi = "";
    int gender = 0;
    boolean seleksi = false;

    Session session;
    private List<DataItemPemain> listPemain = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemain_hasil);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        session = new Session(this);
        posisi = getIntent().getStringExtra("posisi");
        gender = getIntent().getIntExtra("gender", 0);
        seleksi = getIntent().getBooleanExtra("seleksi", false);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
                load();
            }
        });

        adapter = new PemainAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new PemainAdapter.OnItemClickListener() {
            @Override
            public void onClick(DataItemPemain item) {
                FragmentInputPemain fragment = FragmentInputPemain.newInstance(item);
                ActivityUtils.addFragment(PemainHasilActivity.this, android.R.id.content, fragment);
                fragment.setListener(new FragmentInputPemain.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentInteraction() {
                        load();
                    }
                });
            }
        });


        load();
    }

    @OnClick(R.id.btn_formasi)
    public void onViewClicked() {
        FragmentFormasi fragment = FragmentFormasi.newInstance(listPemain);
        ActivityUtils.addFragment(PemainHasilActivity.this, android.R.id.content, fragment);

    }

    public void load() {
        showProgress();
        ANRequest.GetRequestBuilder getRequestBuilder = new ANRequest.GetRequestBuilder(PEMAIN);
        if (posisi != null) {
            getRequestBuilder.addQueryParameter("posisi", posisi);
        }
        getRequestBuilder.addQueryParameter("gender", String.valueOf(gender));
        if(seleksi){
            getRequestBuilder.addQueryParameter("terpilih", String.valueOf(1));
        }
        getRequestBuilder.build()
                .getAsObject(PemainResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closeProgress();
                        if (response instanceof PemainResponse) {
                            if (((PemainResponse) response).isStatus()) {
                                listPemain = ((PemainResponse) response).getData();
                                proses(listPemain);
                            } else {
                                Toast.makeText(PemainHasilActivity.this, "Kesalahan Teknis !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PemainHasilActivity.this, "Kesalahan Teknis !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeProgress();
                    }
                });
    }

    private void proses(List<DataItemPemain> data) {
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNilai() != null) {
                    int dribble1 = Integer.parseInt(data.get(i).getNilai().getDribble1());
                    int dribble2 = Integer.parseInt(data.get(i).getNilai().getDribble2());
                    int dribble3 = Integer.parseInt(data.get(i).getNilai().getDribble3());
                    int dribble4 = Integer.parseInt(data.get(i).getNilai().getDribble4());
                    int dribble5 = Integer.parseInt(data.get(i).getNilai().getDribble5());
                    int dribble6 = Integer.parseInt(data.get(i).getNilai().getDribble6());
                    int dribble7 = Integer.parseInt(data.get(i).getNilai().getDribble7());
                    int dribble8 = Integer.parseInt(data.get(i).getNilai().getDribble8());

                    int shooting1 = Integer.parseInt(data.get(i).getNilai().getShooting1());
                    int shooting2 = Integer.parseInt(data.get(i).getNilai().getShooting2());
                    int shooting3 = Integer.parseInt(data.get(i).getNilai().getShooting3());
                    int shooting4 = Integer.parseInt(data.get(i).getNilai().getShooting4());


                    int pass1 = Integer.parseInt(data.get(i).getNilai().getPass1());
                    int pass2 = Integer.parseInt(data.get(i).getNilai().getPass2());
                    int pass3 = Integer.parseInt(data.get(i).getNilai().getPass3());
                    int pass4 = Integer.parseInt(data.get(i).getNilai().getPass4());

                    int defence = Integer.parseInt(data.get(i).getNilai().getDefence());
                    int serangan = Integer.parseInt(data.get(i).getNilai().getSerangan());
                    int speed = Integer.parseInt(data.get(i).getNilai().getSpeed());
                    int body = Integer.parseInt(data.get(i).getNilai().getBody_balance());
                    int handling = Integer.parseInt(data.get(i).getNilai().getBall_handling());
                    int rebound = Integer.parseInt(data.get(i).getNilai().getRebound());
                    int response = Integer.parseInt(data.get(i).getNilai().getResponse());
                    int jump = Integer.parseInt(data.get(i).getNilai().getJump());
                    int fisik = Integer.parseInt(data.get(i).getNilai().getFisik());
                    int kehadiran = Integer.parseInt(data.get(i).getNilai().getKehadiran());


                    int totalDribble = dribble1 + dribble2 + dribble3 + dribble3 + dribble4 + dribble5 + dribble6 + dribble7 + dribble8;
                    int totalShooting = shooting1 + shooting2 + shooting3 + shooting4;
                    int totalPass = pass1 + pass2 + pass3 + pass4;
                    int other = defence + serangan + speed + body + handling + rebound + response + jump + fisik + kehadiran;

                    int total = totalDribble + totalShooting + totalPass + other;
                    total = check(total);

                    if (total > 300) {
                        data.get(i).setMasuk(true);
                    } else {
                        data.get(i).setMasuk(false);
                    }
                    data.get(i).setTotal(total);

                    Collections.sort(data, (o1, o2) -> Integer.valueOf(o2.getTotal()).compareTo(o1.getTotal()));
                }
            }

        }
        adapter.swap(data);
    }
    private int check(int total) {
        return total / 5;
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . .");
        progressDialog.show();
    }

    public void closeProgress() {
        progressDialog.dismiss();
    }
}
