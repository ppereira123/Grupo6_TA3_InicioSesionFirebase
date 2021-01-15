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
        db_reference = FirebaseDatabase.getInstance().getReference().child("Grupo").child("Grupo6").child("Registros");
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
        LinearLayout contAxis = (LinearLayout) findViewById(R.id.ContenedorAxis);

        String tempVal = String.valueOf(snapshot.child("temp").getValue());
        String axisVal = String.valueOf(snapshot.child("axi").getValue());

        TextView temp = new TextView(getApplicationContext());
        temp.setText(tempVal+" °C");
        contTemp.addView(temp);

        TextView axis = new TextView(getApplicationContext());
        axis.setText(axisVal);
        contAxis.addView(axis);
    }

}