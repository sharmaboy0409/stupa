package com.digitalmeverick.stupa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText phoneLogin,passwardLogin;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview();

        button();


    }



    private void initview() {
        phoneLogin= findViewById(R.id.phoneLogin);
        passwardLogin= findViewById(R.id.passwordLogin);
        login= findViewById(R.id.loginActivity);

    }
    private void button() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone =phoneLogin.getText().toString();
                String password =passwardLogin.getText().toString();
                if (phone.isEmpty()||password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"fill all details",Toast.LENGTH_SHORT).show();
                }else
                {

                    UserDatabase userDatabase= UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao= userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity= userDao.Login(phone,password);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this,"Invalid credential",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                                startActivity(new Intent(LoginActivity.this,Home.class));

                            }
                        }
                    }).start();
                }
            }
        });
    }
}