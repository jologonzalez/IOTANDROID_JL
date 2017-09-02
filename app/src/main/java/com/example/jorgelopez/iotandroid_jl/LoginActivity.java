package com.example.jorgelopez.iotandroid_jl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtCorreo)
    EditText txtCorreo;

    @BindView(R.id.txtContra)
    EditText txtContra;

    @BindView(R.id.btnIngresar)
    Button btnIngresar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.btnIngresar)
    public void clickIngresar() {

        String correo = txtCorreo.getText().toString();
        String contra = txtContra.getText().toString();

        btnIngresar.setEnabled(false);

        auth.signInWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    Intent intent = new Intent(LoginActivity.this, ControlActivity.class);

                    startActivity(intent);

                    finish();
                }

            }
        });

    }

    @OnClick(R.id.btnRegistrar)
    public void clickRegistrar(){

        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);

        startActivity(intent);

    }
}
