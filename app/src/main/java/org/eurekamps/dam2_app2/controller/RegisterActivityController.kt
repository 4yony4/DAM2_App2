package org.eurekamps.dam2_app2.controller

import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.eurekamps.dam2_app2.RegistroActivity


class RegisterActivityController(val registerActivity: RegistroActivity): OnClickListener {

    val TAG:String = "RegisterActivityController"

    private var auth: FirebaseAuth

    init {
        auth = Firebase.auth
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            registerActivity.btnRegistrar.id->{
                val username=registerActivity.edTxtUsuario.text.toString()
                val password=registerActivity.edTxtPassword.text.toString()
                val repetirPass=registerActivity.edTxtRepetirPassword.text.toString()

                if(password==repetirPass){
                    if(isValidPassword(password)){
                        auth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(registerActivity) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success")
                                    registerActivity.finish()

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                                }
                            }
                    }
                    else{
                        Log.w(TAG,"PASSWORD NO CUMPLE CON EL REGEX")
                    }
                }
                else{//CONTRASEÑAS NO COIINCIDEN

                }

            }
            registerActivity.btnVolver.id->{
                registerActivity.finish()

            }
        }
    }

    fun isValidPassword(password: String): Boolean {
        // Regex pattern for password validation
        val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"

        // Check if the password matches the pattern
        return password.matches(passwordRegex.toRegex())
    }
}