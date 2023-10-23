package com.edu.ufps.appturismo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.edu.ufps.appturismo.vista.Contenedor
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //DECLARACION DE ATRIBUTOS CON CARGA LENTA
    // CARGA LENTA LUEGO SE CARGA
    //lateinit var txtusuario : TextInputEditText
    private lateinit var auth: FirebaseAuth;
    val TAG : String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LLAMADO DE CARGA LENTA
       var txtusuario: TextInputEditText = findViewById(R.id.email)

        //SEGUNDA FORMA DE LLAMAR Y DELCARAR
        var txtclave : TextInputEditText = findViewById(R.id.clave)
        var btnlogin : Button = findViewById(R.id.btnLogin)
        auth = Firebase.auth

        btnlogin.setOnClickListener(){
            signIn(txtusuario.text.toString(),txtclave.text.toString())
        }

20

    }

    //MEOTODO PARA AUTENTICACION Y QUE NO SE TENGA QUE VOLVER A INCIAR SESION
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

      // --> a donde me debe llevar si estoy inicializadp // updateUI(currentUser)
    }

    fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                  //  updateUI(user)
                    Toast.makeText(
                        baseContext,
                        "BIENVENIDO PA",
                        Toast.LENGTH_SHORT,
                    ).show()

                    irInicio();

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "NO EXISTE USUARIO.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    //updateUI(null)
                }
            }
    }

    fun irInicio(){
        val intent = Intent(this, Contenedor::class.java)
        startActivity(intent)
    }
}