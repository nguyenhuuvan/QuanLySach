package com.poly.dell.qunlsch.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.poly.dell.qunlsch.R;
import com.poly.dell.qunlsch.model.User;
import com.poly.dell.qunlsch.sqlite.DatabaseHelper;
import com.poly.dell.qunlsch.sqlite.UserDAO;

public class LoginActivity extends AppCompatActivity {
    private ImageView imgLogo;
    private EditText edUserName;
    private EditText edPassWord;
    private CheckBox chkRememberPass;
    private Button loginDangnhap;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgLogo = findViewById(R.id.imgLogo);
        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassWord);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        loginDangnhap = findViewById(R.id.login_dangnhap);
        userDAO = new UserDAO(this);

        edPassWord.setText("123456");
        edUserName.setText("Admin");


        loginDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUserName.getText().toString().trim();
                String pass = edPassWord.getText().toString().trim();
                if (pass.length() < 6 || user.isEmpty() || pass.isEmpty()) {

                    if (user.isEmpty()) {
                        edUserName.setError(getString(R.string.notify_empty_user));
                    }
                    if (pass.length() < 6) {
                        edPassWord.setError(getString(R.string.notify_length_pass));
                    }
                    if (pass.isEmpty()) {
                        edPassWord.setError(getString(R.string.notify_empty_pass));
                    }

                } else {
                    if (edPassWord.getText().toString().trim().equals("123456") && edUserName.getText().toString().trim().equals("Admin")) {
                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                        final int[] a = {0};

                        progressDialog.setTitle(getString(R.string.login));
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


                        CountDownTimer countDownTimer = new CountDownTimer(1500, 40) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                a[0] = a[0] + 4;
                                progressDialog.show();
                                progressDialog.setProgress(a[0]);
                            }

                            @Override
                            public void onFinish() {
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }
                        }.start();
                    } else {
                        User user2 = userDAO.getUser(edUserName.getText().toString().trim());
                        if (user2 == null) {
                            edUserName.setError(getString(R.string.user_chuatontai));
                        } else {
                            if (user2.getPassword().equals(edPassWord.getText().toString().trim())) {
                                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                                final int[] a = {0};

                                progressDialog.setTitle(getString(R.string.login));
                                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


                                CountDownTimer countDownTimer = new CountDownTimer(1500, 40) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        a[0] = a[0] + 4;
                                        progressDialog.show();
                                        progressDialog.setProgress(a[0]);
                                    }

                                    @Override
                                    public void onFinish() {
                                        progressDialog.dismiss();
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    }
                                }.start();
                            } else {
                                edPassWord.setError(getString(R.string.saipass));
                            }

                        }
                    }

                }
            }
        });


    }
}
