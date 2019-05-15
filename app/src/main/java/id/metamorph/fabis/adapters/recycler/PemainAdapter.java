package id.metamorph.fabis.adapters.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.metamorph.fabis.R;
import id.metamorph.fabis.data.Contans;
import id.metamorph.fabis.models.pemain.DataItemPemain;


/**
 * Created by Comp on 2/11/2018.
 */

public class PemainAdapter extends RecyclerView.Adapter<PemainAdapter.MyHolder> {
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


    public PemainAdapter(Context context, List<DataItemPemain> listArticle) {
        this.context = context;
        this.listItem = listArticle;
    }

    public PemainAdapter(Context context) {
        this.context = context;
        this.listItem = new ArrayList<>();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_pemain, parent, false);
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(item);
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
        @BindView(R.id.tv_indeks)
        TextView tvIndeks;


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
}
