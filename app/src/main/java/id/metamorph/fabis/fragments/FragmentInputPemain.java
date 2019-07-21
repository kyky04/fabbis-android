package id.metamorph.fabis.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.DialogFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import id.metamorph.fabis.R;
import id.metamorph.fabis.data.Contans;
import id.metamorph.fabis.data.Session;
import id.metamorph.fabis.models.pemain.DataItemPemain;
import id.metamorph.fabis.utils.DialogUtils;
import okhttp3.Response;

import static id.metamorph.fabis.data.Contans.PEMAIN;

public class FragmentInputPemain extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.img_foto)
    CircleImageView imgFoto;
    @BindView(R.id.lay)
    LinearLayout lay;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.et_nama_lengkap)
    TextInputEditText etNamaLengkap;
    @BindView(R.id.et_nim)
    TextInputEditText etNim;
    @BindView(R.id.et_tinggi)
    TextInputEditText etTinggi;
    @BindView(R.id.et_berat)
    TextInputEditText etBerat;
    @BindView(R.id.btn_simpan)
    Button btnSimpan;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.et_posisi)
    TextInputEditText etPosisi;
    @BindView(R.id.text_posisi)
    TextInputLayout textPosisi;

    DataItemPemain pemain;
    private OnFragmentInteractionListener mListener;
    private ProgressDialog progressDialog;
    private String posisi = "";
    private Image image;
    private Session session;

    public static FragmentInputPemain newInstance(DataItemPemain pemain) {

        Bundle args = new Bundle();
        args.putSerializable("pemain", pemain);
        FragmentInputPemain fragment = new FragmentInputPemain();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pemain, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null) {
            pemain = (DataItemPemain) getArguments().getSerializable("pemain");

            etNamaLengkap.setText(pemain.getNama());
            etNim.setText(pemain.getNim());
            etTinggi.setText(pemain.getTinggi());
            etBerat.setText(pemain.getBerat());
            tvNama.setText("Fabbis Selection Team (" + pemain.getPosisi() + ")");
            etPosisi.setText(pemain.getPosisi());
            posisi = pemain.getPosisi();
            Glide.with(getActivity()).load(Contans.STORAGE + pemain.getFoto()).into(imgFoto);

            session = new Session(getActivity());
            if (session.getUser().getData().getStatus().equals("1")) {
                btnDelete.setVisibility(View.GONE);
                btnSimpan.setVisibility(View.GONE);
            }
        } else {
            btnDelete.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_foto, R.id.btn_simpan, R.id.et_posisi, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_foto:
                addImage();
                break;
            case R.id.btn_simpan:
                if (getArguments() != null) {
                    edit();
                } else {
                    if (image != null) {
                        post();
                    } else {
                        Toast.makeText(getActivity(), "Harap isi foto terlebih dahulu", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.et_posisi:
                String[] strings = getActivity().getResources().getStringArray(R.array.array_posisi);
                DialogUtils.dialogArray(getActivity(), strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etPosisi.setText(strings[which]);
                        switch (which) {
                            case 0:
                                posisi = "PG";
                                break;
                            case 1:
                                posisi = "SG";
                                break;
                            case 2:
                                posisi = "PF";
                                break;
                            case 3:
                                posisi = "SF";
                                break;
                            case 4:
                                posisi = "C";
                                break;
                        }
                    }
                });
                break;
        }
    }

    private void addImage() {
        ImagePicker.create(this)
                .returnMode(ReturnMode.ALL)
                .single()
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            image = ImagePicker.getFirstImageOrNull(data);

            Glide.with(getActivity()).load(image.getPath()).into(imgFoto);

        }
    }

    public void post() {
        showProgress();
        ANRequest.MultiPartBuilder builder = new ANRequest.MultiPartBuilder(PEMAIN);
        builder
                .addMultipartParameter("nama", etNamaLengkap.getText().toString())
                .addMultipartParameter("nim", etNim.getText().toString())
                .addMultipartParameter("tinggi", etTinggi.getText().toString())
                .addMultipartParameter("berat", etBerat.getText().toString())
                .addMultipartParameter("posisi", posisi)
                .addMultipartFile("foto", new File(image.getPath()))
                .addMultipartParameter("gender", String.valueOf(0))
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

    public void edit() {
        showProgress();
        ANRequest.MultiPartBuilder builder = new ANRequest.MultiPartBuilder(PEMAIN + "/{id_pemain}");
        builder.addPathParameter("id_pemain", String.valueOf(pemain.getId()))
                .addMultipartParameter("nama", etNamaLengkap.getText().toString())
                .addMultipartParameter("nim", etNim.getText().toString())
                .addMultipartParameter("tinggi", etTinggi.getText().toString())
                .addMultipartParameter("berat", etBerat.getText().toString())
                .addMultipartParameter("posisi", posisi)
                .addMultipartParameter("gender", String.valueOf(0))
                .addMultipartParameter("_method", "PUT");
        if (image != null) {
            builder.addMultipartFile("foto", new File(image.getPath()));
        }
        builder.build().getAsOkHttpResponse(new OkHttpResponseListener() {
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    public void setListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }
}
