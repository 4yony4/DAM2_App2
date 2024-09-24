package org.eurekamps.dam2_app2.controller

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.MainActivity

class MainActivityController(val mainActivity: MainActivity):OnClickListener {


    private var auth: FirebaseAuth

    init {
        auth = Firebase.auth
    }

    override fun onClick(p0: View?) {

        if(p0!!.id==mainActivity.btnLogin.id){
            Log.v("MainActivity","ESTA INTENTANDO LOGEAR CON USUARIO: "+mainActivity.edTxtUsuario.text.toString()+"" +
                    " Y CON PASSWORD: "+mainActivity.edTxtPassword.text.toString())
            val email=mainActivity.edTxtUsuario.text.toString()
            val password=mainActivity.edTxtPassword.text.toString()

            val taskLogin=auth.signInWithEmailAndPassword(email, password)
            taskLogin.addOnCompleteListener(mainActivity) { task ->
                    if (task.isSuccessful) {
                        irAHomeActivity()
                    } else {
                        Log.w("MainActivityController", "signInWithEmail:failure", task.exception)
                        mainActivity.miToast.show()
                    }
                }


        }
        else if(p0!!.id==mainActivity.btnRegistrar.id){
            println("!!!!!!!!!!!------>>>>>>>>>> BOTON REGISTRO")

        }
    }

    fun prueba(btn: Button?){
        btn!!.text="BUTON DE LOGIN"

    }

    fun irAHomeActivity(){
        val intentHomeActivity: Intent = Intent(mainActivity, HomeActivity::class.java)
        mainActivity.startActivity(intentHomeActivity)
        mainActivity.finish()
    }

}