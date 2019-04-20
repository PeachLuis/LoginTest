package com.example.luis.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity {
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit = (EditText) findViewById(R.id.account);
        edit = (EditText) findViewById(R.id.password);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(LoginActivity.this,"存储成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(LoginActivity.this,"存储不成功",Toast.LENGTH_SHORT).show();
        }


//        pref = PreferenceManager.getDefaultSharedPreferences(this);
//        rememberPass = (CheckBox) findViewById(R.id.remember_pass) ;
        Button button = (Button) findViewById(R.id.button3);

//        boolean isRemember = pref.getBoolean("remember_password", false);
//        if (isRemember) {
//            String account = pref.getString("account", "");
//            String password = pref.getString("password", "");
//            accountEdit.setText(password);
//            rememberPass.setChecked(true);
//        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下面这一段一直虽然能运行，但是一点Button就应用死掉
//                String account = accountEdit.getText().toString();
//                String password = passwordEdit.getText().toString();
//                //如果账号是"admin"，密码是“123456”,就登录成功
//                if (accountEdit.getText().toString().equals("admin") && passwordEdit.getText().toString().equals("123456")) {
//                    editor = pref.edit();
//                    if (rememberPass.isChecked()) {
//                        editor.putBoolean("remember_password", true);
//                        editor.putString("account", account);
//                        editor.putString("password", password);
//                    }else{
//                        editor.clear();
//                    }
//                    editor.apply();
//                    Intent intent = new Intent(LoginActivity.this, dengluchenggong.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(LoginActivity.this,"登录失败，请检查用户名和密码",Toast.LENGTH_LONG).show();
//                }
//                Intent intent = new Intent(LoginActivity.this, dengluchenggong.class);
//                startActivity(intent);
//                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);
    }

    public void save(String inputText) {
        FileOutputStream out =null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("date", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
