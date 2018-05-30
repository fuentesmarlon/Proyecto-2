package olakase.proyectoobjetos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Conductor extends AppCompatActivity {
    /*Atributo de la base de datos*/
    private DatabaseReference mDatabase;

    /*Atributos de la clase*/
    EditText txtNumeroPasajero;
    EditText txtColor;
    EditText txtMarca;
    EditText txtTipoCarro;
    Button btnNotificacion;
    Button btnCancelar;
    private String tipo;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);
        /*Atributos para cada uno de los textField*/
        txtMarca = (EditText) findViewById(R.id.txtMarca);
        txtColor = (EditText) findViewById(R.id.txtColor);
        txtNumeroPasajero = (EditText) findViewById(R.id.txtNumeroPasajero);
        txtTipoCarro = (EditText) findViewById(R.id.txtTipoCarro);

        /*Listener del boton notificacion*/
        btnNotificacion = (Button) findViewById(R.id.btnNotificacion);
        btnNotificacion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                /*Atributos para guardar cada uno de los datos*/
                String marca = String.valueOf(txtMarca.getText());
                String color = String.valueOf(txtColor.getText());
                String numero = String.valueOf(txtNumeroPasajero.getText());
                tipo = String.valueOf(txtTipoCarro.getText());
                /*Datos que se agregan a la database*/
                database.child("Carros disponibles").child("placa "+tipo).child("Color").setValue(color);
                database.child("Carros disponibles").child("placa "+tipo).child("Marca").setValue(marca);
                database.child("Carros disponibles").child("placa "+tipo).child("Numero de Pasajero").setValue(numero);
                //database.child("Carros disponibles").child(tipo).child("Tipo de Carro").setValue(tipo);
                Limpiar();
                btnNotificacion.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setEnabled(false);
        btnCancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                /*Cancela la operacion de los text*/
                database.child("Carros disponibles").child("placa "+tipo).removeValue();
                btnNotificacion.setEnabled(true);
                btnCancelar.setEnabled(false);
            }
        });
    }

    public void Limpiar(){
        txtMarca.setText("");
        txtColor.setText("");
        txtNumeroPasajero.setText("");
        txtTipoCarro.setText("");
    }
}
