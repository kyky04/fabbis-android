package id.metamorph.fabis.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import id.metamorph.fabis.R;
import id.metamorph.fabis.adapters.recycler.KriteriaAdapter;
import id.metamorph.fabis.models.kriteria.DetailItem;
import id.metamorph.fabis.models.kriteria.KriteriaResponse;
import id.metamorph.fabis.models.pemain.DataItemPemain;
import okhttp3.Response;

import static id.metamorph.fabis.data.Contans.KRITERIA;
import static id.metamorph.fabis.data.Contans.NILAI;
import static id.metamorph.fabis.data.Contans.PEMAIN;

public class FragmentInputNilai extends DialogFragment {

    Unbinder unbinder;
    DataItemPemain pemain;
    @BindView(R.id.img_foto)
    CircleImageView imgFoto;
    @BindView(R.id.lay)
    LinearLayout lay;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_nim)
    TextView tvNim;
    @BindView(R.id.et_dribble1)
    EditText etDribble1;
    @BindView(R.id.et_dribble2)
    EditText etDribble2;
    @BindView(R.id.et_dribble3)
    EditText etDribble3;
    @BindView(R.id.et_dribble4)
    EditText etDribble4;
    @BindView(R.id.et_dribble5)
    EditText etDribble5;
    @BindView(R.id.et_dribble6)
    EditText etDribble6;
    @BindView(R.id.et_dribble7)
    EditText etDribble7;
    @BindView(R.id.et_dribble8)
    EditText etDribble8;
    @BindView(R.id.et_shoot1)
    EditText etShoot1;
    @BindView(R.id.et_shoot2)
    EditText etShoot2;
    @BindView(R.id.et_shoot3)
    EditText etShoot3;
    @BindView(R.id.et_shoot4)
    EditText etShoot4;
    @BindView(R.id.et_pass1)
    EditText etPass1;
    @BindView(R.id.et_pass2)
    EditText etPass2;
    @BindView(R.id.et_pass3)
    EditText etPass3;
    @BindView(R.id.et_pass4)
    EditText etPass4;
    @BindView(R.id.et_defence)
    EditText etDefence;
    @BindView(R.id.et_serangan)
    EditText etSerangan;
    @BindView(R.id.et_speed)
    EditText etSpeed;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_simpan)
    Button btnSimpan;
    @BindView(R.id.lay_button)
    LinearLayout layButton;


    private OnFragmentInteractionListener mListener;
    private ProgressDialog progressDialog;
    private String posisi = "";
    private KriteriaAdapter adapter;

    public static FragmentInputNilai newInstance(DataItemPemain pemain) {

        Bundle args = new Bundle();
        args.putSerializable("pemain", pemain);
        FragmentInputNilai fragment = new FragmentInputNilai();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penilaian, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null) {
            pemain = (DataItemPemain) getArguments().getSerializable("pemain");

            tvNim.setText(pemain.getNim());
            tvNama.setText(pemain.getNama() + " (" + pemain.getPosisi() + ")");

            if (pemain.getNilai() != null) {
                etDribble1.setText(pemain.getNilai().getDribble1());
                etDribble2.setText(pemain.getNilai().getDribble2());
                etDribble3.setText(pemain.getNilai().getDribble3());
                etDribble4.setText(pemain.getNilai().getDribble4());
                etDribble5.setText(pemain.getNilai().getDribble5());
                etDribble6.setText(pemain.getNilai().getDribble6());
                etDribble7.setText(pemain.getNilai().getDribble7());
                etDribble8.setText(pemain.getNilai().getDribble8());
                etPass1.setText(pemain.getNilai().getPass1());
                etPass2.setText(pemain.getNilai().getPass2());
                etPass3.setText(pemain.getNilai().getPass3());
                etPass4.setText(pemain.getNilai().getPass4());
                etShoot1.setText(pemain.getNilai().getShooting1());
                etShoot2.setText(pemain.getNilai().getShooting2());
                etShoot3.setText(pemain.getNilai().getShooting3());
                etShoot4.setText(pemain.getNilai().getShooting4());
                etDefence.setText(pemain.getNilai().getDefence());
                etSerangan.setText(pemain.getNilai().getSerangan());
                etSpeed.setText(pemain.getNilai().getSpeed());

                layButton.setVisibility(View.GONE);
            }
        } else {
            etDribble1.setText("50");
            etDribble2.setText("50");
            etDribble3.setText("50");
            etDribble4.setText("50");
            etDribble5.setText("50");
            etDribble6.setText("50");
            etDribble7.setText("50");
            etDribble8.setText("50");
            etPass1.setText("50");
            etPass2.setText("50");
            etPass3.setText("50");
            etPass4.setText("50");
            etShoot1.setText("50");
            etShoot2.setText("50");
            etShoot3.setText("50");
            etShoot4.setText("50");
            etDefence.setText("50");
            etSerangan.setText("50");
            etSpeed.setText("50");
        }

        adapter = new KriteriaAdapter(getActivity());
//        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recycler.setAdapter(adapter);

//        loadKriteria();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void loadKriteria() {
        showProgress();
        AndroidNetworking.get(KRITERIA)
                .build()
                .getAsObject(KriteriaResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closeProgress();
                        if (response instanceof KriteriaResponse) {
                            List<DetailItem> detailItems = new ArrayList<>();
                            for (int i = 0; i < ((KriteriaResponse) response).getData().size(); i++) {
                                DetailItem detailItem = new DetailItem();
                                detailItem.setNama(((KriteriaResponse) response).getData().get(i).getNama());
                                detailItem.setHeader(true);
                                detailItems.add(detailItem);
                                for (int j = 0; j < ((KriteriaResponse) response).getData().get(i).getDetail().size(); j++) {
                                    DetailItem detail = new DetailItem();
                                    detail.setNama(((KriteriaResponse) response).getData().get(i).getDetail().get(j).getNama());
                                    detail.setIdKriteria(((KriteriaResponse) response).getData().get(i).getDetail().get(j).getIdKriteria());
                                    detail.setHeader(false);
                                    detailItems.add(detail);
                                }
                            }
                            adapter.swap(detailItems);
                        } else {

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeProgress();
                    }
                });
    }

    public void post() {
        showProgress();
        AndroidNetworking.post(NILAI)
                .addBodyParameter("id_pemain", String.valueOf(pemain.getId()))
                .addBodyParameter("dribble1", etDribble1.getText().toString())
                .addBodyParameter("dribble2", etDribble2.getText().toString())
                .addBodyParameter("dribble3", etDribble3.getText().toString())
                .addBodyParameter("dribble4", etDribble4.getText().toString())
                .addBodyParameter("dribble5", etDribble5.getText().toString())
                .addBodyParameter("dribble6", etDribble6.getText().toString())
                .addBodyParameter("dribble7", etDribble7.getText().toString())
                .addBodyParameter("dribble8", etDribble8.getText().toString())
                .addBodyParameter("shooting1", etShoot1.getText().toString())
                .addBodyParameter("shooting2", etShoot2.getText().toString())
                .addBodyParameter("shooting3", etShoot3.getText().toString())
                .addBodyParameter("shooting4", etShoot4.getText().toString())
                .addBodyParameter("pass1", etPass1.getText().toString())
                .addBodyParameter("pass2", etPass2.getText().toString())
                .addBodyParameter("pass3", etPass3.getText().toString())
                .addBodyParameter("pass4", etPass4.getText().toString())
                .addBodyParameter("defence", etDefence.getText().toString())
                .addBodyParameter("speed", etSpeed.getText().toString())
                .addBodyParameter("serangan", etSerangan.getText().toString())
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        closeProgress();
                        if (response.code() == 200) {
                            mListener.onFragmentInteraction();
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeProgress();

                    }
                });
    }

    public void delete() {
        showProgress();
        AndroidNetworking.delete(PEMAIN + "/{id_pemain}")
                .addPathParameter("id_pemain", String.valueOf(pemain.getId()))
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        closeProgress();
                        if (response.code() == 200) {
                            mListener.onFragmentInteraction();
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closeProgress();

                    }
                });
    }

    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading . . .");
        progressDialog.show();
    }

    public void closeProgress() {
        progressDialog.dismiss();
    }

    @OnClick({R.id.btn_cancel, R.id.btn_simpan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_simpan:
                post();
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    public void setListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }
}
