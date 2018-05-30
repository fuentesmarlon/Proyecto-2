package olakase.proyectoobjetos;

import android.content.Intent;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Opciones extends AppCompatActivity {
    Button conductor;
    Button pasajero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        //Enlazar variable con objetos
        conductor = (Button) findViewById(R.id.btnConductor);
        /*Nos dirige asi la ventana de la coductor*/
        conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irConductor = new Intent(Opciones.this, Conductor.class);
                Opciones.this.startActivity(irConductor);
            }
        });
        //Enlazar variable con objetos
        pasajero = (Button) findViewById(R.id.btnPasajero);
        /*Clase anonima para llegar a la clase Pasajero*/
        pasajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irpasajero = new Intent(Opciones.this, Pasajero.class);
                Opciones.this.startActivity(irpasajero);
            }
        });
    }
}
