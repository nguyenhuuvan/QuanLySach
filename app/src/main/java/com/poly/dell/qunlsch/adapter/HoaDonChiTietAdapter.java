package com.poly.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poly.dell.qunlsch.listener.OnDelete;
import com.poly.dell.qunlsch.model.HoaDonChitiet;
import com.poly.dell.qunlsch.R;

import java.util.List;

public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.ViewHolder>{
    private List<HoaDonChitiet> hoaDonList;
    private OnDelete onDelete;

    public HoaDonChiTietAdapter(List<HoaDonChitiet> hoaDonList, OnDelete onDelete) {
        this.hoaDonList = hoaDonList;
        this.onDelete = onDelete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_hoadonchitiet,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonChiTietAdapter.ViewHolder holder, int position) {
        HoaDonChitiet st = hoaDonList.get(position);
        holder.tvSoluong.setText(st.getSoluong());
        holder.tvTen.setText(st.getTensach());
        holder.tvThanhTien.setText(st.getThanhtien());
        holder.tvDonGia.setText(st.getDongia());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.onDelete();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar,imgDelete;
        public TextView tvTen, tvSoluong,tvDonGia,tvThanhTien;


        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_HoaDonChiTiet);
            tvSoluong = itemView.findViewById(R.id.tvSoluong_HoaDonChiTiet);
            tvTen = itemView.findViewById(R.id.tvTenSach_HoaDonChiTiet);
            tvDonGia=itemView.findViewById(R.id.tvDonGia_HoaDonChiTiet);
            tvThanhTien=itemView.findViewById(R.id.tvThanhTien_HoaDonChiTiet);
            imgDelete=itemView.findViewById(R.id.imgDeleteHoaDonChiTiet_item);
        }

    }
}
