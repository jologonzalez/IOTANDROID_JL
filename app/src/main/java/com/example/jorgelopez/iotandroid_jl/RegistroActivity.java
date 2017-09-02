package com.example.jorgelopez.iotandroid_jl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgelopez.iotandroid_jl.entidades.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity {

    @BindView(R.id.txtNombre)
    EditText txtNombre;

    @BindView(R.id.txtCorreo)
    EditText txtCorreo;

    @BindView(R.id.txtContra)
    EditText txtContra;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    private FirebaseAuth auth;

    private FirebaseDatabase database;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Usuarios");

    }

    @OnClick(R.id.btnRegistrar)
    public void clickRegistrar() {

        final String nombre = txtNombre.getText().toString();
        String email = txtCorreo.getText().toString();
        String contra = txtContra.getText().toString();

        btnRegistrar.setEnabled(false);

        auth.createUserWithEmailAndPassword(email,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    final String Uid = task.getResult().getUser().getUid();
                    task.getResult().getUser().getToken(true)
                            .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {

                            if(task.isSuccessful()){

                                final String nomUsuario = nombre;

                                Usuario objUsuario = new Usuario();

                                objUsuario.setNombres(nombre);
                                objUsuario.setToken(task.getResult().getToken());

                                reference.child(Uid).setValue(objUsuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Intent intent = new Intent(RegistroActivity.this, ControlActivity.class);

                                        intent.putExtra("nomUsuario", nomUsuario);

                                        startActivity(intent);

                                        finish();

                                    }
                                });


                            }else {

                                Toast.makeText(RegistroActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });

    }

    @OnClick(R.id.textView)
    public void clickYaTengoUsusario() {

        textView.setEnabled(false);

        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);

        startActivity(intent);

        finish();

    }

}