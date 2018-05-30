package olakase.proyectoobjetos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import olakase.proyectoobjetos.objetos.FirebaseReferencias;
import olakase.proyectoobjetos.objetos.RegistroUsuarios;

public class Login extends AppCompatActivity {

    private static final String  TAG  = "adfAS";
    /*Edit text atributos*/
    EditText usuario;
    EditText contraseña;
    /*Atributos a utilizar*/
    Button registar;
    Button opciones;
    /*Base de datos*/
    private FirebaseAuth mAuth;
    /*Verificar los datos dentro de la base de datos*/
    //prjava.lang.Stringivate FirebaseAuth;
    com.google.firebase.auth.FirebaseAuth.AuthStateListener mAuthListener;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Intanciamos la base de autenticacion de firebase*/
        mAuth = FirebaseAuth.getInstance();
        /*Inicializamos los EditText*/
        usuario = (EditText) findViewById(R.id.txtUsuario);
        contraseña = (EditText) findViewById(R.id.txtContraseña);

       // nuevoUser = (EditText) findViewById(R.id.txtUsuarioR);
       // nuevaContraseña = (EditText) findViewById(R.id.txtContraseñaR);

        /*Indica si los datos estan ingresados y hace la comparacion*/
        opciones = (Button) findViewById(R.id.btnIngresar);

        //accion del boton
        opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInUsuario();
                //Login.this.startActivity(opcionesR);
                /*Nos dirige hacia otra ventana*/
            }
        });


        //Clase anonima
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    /*Viene la ventana de Opciones si los datos ingresaron correctamente*/
                    startActivity(new Intent(Login.this, Opciones.class));
                }
                else{
                    /*Manda un mensaje que los datos estan incorrectos.*/
                    Toast.makeText(Login.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        };


        /*Nos dirige hacia otra ventana*/
        registar = (Button) findViewById(R.id.btnRegistrar);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenReg = new Intent(Login.this, Registrar.class);
                Login.this.startActivity(intenReg);
            }
        });
        /*Implemetamos la base de datos*/
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        /*Referencia de la base de datos*/
        //DatabaseReference proyecto = database.getReference(FirebaseReferencias.Proyecto_Objetos);
    }

    //Void, sin parametros. Iniciar con el usuario  existente
    private void SignInUsuario(){
        //Guarda valores de los Edittext
        String email = usuario.getText().toString();
        String password = contraseña.getText().toString();
        //Proceso para iniciar
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        //manda mensaje de fallo
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(Login.this, "Fallo",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //cambia de ventana
                        else{
                            Intent intenReg = new Intent(Login.this, Opciones.class);
                            Login.this.startActivity(intenReg);
                        }

                        // ...
                    }
                });
    }




}
