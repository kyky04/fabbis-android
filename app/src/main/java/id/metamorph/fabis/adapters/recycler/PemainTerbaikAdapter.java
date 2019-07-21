package id.metamorph.fabis.adapters.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.metamorph.fabis.R;
import id.metamorph.fabis.data.Contans;
import id.metamorph.fabis.models.pemain.DataItemPemain;


/**
 * Created by Comp on 2/11/2018.
 */

public class PemainTerbaikAdapter extends RecyclerView.Adapter<PemainTerbaikAdapter.MyHolder> {
    Context context;
    List<DataItemPemain> listItem;
    OnItemClickListener mOnItemClickListener;


    public void resetListData() {
        this.listItem = new ArrayList<>();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(DataItemPemain item);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    public PemainTerbaikAdapter(Context context, List<DataItemPemain> listArticle) {
        this.context = context;
        this.listItem = listArticle;
    }

    public PemainTerbaikAdapter(Context context) {
        this.context = context;
        this.listItem = new ArrayList<>();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_pemain_terbaik, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        DataItemPemain item = listItem.get(position);
        holder.tvNamaPemain.setText(item.getNama());
        holder.tvNim.setText(item.getNim());
        holder.tvBerat.setText("BB : " + item.getBerat() + ", TB : " + item.getTinggi());
        holder.tvPosisi.setText(item.getPosisi());
        int indeks = position + 1;
        holder.tvIndeks.setText(indeks + "");

        Glide.with(context).load(Contans.STORAGE + item.getFoto()).into(holder.imgFoto);

        if (item.isMasuk() && item.getTotal() > 400 && item.getTotal() < 600) {
            holder.tvStatus.setText("Layak");
        } else if (item.isMasuk() && item.getTotal() > 600) {
            holder.tvStatus.setText("Sangat Layak");
        } else {
            holder.tvStatus.setText("Kurang Layak");
        }
        holder.tvNilai.setText(item.getTotal() + " ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(item);
            }
        });

        holder.spTerbaik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    item.setSeleksi(true);
                } else {
                    item.setSeleksi(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_foto)
        CircleImageView imgFoto;
        @BindView(R.id.tv_nama_pemain)
        TextView tvNamaPemain;
        @BindView(R.id.tv_nim)
        TextView tvNim;
        @BindView(R.id.tv_berat)
        TextView tvBerat;
        @BindView(R.id.tv_posisi)
        TextView tvPosisi;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.sp_terbaik)
        AppCompatSpinner spTerbaik;
        @BindView(R.id.tv_indeks)
        TextView tvIndeks;
        @BindView(R.id.tv_nilai)
        TextView tvNilai;


        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void add(DataItemPemain item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<DataItemPemain> listItem) {
        for (DataItemPemain item : listItem) {
            add(item);
        }
    }

    public void swap(List<DataItemPemain> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public void setFilter(List<DataItemPemain> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public DataItemPemain getItem(int pos) {
        return listItem.get(pos);
    }

    public List<DataItemPemain> getListItem() {
        return listItem;
    }
}
