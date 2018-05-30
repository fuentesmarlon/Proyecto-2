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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registrar extends AppCompatActivity {
    //atributos
    private static final String TAG = "Registrando";
    EditText nuevoEmail;
    EditText nuevaContra;
    EditText confirmarContraseña;
    FirebaseAuth mAuth;
    Button nuevoRegistrar;
    @Override
    /**
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        /*Datos de nuevos usario*/
        //Enlazar objetos con las variables
        nuevoEmail = (EditText) findViewById(R.id.txtUsuarioR);
        nuevaContra = (EditText) findViewById(R.id.txtContraseñaR);
        confirmarContraseña = (EditText) findViewById(R.id.txtConfirmacion);
        mAuth = FirebaseAuth.getInstance();
        nuevoRegistrar = (Button) findViewById(R.id.btnNuevoUser);
        //listener del boton

            nuevoRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!(nuevaContra.getText().equals(confirmarContraseña.getText()))){
                        /*Manda a llamar al metodo crear usuario y envia a nueva ventanaa*/
                            if (!(nuevaContra.getText().length()<6 || nuevoEmail.getText().toString().equals(""))){
                                CrearUsuario();
                                Intent intenReg = new Intent(Registrar.this, Opciones.class);
                                Registrar.this.startActivity(intenReg);
                            }
                            //mensake de datos incorrectos
                            else{
                                Toast.makeText(Registrar.this, "Ingrese datos correctos, la contraseña debe contener mas de 6 caracteres",
                                Toast.LENGTH_SHORT).show();
                            }
                    } else {
                        Toast.makeText(Registrar.this, "Deben de ser iguales las contraseña",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

    }

    /**
     * procedimiento void sin parametros. Ingresa nuevo usuario en la base de datos
     */
    private void CrearUsuario(){
        //Guardan valores en variables
        String email = nuevoEmail.getText().toString();
        String password = nuevaContra.getText().toString();
        //pasa los valores de las variables al procedimiento
        mAuth.createUserWithEmailAndPassword(email, password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        //si la acción no fue exitosa, manda mensaje de error
                        if (!task.isSuccessful()) {
                            Toast.makeText(Registrar.this, "Ingrese datos correctos, la contraseña debe contener mas de 6 caracteres",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //si fue exitoso manda mensaje de exito
                        else if (task.isSuccessful()){
                            Toast.makeText(Registrar.this, "Datos Correctos",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

    }
    /*Verifica si el usuario esta ya iniciado, para,
     * no volverle a pedir que ingrese de nuevo la contraseña*/
}

