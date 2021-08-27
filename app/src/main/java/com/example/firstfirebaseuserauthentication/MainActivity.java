package com.example.firstfirebaseuserauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout etName, etEmail, etPass;
    private Button signupBtn;
    private static String password, email,name;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.user_name_etv);
        etEmail = findViewById(R.id.email);
        firebaseAuth = FirebaseAuth.getInstance();
        etPass = findViewById(R.id.password_etv);

    }

    private boolean performUserInputPasswordLengthCheck() {
         name = etName.getEditText().getText().toString();
        email = etEmail.getEditText().getText().toString();
        password = etPass.getEditText().getText().toString();
        etName.getEditText().setText(null);
        etEmail.getEditText().setText(null);
        etPass.getEditText().setText(null);

        if ((password.length() < 8)||(TextUtils.isEmpty(email))||(TextUtils.isEmpty(name))) {
            return false;
        }
        return true;
    }

    public void addIntoDataBase(View view) {
        if (performUserInputPasswordLengthCheck()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password);
            Toast.makeText(this, "registerd succesfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "password must contains atleast 8 charachers", Toast.LENGTH_LONG).show();
        }
    }

    public void gotoSignInActivity(View view) {
        startActivity(new Intent(MainActivity.this,loginAcitvity.class));
        finish();
    }
}