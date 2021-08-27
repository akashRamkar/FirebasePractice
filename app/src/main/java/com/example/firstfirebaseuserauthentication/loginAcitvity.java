package com.example.firstfirebaseuserauthentication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginAcitvity extends AppCompatActivity {
TextInputLayout email,pwd;
MaterialButton loginBtn;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        email=findViewById(R.id.userEmail);
        pwd=findViewById(R.id.password);
        loginBtn=findViewById(R.id.login_btn);
        mAuth=FirebaseAuth.getInstance();
    }

    public void loginUser(View view) {
        String usermail,userpass;
        usermail=email.getEditText().getText().toString();
        userpass=pwd.getEditText().getText().toString();
        mAuth.signInWithEmailAndPassword(usermail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(loginAcitvity.this, "login succesfull", Toast.LENGTH_SHORT).show();
                    System.out.println(mAuth.getCurrentUser().getUid());
                }
                else{
                    System.out.println("invalid user");
                }
            }
        });


    }

    public void logoutUser(View view) {
        mAuth.signOut();
        startActivity(new Intent(loginAcitvity.this,MainActivity.class));
        finish();
    }
}