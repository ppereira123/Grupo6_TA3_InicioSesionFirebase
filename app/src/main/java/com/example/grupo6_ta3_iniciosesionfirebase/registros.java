package com.example.grupo6_ta3_iniciosesionfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class registros extends AppCompatActivity {
    DatabaseReference db_reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Grupo").child("Grupo6");
        leerRegistros();
    }

    private void leerRegistros() {
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mostrarRegistrosPorPantalla(snapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }
    public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){
        LinearLayout contTemp = (LinearLayout) findViewById(R.id.ContenedorTemp);
        LinearLayout contHum = (LinearLayout) findViewById(R.id.ContenedorHum);

        String tempVal = String.valueOf(snapshot.child("payload_fields").child("temperatura").getValue());
        String humVal = String.valueOf(snapshot.child("payload_fields").child("humedad").getValue());

        TextView temp = new TextView(getApplicationContext());
        temp.setText(tempVal+" °C");
        contTemp.addView(temp);

        TextView hum = new TextView(getApplicationContext());
        hum.setText(humVal+" %");
        contHum.addView(hum);
    }

}