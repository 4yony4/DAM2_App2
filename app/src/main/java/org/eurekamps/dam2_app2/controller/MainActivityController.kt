package org.eurekamps.dam2_app2.controller

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.MainActivity

class MainActivityController(val mainActivity: MainActivity):OnClickListener {


    override fun onClick(p0: View?) {
        /*when(p0!!.id){
            btnLogin.id->{

            }
        }*/

        if(p0!!.id==mainActivity.btnLogin.id){
            Log.v("MainActivity","ESTA INTENTANDO LOGEAR CON USUARIO: "+mainActivity.edTxtUsuario.text.toString()+"" +
                    " Y CON PASSWORD: "+mainActivity.edTxtPassword.text.toString())

            if(mainActivity.edTxtUsuario.text.toString()=="yony@yony.com" &&
                mainActivity.edTxtPassword.text.toString()=="1234567890"){
                irAHomeActivity()
            }
            else{

                mainActivity.miToast.show()
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