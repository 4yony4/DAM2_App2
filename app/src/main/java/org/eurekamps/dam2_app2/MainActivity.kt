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

class MainActivity : AppCompatActivity(),OnClickListener {

    lateinit var btnLogin:Button
    lateinit var btnRegistrar:Button
    lateinit var edTxtPassword:EditText
    lateinit var edTxtUsuario:EditText
    lateinit var miToast: Toast
    var usuarioLogeado:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnLogin=findViewById(R.id.button)
        btnRegistrar=findViewById(R.id.button2)
        edTxtPassword=findViewById(R.id.editTextTextPassword)
        edTxtUsuario=findViewById(R.id.editTextTextEmailAddress)

        btnLogin.setOnClickListener(this)
        btnRegistrar.setOnClickListener(this)

        prueba(btnLogin)

        miToast=Toast.makeText(this,"USUARIO o CONTRASEÑA MAL",Toast.LENGTH_LONG)

        if(usuarioLogeado==true){
            irAHomeActivity()
        }

    }

    fun prueba(btn:Button?){
        btn!!.text="BUTON DE LOGIN"

    }

    fun irAHomeActivity(){
        val intentHomeActivity:Intent=Intent(this,HomeActivity::class.java)
        this.startActivity(intentHomeActivity)
        this.finish()
    }

    override fun onClick(p0: View?) {
        /*when(p0!!.id){
            btnLogin.id->{

            }
        }*/

        if(p0!!.id==btnLogin.id){
            Log.v("MainActivity","ESTA INTENTANDO LOGEAR CON USUARIO: "+edTxtUsuario.text.toString()+"" +
                    " Y CON PASSWORD: "+edTxtPassword.text.toString())

            if(edTxtUsuario.text.toString()=="yony@yony.com" &&
                edTxtPassword.text.toString()=="1234567890"){
                irAHomeActivity()
            }
            else{

                miToast.show()
            }


        }
        else if(p0!!.id==btnRegistrar.id){
            println("!!!!!!!!!!!------>>>>>>>>>> BOTON REGISTRO")

        }
    }
}