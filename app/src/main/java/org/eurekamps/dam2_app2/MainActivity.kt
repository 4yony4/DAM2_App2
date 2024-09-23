package org.eurekamps.dam2_app2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.eurekamps.dam2_app2.controller.MainActivityController

class MainActivity : AppCompatActivity() {

    lateinit var btnLogin:Button
    lateinit var btnRegistrar:Button
    lateinit var edTxtPassword:EditText
    lateinit var edTxtUsuario:EditText
    lateinit var miToast: Toast
    var usuarioLogeado:Boolean=false
    lateinit var mainActivityController: MainActivityController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        mainActivityController= MainActivityController(this)

        btnLogin=findViewById(R.id.button)
        btnRegistrar=findViewById(R.id.button2)
        edTxtPassword=findViewById(R.id.editTextTextPassword)
        edTxtUsuario=findViewById(R.id.editTextTextEmailAddress)

        btnLogin.setOnClickListener(mainActivityController)
        btnRegistrar.setOnClickListener(mainActivityController)

        mainActivityController.prueba(btnLogin)

        miToast=Toast.makeText(this,"USUARIO o CONTRASEÑA MAL",Toast.LENGTH_LONG)

        if(usuarioLogeado==true){
            mainActivityController.irAHomeActivity()
        }

    }
}