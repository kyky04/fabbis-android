package id.metamorph.fabis.adapters.recycler;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.metamorph.fabis.R;
import id.metamorph.fabis.models.kriteria.DetailItem;
import id.metamorph.fabis.utils.InputFilterMinMax;


/**
 * Created by Comp on 2/11/2018.
 */

public class KriteriaAdapter extends RecyclerView.Adapter<KriteriaAdapter.MyHolder> {
    Context context;
    List<DetailItem> listItem;
    OnItemClickListener mOnItemClickListener;


    public void resetListData() {
        this.listItem = new ArrayList<>();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(DetailItem item);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    public KriteriaAdapter(Context context, List<DetailItem> listArticle) {
        this.context = context;
        this.listItem = listArticle;
    }

    public KriteriaAdapter(Context context) {
        this.context = context;
        this.listItem = new ArrayList<>();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_penilaian, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        DetailItem item = listItem.get(position);

        holder.etNilai.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100")});
        if (item.isHeader()) {
            holder.tvTitle.setText(item.getNama());
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.etNilai.setVisibility(View.GONE);
        } else {
            holder.tvTitle.setText(item.getNama());
            holder.etNilai.setVisibility(View.VISIBLE);
        }

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
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.et_nilai)
        EditText etNilai;


        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void add(DetailItem item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<DetailItem> listItem) {
        for (DetailItem item : listItem) {
            add(item);
        }
    }

    public void swap(List<DetailItem> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public void setFilter(List<DetailItem> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public DetailItem getItem(int pos) {
        return listItem.get(pos);
    }
}
