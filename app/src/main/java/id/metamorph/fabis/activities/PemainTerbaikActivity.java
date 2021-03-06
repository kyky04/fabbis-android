package id.metamorph.fabis.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.metamorph.fabis.R;
import id.metamorph.fabis.adapters.recycler.PemainTerbaikAdapter;
import id.metamorph.fabis.algorithm.Topsis;
import id.metamorph.fabis.fragments.FragmentInputNilai;
import id.metamorph.fabis.models.pemain.DataItemPemain;
import id.metamorph.fabis.models.pemain.PemainResponse;
import id.metamorph.fabis.utils.ActivityUtils;
import okhttp3.Response;

import static id.metamorph.fabis.data.Contans.PEMAIN;
import static id.metamorph.fabis.data.Contans.PEMAIN_SELEKSI;

public class PemainTerbaikActivity extends AppCompatActivity {
    private static final String TAG = "PemainTerbaikActivity";

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.btn_save)
    Button btnSave;
    private ProgressDialog progressDialog;

    PemainTerbaikAdapter adapter;
    String posisi = "";
    int gender = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemain_terbaik);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        if (getIntent().getStringExtra("posisi") != null) {
            posisi = getIntent().getStringExtra("posisi");
        }
        gender = getIntent().getIntExtra("gender", 0);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
                load();
            }
        });

        adapter = new PemainTerbaikAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new PemainTerbaikAdapter.OnItemClickListener() {
            @Override
            public void onClick(DataItemPemain item) {
//                FragmentInputNilai fragment = FragmentInputNilai.newInstance(item);
//                ActivityUtils.addFragment(PemainTerbaikActivity.this, android.R.id.content, fragment);
//                fragment.setListener(new FragmentInputNilai.OnFragmentInteractionListener() {
//                    @Override
//                    public void onFragmentInteraction() {
//                        load();
//                    }
//                });
            }
        });


        load();
    }


    public void load() {
        showProgress();
        ANRequest.GetRequestBuilder getRequestBuilder = new ANRequest.GetRequestBuilder(PEMAIN);
        if (!posisi.equals("")) {
            getRequestBuilder.addQueryParameter("posisi", posisi);
        }
        getRequestBuilder.addQueryParameter("gender", String.valueOf(gender));
        getRequestBuilder.addQueryParameter("status", String.valueOf(1));
        getRequestBuilder.build()
                .getAsObject(PemainResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closeProgress();
                        if (response instanceof PemainResponse) {
                            if (((PemainResponse) response).isStatus()) {
                                List<DataItemPemain> data = ((PemainResponse) response).getData();
                                proses(data);

                                //proses topsis


                            } else {
                                Toast.makeText(PemainTerbaikActivity.this, "Kesalahan Teknis !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PemainTerbaikActivity.this, "Kesalahan Teknis !", Toast.LENGTH_SHORT).show();
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

                    for (int j = 0; j < data.size(); j++) {
                        try {
                            Topsis topsis = new Topsis(data.size(), data.get(j).getId());
                            topsis.TopsisMethod();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


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


    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . .");
        progressDialog.show();
    }

    public void closeProgress() {
        progressDialog.dismiss();
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        post();
    }

    public void post() {
        showProgress();
        ANRequest.PostRequestBuilder postRequestBuilder = new ANRequest.PostRequestBuilder(PEMAIN_SELEKSI);

        postRequestBuilder.addBodyParameter("gender", String.valueOf(gender));
        for (int i = 0; i < adapter.getItemCount(); i++) {
            if (adapter.getItem(i).isSeleksi()) {
                postRequestBuilder.addBodyParameter("pemain[" + i + "]", String.valueOf(adapter.getItem(i).getId()));
            }
        }
        postRequestBuilder
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        closeProgress();
                        if (response.code() == 200) {
                            Toast.makeText(PemainTerbaikActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(PemainTerbaikActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeProgress();

                    }
                });
    }

    private int check(int total) {
        return total / 5;
    }
}
