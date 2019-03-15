package id.metamorph.fabis.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import id.metamorph.fabis.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentHome extends Fragment {

    @BindView(R.id.img_foto)
    CircleImageView imgFoto;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_no_hp)
    TextView tvNoHp;
    private OnFragmentInteractionListener mListener;
    private Unbinder unbinder;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(view);


        return view;
    }

    @OnClick({R.id.btn_sales, R.id.btn_income, R.id.btn_loan, R.id.btn_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sales:
                break;
            case R.id.btn_income:
                break;
            case R.id.btn_loan:
                break;
            case R.id.btn_withdraw:
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
