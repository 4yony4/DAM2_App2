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
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.R


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(),OnClickListener {
    lateinit var btnLogin: Button
    lateinit var btnRegistrar: Button
    lateinit var edTxtPassword: EditText
    lateinit var edTxtUsuario: EditText

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin=view.findViewById(R.id.button3)
        btnRegistrar=view.findViewById(R.id.button4)
        edTxtUsuario=view.findViewById(R.id.editTextText)
        edTxtPassword=view.findViewById(R.id.editTextTextPassword3)
        btnLogin.setOnClickListener(this)
        btnRegistrar.setOnClickListener(this)


        /*requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }*/
    }




    override fun onClick(p0: View?) {

        if(p0!!.id==btnLogin.id){
            val email=edTxtUsuario.text.toString()
            val password=edTxtPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                val taskLogin=auth.signInWithEmailAndPassword(email, password)
                taskLogin.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
                        requireActivity().startActivity(intentHomeActivity)
                        requireActivity().finish()
                    } else {
                        Log.w("MainActivityController", "signInWithEmail:failure", task.exception)
                        //requireActivity().miToast.show()
                    }
                }
            }
        }
        else {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)


            /*
           val fragmentoRegistro:RegisterFragment= RegisterFragment()


           val transaction = requireActivity().supportFragmentManager.beginTransaction()
           transaction.replace(R.id.fragmentContainerView, fragmentoRegistro)
           //transaction.addToBackStack(null) // Optional: allows the user to navigate back
           transaction.commit()
            */
        }
    }


}