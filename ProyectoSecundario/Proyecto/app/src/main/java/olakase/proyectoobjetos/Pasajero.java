package olakase.proyectoobjetos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pasajero extends AppCompatActivity {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static final String  TAG  = "available";

    /*Atributos de los text view*/
    TextView txtPlaca1;
    TextView txtPlaca2;
    TextView txtPlaca3;
    TextView txtColor1;
    TextView txtColor2;
    TextView txtColor3;
    TextView txtMarca1;
    TextView txtMarca2;
    TextView txtMarca3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasajero);
        /*Brinda el valor a los text view*/
        txtPlaca1 = (TextView) findViewById(R.id.txtPlaca1);
        txtPlaca2 = (TextView) findViewById(R.id.txtPlaca2);
        txtPlaca3 = (TextView) findViewById(R.id.txtPlaca3);
        txtColor1 = (TextView) findViewById(R.id.txtColor1);
        txtColor2 = (TextView) findViewById(R.id.txtColor2);
        txtColor3 = (TextView) findViewById(R.id.txtColor3);
        txtMarca1 = (TextView) findViewById(R.id.txtMarca1);
        txtMarca2 = (TextView) findViewById(R.id.txtMarca2);
        txtMarca3 = (TextView) findViewById(R.id.txtMarca3);




        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*Le da el valor al los text view*/
                txtPlaca1.setText(dataSnapshot.child("Carros disponibles").child("placa hkl728").child("Numero de Pasajero").getValue().toString());
                txtPlaca2.setText(dataSnapshot.child("Carros disponibles").child("placa hls472").child("Numero de Pasajero").getValue().toString());
                txtPlaca3.setText(dataSnapshot.child("Carros disponibles").child("placa ifo749").child("Numero de Pasajero").getValue().toString());
                txtColor1.setText(dataSnapshot.child("Carros disponibles").child("placa hkl728").child("Color").getValue().toString());
                txtColor2.setText(dataSnapshot.child("Carros disponibles").child("placa hls472").child("Color").getValue().toString());
                txtColor3.setText(dataSnapshot.child("Carros disponibles").child("placa ifo749").child("Color").getValue().toString());
                txtMarca1.setText(dataSnapshot.child("Carros disponibles").child("placa hkl728").child("Marca").getValue().toString());
                txtMarca2.setText(dataSnapshot.child("Carros disponibles").child("placa hls472").child("Marca").getValue().toString());
                txtMarca3.setText(dataSnapshot.child("Carros disponibles").child("placa ifo749").child("Marca").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}
