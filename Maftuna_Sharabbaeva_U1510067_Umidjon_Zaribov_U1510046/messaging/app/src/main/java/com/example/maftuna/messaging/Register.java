package com.example.maftuna.messaging;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText email, pass;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.passwprd);
        firebaseAuth = FirebaseAuth.getInstance();

    }
    //when internet has some problem, it will show loading message during waiting button works
    public void btnRegisterAction(View view) {
        final ProgressDialog pd = new ProgressDialog(Register.this);
        pd.setMessage("loading...");
        pd.show();
        pd.show();

        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        pd.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }
}




