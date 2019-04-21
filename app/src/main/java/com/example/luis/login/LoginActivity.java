package com.example.luis.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText account,password;
    private CheckBox rememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = (Button) findViewById(R.id.button_login);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        rememberPassword = (CheckBox) findViewById(R.id.remember_pass);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent intent = new Intent(LoginActivity.this, dengluchenggong.class);
                startActivity(intent);
            }
        });
    }

    private void save() {
        SharedPreferences.Editor editor = getSharedPreferences("account_password",MODE_PRIVATE).edit();
        editor.putString("account", account.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.putBoolean("remember_password", rememberPassword.isChecked());
        editor.apply();
    }

    private void read() {
        SharedPreferences pref = getSharedPreferences("account_password", MODE_PRIVATE);
        account.setText(pref.getString("account", ""));
        if (pref.getBoolean("remember_password",false)) {
           password.setText(pref.getString("password",""));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        read();
    }

}
