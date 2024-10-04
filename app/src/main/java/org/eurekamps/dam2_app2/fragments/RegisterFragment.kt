package org.eurekamps.dam2_app2.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.R

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(),OnClickListener {

    lateinit var btnRegistrar:Button
    lateinit var edTxtPassword: EditText
    lateinit var edTxtRepetirPassword: EditText
    lateinit var edTxtUsuario: EditText
    lateinit var btnVolver: Button
    lateinit var auth: FirebaseAuth

    val TAG="RegisterFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRegistrar=view.findViewById(R.id.button5)
        btnVolver=view.findViewById(R.id.button6)
        edTxtUsuario=view.findViewById(R.id.editTextTextEmailAddress2)
        edTxtPassword=view.findViewById(R.id.editTextTextPassword4)
        edTxtRepetirPassword=view.findViewById(R.id.editTextTextPassword5)

        btnRegistrar.setOnClickListener(this)
        btnVolver.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnRegistrar.id){
            val username=edTxtUsuario.text.toString()
            val password=edTxtPassword.text.toString()
            val repetirPass=edTxtRepetirPassword.text.toString()

            if(password==repetirPass){
                //if(isValidPassword(password)){
                    auth.createUserWithEmailAndPassword(username, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)

                            }
                        }
            }
            else{//CONTRASEÑAS NO COIINCIDEN

            }
        }
        else {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}