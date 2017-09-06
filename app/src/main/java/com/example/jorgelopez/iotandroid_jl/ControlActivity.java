package com.example.jorgelopez.iotandroid_jl;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.jorgelopez.iotandroid_jl.entidades.Hogar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ControlActivity extends AppCompatActivity {

    @BindView(R.id.btnSala)
    Button btnSala;

    @BindView(R.id.btnHabitacion)
    Button btnHabitacion;

    @BindView(R.id.btnHabitacion2)
    Button btnHabitacion2;

    @BindView(R.id.btnCocina)
    Button btnCocina;

    private FirebaseAuth auth;

    private FirebaseDatabase database;

    private DatabaseReference reference;

    Hogar objHogar = new Hogar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        ButterKnife.bind(this);

        String nomUsuario = getIntent().getStringExtra("nomUsuario");

        //Linea para imprimir en consola
        //Log.i("ControlActivity", nomUsuario);

        setTitle("Bienvenido " + nomUsuario);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Hogar");

        datosHogar();

    }

    private void datosHogar(){

        String Uid = auth.getCurrentUser().getUid();

        reference.child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Hogar hogar = dataSnapshot.getValue(Hogar.class);

                if(hogar.getSala()==1){
                    btnSala.setBackgroundColor(Color.RED);
                }else{
                    btnSala.setBackgroundColor(Color.BLUE);
                }

                if(hogar.getHabitacion1()==1){
                    btnHabitacion.setBackgroundColor(Color.RED);
                }else{
                    btnHabitacion.setBackgroundColor(Color.BLUE);
                }

                if(hogar.getHabitacion2()==1){
                    btnHabitacion2.setBackgroundColor(Color.RED);
                }else{
                    btnHabitacion2.setBackgroundColor(Color.BLUE);
                }

                if(hogar.getCocina()==1){
                    btnCocina.setBackgroundColor(Color.RED);
                }else{
                    btnCocina.setBackgroundColor(Color.BLUE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.btnSala)
    public void clickSala(){

        String Uid = auth.getCurrentUser().getUid();

        if(objHogar.getSala()==0){
            objHogar.setSala(1);
            btnSala.setBackgroundColor(ContextCompat.getColor(this, R.color.rojo));
        }else if(objHogar.getSala()==1){
            objHogar.setSala(0);
            btnSala.setBackgroundColor(Color.BLUE);
        }

        reference.child(Uid).child("Sala").setValue(objHogar.getSala());

    }

    @OnClick(R.id.btnHabitacion)
    public void clickHabitacion(){

        String Uid = auth.getCurrentUser().getUid();

        if(objHogar.getHabitacion1()==0){
            objHogar.setHabitacion1(1);
            btnHabitacion.setBackgroundColor(Color.RED);
        }else if(objHogar.getHabitacion1()==1){
            objHogar.setHabitacion1(0);
            btnHabitacion.setBackgroundColor(Color.BLUE);
        }

        reference.child(Uid).child("Habitacion1").setValue(objHogar.getHabitacion1());

    }

    @OnClick(R.id.btnHabitacion2)
    public void clickHabitacion2(){

        String Uid = auth.getCurrentUser().getUid();

        if(objHogar.getHabitacion2()==0){
            objHogar.setHabitacion2(1);
            btnHabitacion2.setBackgroundColor(Color.RED);
        }else if(objHogar.getHabitacion2()==1){
            objHogar.setHabitacion2(0);
            btnHabitacion2.setBackgroundColor(Color.BLUE);
        }

        reference.child(Uid).child("Habitacion2").setValue(objHogar.getHabitacion2());

    }

    @OnClick(R.id.btnCocina)
    public void clickCocina(){

        String Uid = auth.getCurrentUser().getUid();

        if(objHogar.getCocina()==0){
            objHogar.setCocina(1);
            btnCocina.setBackgroundColor(Color.RED);
        }else if(objHogar.getCocina()==1){
            objHogar.setCocina(0);
            btnCocina.setBackgroundColor(Color.BLUE);
        }

        reference.child(Uid).child("Cocina").setValue(objHogar.getCocina());

    }

}
