package com.example.qbhcomics.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qbhcomics.R;
import com.example.qbhcomics.model.DatabaseHelper;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    EditText username, password, repassword;
    Button signUp;
    TextView loginClient;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.signup_pass);
        repassword = (EditText) findViewById(R.id.signup_repass);
        signUp = (Button) findViewById(R.id.signup_btn);
        loginClient=(TextView)findViewById(R.id.login_client);
        databaseHelper = new DatabaseHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") && Patterns.EMAIL_ADDRESS.matcher(user).matches())
                    Toast.makeText(Signup.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = databaseHelper.checkUserName(user);
                        if (checkuser == false){
                            Boolean insert = databaseHelper.insertData(user, pass);
                            if (insert == true){
                                validateUser(username);
                                validatePass(password);
                            }
//                            else {
//                                Toast.makeText(Signup.this, "Sign failed", Toast.LENGTH_SHORT).show();
//                            }
                        }
                        else {
                            Toast.makeText(Signup.this, "User already exists, Please login", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Signup.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        loginClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });
    }
    private boolean validateUser(EditText username){
        String userInput = username.getText().toString();

        if (Patterns.EMAIL_ADDRESS.matcher(userInput).matches()){
            Toast.makeText(Signup.this, "Email Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(Signup.this, "Invalid Email Successfully", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validatePass(EditText password){
        String passInput = password.getText().toString();

        if (PASSWORD_PATTERN.matcher(passInput).matches()){
            password.setError("Password too weak");
            return true;
        }else {
            password.setError(null);
            return false;
        }
    }

}