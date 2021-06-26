package com.digitalmeverick.stupa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class Register extends AppCompatActivity {
    Spinner spinner;
    String country;
    SpinerMember member;

    EditText userName,phone,email,password;
    Button b1,b2;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        initViews();
        spinnerMethod();
        buttons();

    }

    private void initViews() {
       // spinner=findViewById(R.id.sniper);
        userName=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        b1=findViewById(R.id.signup);
        b2=findViewById(R.id.loginResgis);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);

    }

    private void spinnerMethod() {
        country="india";
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                country = selectedCountry.getName();
            }
        });

      /*

        ##kept for referense


        String[] countryname={"ChooseCountry","USA","UK","FRANCE","UAE","INDIA"};
        member= new SpinerMember();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,countryname);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country=spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

          <Spinner//this is the Xml code for the spineer this can simple paste and spinner is ready
            android:layout_width="match_parent"
            android:layout_height="30dp"
            android:gravity="center"
            android:layout_marginStart="5dp"
            android:layout_marginTop="20dp"
            android:textAlignment="center"
            android:background="@drawable/custom_input"
            android:id="@+id/sniper"/>


        */
    }

    private void buttons() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity userEntity= new UserEntity();
                userEntity.setUserName(userName.getText().toString());
                userEntity.setEmail(email.getText().toString());
                userEntity.setPhone(phone.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setCountry(country);

                if (validationInput(userEntity)){
                    UserDatabase userDatabase= UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao= userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Congratulation you are stupa member",Toast.LENGTH_SHORT).show();
                                    userName.setText("");
                                    email.setText("");
                                    phone.setText("");
                                    password.setText("");
                                }
                            });

                        }
                    }).start();
                }else {
                    Toast.makeText(getApplicationContext(),"Kindly Fill all the CORRECT details",Toast.LENGTH_LONG).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }



    private Boolean validationInput(UserEntity userEntity)
    {
        if (userEntity.getUserName().isEmpty() ||userEntity.getCountry().isEmpty()||userEntity.getPassword().isEmpty()||userEntity.getPhone().length()<10
        ||userEntity.getEmail().isEmpty()||userEntity.getPhone().isEmpty()){
            return false;
        }
        return true;

    }
}