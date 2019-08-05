package id.metamorph.fabis.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.metamorph.fabis.R;
import id.metamorph.fabis.models.pemain.DataItemPemain;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentFormasi extends Fragment {
    private static final String TAG = "FragmentFormasi";

    @BindView(R.id.tv_sg)
    TextView tvSg;
    @BindView(R.id.tv_pg)
    TextView tvPg;
    @BindView(R.id.tv_sf)
    TextView tvSf;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_pf)
    TextView tvPf;
    private Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    List<DataItemPemain> list = new ArrayList<>();

    public static FragmentFormasi newInstance(List<DataItemPemain> pemain) {

        Bundle args = new Bundle();
        args.putSerializable("pemain", (Serializable) pemain);
        FragmentFormasi fragment = new FragmentFormasi();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentFormasi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formasi, container, false);
        unbinder = ButterKnife.bind(this, view);


        list = (List<DataItemPemain>) getArguments().getSerializable("pemain");

        if (list != null) {
            String pg = "";
            String sg = "";
            String sf = "";
            String c = "";
            String pf = "";
            int noPg = 1;
            int noSg = 1;
            int noSf = 1;
            int noC = 1;
            int noPf = 1;


            for (int i = 0; i < list.size(); i++) {
                Log.d(TAG, "PEMAIN: " + list.get(i).getPosisi());
                DataItemPemain pemain = list.get(i);
                if (pemain.getPosisi().equals("PG")) {

                    pg += noPg + ". " + pemain.getNama() + "\n";
                    noPg++;
                } else if (pemain.getPosisi().equals("SG")) {

                    sg += noSg + ". " + pemain.getNama() + "\n";
                    noSg++;
                } else if (pemain.getPosisi().equals("SF")) {
                    sf += noSf + ". " + pemain.getNama() + "\n";
                    noSf++;
                } else if (pemain.getPosisi().equals("C")) {
                    c += noC + ". " + pemain.getNama() + "\n";
                    noC++;
                } else if (pemain.getPosisi().equals("PF")) {
                    pf += noPf + ". " + pemain.getNama() + "\n";
                    noPf++;
                }
            }
            tvPg.setText(pg);
            tvSg.setText(sg);
            tvSf.setText(sf);
            tvCenter.setText(c);
            tvPf.setText(pf);


        }

        return view;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    public void setListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }
}
