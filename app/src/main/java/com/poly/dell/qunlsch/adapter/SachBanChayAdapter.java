package com.poly.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poly.dell.qunlsch.model.Sach;
import com.poly.dell.qunlsch.R;

import java.util.List;

public class SachBanChayAdapter extends RecyclerView.Adapter<SachBanChayAdapter.ViewHolder>{
    private List<Sach> sachList;

    public SachBanChayAdapter(List<Sach> sachList) {
        this.sachList = sachList;
    }

    @NonNull
    @Override
    public SachBanChayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_sachbanchay,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SachBanChayAdapter.ViewHolder holder, int position) {
        Sach st = sachList.get(position);
        holder.tvName.setText(st.getName());
        holder.tvSoluong.setText(st.getSoluong());
        holder.tvMa.setText(st.getMasach());
    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar;
        public TextView tvName;
        public TextView tvSoluong,tvMa;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_SachBanChay);
            tvName = itemView.findViewById(R.id.tvTenSach_SachBanChay);
            tvSoluong = itemView.findViewById(R.id.tvSoluong_SachBanChay);
            tvMa=itemView.findViewById(R.id.tvMaSach_SachBanChay);
        }

    }
}
