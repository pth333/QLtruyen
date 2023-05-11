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

public class MainActivity extends AppCompatActivity {
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
    EditText username,password;

    TextView signupClient;

    Button btnLogin;

    DatabaseHelper databaseHelper;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        btnLogin =(Button) findViewById(R.id.btnlg);
        username =(EditText)findViewById(R.id.edtEmail);
        password =(EditText)findViewById(R.id.edtPass);
        databaseHelper = new DatabaseHelper(this);

        signupClient=(TextView)findViewById(R.id.signup_client);
        signupClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();

                    if (user.equals("") || pass.equals("") && Patterns.EMAIL_ADDRESS.matcher(user).matches() )
                        Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    else {
                        Boolean checkuserpass = databaseHelper.checkUserPassword(user,pass);
                        if (checkuserpass == true){
                            validateUser(username);
                        }

//                        if (checkuserpass == true){
//                            Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(), Home.class);
//                            startActivity(intent);
//                        }else {
//                            Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                        }
                    }
                }
            });
        }
        private boolean validateUser(EditText username){
            String userInput = username.getText().toString();

            if (Patterns.EMAIL_ADDRESS.matcher(userInput).matches()){
                Toast.makeText(MainActivity.this, "Email Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                return true;
            }else {
                Toast.makeText(MainActivity.this, "Invalid Email Successfully", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    private boolean validatePass(EditText password){
        String passInput = password.getText().toString();

        if (PASSWORD_PATTERN.matcher(passInput).matches()){
            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            return true;
        }else {
            Toast.makeText(MainActivity.this, "Password too weak", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}