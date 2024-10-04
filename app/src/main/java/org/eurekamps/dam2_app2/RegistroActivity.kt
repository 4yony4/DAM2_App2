package org.eurekamps.dam2_app2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.eurekamps.dam2_app2.controller.RegisterActivityController

class RegistroActivity : AppCompatActivity() {

    lateinit var registerActivityController: RegisterActivityController
    lateinit var btnRegistrar: Button
    lateinit var edTxtPassword: EditText
    lateinit var edTxtRepetirPassword: EditText
    lateinit var edTxtUsuario: EditText
    lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        registerActivityController= RegisterActivityController(this)

        btnRegistrar=findViewById(R.id.button)
        btnVolver=findViewById(R.id.button2)
        edTxtPassword=findViewById(R.id.editTextTextPassword)
        edTxtUsuario=findViewById(R.id.editTextTextEmailAddress)
        edTxtRepetirPassword=findViewById(R.id.editTextTextPassword2)


        btnRegistrar.setOnClickListener(registerActivityController)
        btnVolver.setOnClickListener(registerActivityController)



    }
}