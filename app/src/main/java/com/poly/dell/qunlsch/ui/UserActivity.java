package com.poly.dell.qunlsch.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.dell.qunlsch.adapter.NguoiDungAdapter;
import com.poly.dell.qunlsch.listener.OnDeleteNguoiDung;
import com.poly.dell.qunlsch.listener.OnEditNguoiDung;
import com.poly.dell.qunlsch.model.User;
import com.poly.dell.qunlsch.R;
import com.poly.dell.qunlsch.sqlite.DatabaseHelper;
import com.poly.dell.qunlsch.sqlite.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements OnEditNguoiDung, OnDeleteNguoiDung {
    Toolbar toolbarNguoiDung;
    RecyclerView rvNguoiDung;
    private List<User> userList;
    private NguoiDungAdapter adapter;
    private Cursor cursorNguoiDung;
    private UserDAO userDAO;
    String dangdt ="0\\d{9,10}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoidung);

        toolbarNguoiDung = findViewById(R.id.toolbarNguoiDung);
        setSupportActionBar(toolbarNguoiDung);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarNguoiDung.setTitleTextColor(Color.WHITE);
        toolbarNguoiDung.setTitle(getString(R.string.title_nguoidung));
        toolbarNguoiDung.setNavigationIcon(R.drawable.undo);
        userDAO = new UserDAO(this);

        toolbarNguoiDung.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvNguoiDung = findViewById(R.id.RecyclerView_NguoiDung);
        userList = new ArrayList<>();
        adapter = new NguoiDungAdapter(userList, this, this);
        rvNguoiDung.setAdapter(adapter);
        getNguoiDung();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvNguoiDung.setLayoutManager(manager);

    }

    public void getNguoiDung() {
        cursorNguoiDung = userDAO.getUser();
        if (cursorNguoiDung == null) {
            return;
        }
        if (cursorNguoiDung.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursorNguoiDung.getString(cursorNguoiDung.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)));
                user.setPassword(cursorNguoiDung.getString(cursorNguoiDung.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD)));
                user.setName(cursorNguoiDung.getString(cursorNguoiDung.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
                user.setPhonenumber(cursorNguoiDung.getString(cursorNguoiDung.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER)));
                userList.add(user);
            } while (cursorNguoiDung.moveToNext());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNguoiDung:
                showDialogThemNguoiDung();
                break;
            case R.id.doiMatKhau:
                showDialogDoiMatKhau();
                break;

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nguoidung_menu, menu);
        return true;
    }

    public void showDialogThemNguoiDung() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_themnguoidung, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();

        final EditText edTendangNhapThemNguoidung = dialogView.findViewById(R.id.edTendangNhap_themNguoidung);
        final EditText edMatKhauThemNguoidung = dialogView.findViewById(R.id.edMatKhau_themNguoidung);
        final EditText edMatKhau2ThemNguoidung = dialogView.findViewById(R.id.edMatKhau2_themNguoidung);
        final EditText edSDTThemNguoidung = dialogView.findViewById(R.id.edSDT_themNguoidung);
        final EditText edHoTenThemNguoidung = dialogView.findViewById(R.id.edHoTen_themNguoidung);
        Button btnThemThemNguoiDung = dialogView.findViewById(R.id.btnThem_ThemNguoiDung);
        Button btnHuyThemNguoiDung = dialogView.findViewById(R.id.btnHuy_ThemNguoiDung);

        btnHuyThemNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        btnThemThemNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edTendangNhapThemNguoidung.getText().toString().trim();
                String passWord = edMatKhauThemNguoidung.getText().toString().trim();
                String passWord2 = edMatKhau2ThemNguoidung.getText().toString().trim();
                String name = edHoTenThemNguoidung.getText().toString().trim();
                String sdt = edSDTThemNguoidung.getText().toString().trim();

                if (userName.isEmpty() || passWord.isEmpty() || passWord2.isEmpty() || name.isEmpty() || sdt.isEmpty()) {
                    if (userName.isEmpty())
                        edTendangNhapThemNguoidung.setError(getString(R.string.notify_empty_user));
                    if (passWord.isEmpty()) {
                        edMatKhauThemNguoidung.setError(getString(R.string.notify_empty_pass));
                    }
                    if (passWord2.isEmpty())
                        edMatKhau2ThemNguoidung.setError(getString(R.string.notify_empty_pass2));
                    if (name.isEmpty())
                        edHoTenThemNguoidung.setError(getString(R.string.notify_empty_name));
                    if (sdt.isEmpty())
                        edSDTThemNguoidung.setError(getString(R.string.notify_empty_sdt));
                } else if (passWord.length() < 6) {
                    edMatKhauThemNguoidung.setError(getString(R.string.notify_length_pass));
                } else if (!passWord.equals(passWord2)) {
                    edMatKhau2ThemNguoidung.setError(getString(R.string.notify_same_pass));
                } else if(!sdt.matches(dangdt)){
                   edSDTThemNguoidung.setError(getString(R.string.notify_same_sdt));
                } else {
                    User user2 = userDAO.getUser(userName);
                    if (user2 == null) {
                       Integer a= userDAO.insertUser(userName, passWord, name, sdt);
                        userList.clear();
                        getNguoiDung();
                        adapter.notifyDataSetChanged();
                        dialog1.dismiss();
                        if(a==1){
                            Toast.makeText(UserActivity.this, "Thêm thành công người dùng: "+name, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        edTendangNhapThemNguoidung.setError(getString(R.string.user_tontai));
                    }

                }
            }
        });
    }

    public void showDialogDoiMatKhau() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_doimatkhau, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button doi = dialogView.findViewById(R.id.btnDoi_Doimatkhau);
        Button huy = dialogView.findViewById(R.id.btnHuy_Doimatkhau);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }


    @Override
    public void onDeleteNguoiDung(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getString(R.string.question_delete_user));
        builder.setNegativeButton(R.string.co, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userDAO.deleteUser(userList.get(position).getUsername());
                userList.clear();
                getNguoiDung();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setPositiveButton(R.string.khong, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onEditNguoiDung(final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_editnguoidung, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        final EditText name = dialogView.findViewById(R.id.edHoTen_EditNguoidung);
        final EditText sdt = dialogView.findViewById(R.id.edSDT_EditNguoidung);
        String name_old = userList.get(position).getName();
        String sdt_old = userList.get(position).getPhonenumber();
        name.setText(name_old);
        sdt.setText(sdt_old);


        Button sua = dialogView.findViewById(R.id.btnSua_EditNguoiDung);
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name_new = name.getText().toString().trim();
                final String sdt_new = sdt.getText().toString();
                if (name_new.isEmpty() || sdt_new.isEmpty()) {
                    if (name_new.isEmpty()) {
                        name.setError(getString(R.string.notify_empty_name));
                    }
                    if (sdt_new.isEmpty()) {
                        sdt.setError(getString(R.string.notify_empty_sdt));
                    }
                }else if(!sdt_new.matches(dangdt)){
                    sdt.setError(getString(R.string.notify_same_sdt));
                } else{
                    userDAO.updateUser(userList.get(position).getUsername(), name_new, sdt_new);
                    userList.clear();
                    getNguoiDung();
                    adapter.notifyDataSetChanged();
                    dialog1.dismiss();
                }
            }
        });
        Button huy = dialogView.findViewById(R.id.btnHuy_EditNguoiDung);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }
}
