package id.metamorph.fabis.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.metamorph.fabis.R;
import id.metamorph.fabis.adapters.recycler.PemainPenialaianAdapter;
import id.metamorph.fabis.fragments.FragmentInputNilai;
import id.metamorph.fabis.models.pemain.DataItemPemain;
import id.metamorph.fabis.models.pemain.PemainResponse;
import id.metamorph.fabis.utils.ActivityUtils;

import static id.metamorph.fabis.data.Contans.PEMAIN;

public class PemainPenilaianActivity extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private ProgressDialog progressDialog;

    PemainPenialaianAdapter adapter;
    String posisi = "";
    int gender = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemain);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        if(getIntent().getStringExtra("posisi") !=null){
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

        adapter = new PemainPenialaianAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new PemainPenialaianAdapter.OnItemClickListener() {
            @Override
            public void onClick(DataItemPemain item) {
                FragmentInputNilai fragment = FragmentInputNilai.newInstance(item);
                ActivityUtils.addFragment(PemainPenilaianActivity.this, android.R.id.content, fragment);
                fragment.setListener(new FragmentInputNilai.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentInteraction() {
                        load();
                    }
                });
            }
        });


        load();
    }

    @OnClick(R.id.fab_add)
    public void onViewClicked() {
        FragmentInputNilai fragment = new FragmentInputNilai();
        ActivityUtils.addFragment(PemainPenilaianActivity.this, android.R.id.content, fragment);
        fragment.setListener(new FragmentInputNilai.OnFragmentInteractionListener() {
            @Override
            public void onFragmentInteraction() {
                load();
            }
        });
    }

    public void load() {
        showProgress();
        ANRequest.GetRequestBuilder getRequestBuilder = new ANRequest.GetRequestBuilder(PEMAIN);
        if (!posisi.equals("")) {
            getRequestBuilder.addQueryParameter("posisi", posisi);
        }
        getRequestBuilder.addQueryParameter("gender", String.valueOf(gender));
        getRequestBuilder.build()
                .getAsObject(PemainResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closeProgress();
                        if (response instanceof PemainResponse) {
                            if (((PemainResponse) response).isStatus()) {
                                adapter.swap(((PemainResponse) response).getData());
                            } else {
                                Toast.makeText(PemainPenilaianActivity.this, "Kesalahan Teknis !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PemainPenilaianActivity.this, "Kesalahan Teknis !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeProgress();
                    }
                });
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
