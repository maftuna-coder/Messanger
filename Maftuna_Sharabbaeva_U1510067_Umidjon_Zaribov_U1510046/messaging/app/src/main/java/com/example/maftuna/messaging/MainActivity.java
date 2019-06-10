package com.example.maftuna.messaging;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//main activity
public class MainActivity extends AppCompatActivity {
    Button explicit_btn; //for when login successful, then to go to messenger
    private EditText email, pass;
    private FirebaseAuth firebaseAuth;
    private ListView messagesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.email);
        pass = findViewById(R.id.passwprd);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //when network problem, it will say loading message
    public void btnLoginAction(View view) {

        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("loading...");
        pd.show();
        pd.show();
        //here when user try to log in
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        pd.dismiss();
                        //if log in is successful then it will say successful message
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT);
                            Intent intent = new Intent(MainActivity.this, Profile.class);
                            //when log in button is clicked with registerd account it will go to messenger
                            explicit_btn = (Button) findViewById(R.id.BtnLogin);
                            explicit_btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(getBaseContext(), messagestored.class);
                                    startActivity(intent);


                                }
                            });
                        //if there is problem with internet or other errors, it will print error message
                        } else {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public void btnRegisterAction(View view) {

        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);


    }
}

