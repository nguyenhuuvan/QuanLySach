package com.poly.dell.qunlsch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poly.dell.qunlsch.listener.OnDelete;
import com.poly.dell.qunlsch.listener.OnDeleteNguoiDung;
import com.poly.dell.qunlsch.listener.OnEdit;
import com.poly.dell.qunlsch.listener.OnEditNguoiDung;
import com.poly.dell.qunlsch.model.User;
import com.poly.dell.qunlsch.R;

import java.util.List;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder>{
    private List<User> userList;
    private OnDeleteNguoiDung onDelete;
    private OnEditNguoiDung onEdit;

    public NguoiDungAdapter(List<User> userList, OnDeleteNguoiDung onDelete, OnEditNguoiDung onEdit) {
        this.userList = userList;
        this.onDelete = onDelete;
        this.onEdit = onEdit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_nguoidung,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final User st = userList.get(position);
        holder.tvName.setText(st.getName());
        holder.tvSDT.setText(st.getPhonenumber());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete.onDeleteNguoiDung(position);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdit.onEditNguoiDung(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar,imgEdit,imgDelete;
        public TextView tvName;
        public TextView tvSDT;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar_NguoiDung);
            tvSDT = itemView.findViewById(R.id.tvSDT_NguoiDung);
            tvName = itemView.findViewById(R.id.tvName_NguoiDung);
            imgEdit = itemView.findViewById(R.id.imgEditNguoiDung_item);
            imgDelete = itemView.findViewById(R.id.imgDeleteNguoiDung_item);
        }

    }
}
