package org.eurekamps.dam2_app2

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),OnClickListener {

    lateinit var btnLogin:Button
    lateinit var btnRegistrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnLogin=findViewById(R.id.button)
        btnRegistrar=findViewById(R.id.button2)
        btnLogin.setOnClickListener(this)
        btnRegistrar.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnLogin.id){
            println("!!!!!!!!!!!------>>>>>>>>>> BOTON LOGIN")
        }
        else if(p0!!.id==btnRegistrar.id){
            println("!!!!!!!!!!!------>>>>>>>>>> BOTON REGISTRO")
        }


    }


}