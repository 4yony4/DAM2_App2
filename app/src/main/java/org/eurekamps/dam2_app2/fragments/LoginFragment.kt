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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.R
import org.eurekamps.dam2_app2.fbclasses.FBProfile


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
    lateinit var db:FirebaseFirestore
    val TAG = "LoginFragment"

    companion object{
        var miPerfil:FBProfile?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=FirebaseAuth.getInstance()
        db = Firebase.firestore
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


    fun comprobarPerfil(){

        val docRef = db.collection("Profiles").document(auth.currentUser!!.uid)
        docRef.get().addOnSuccessListener { document ->
                if (document.data != null) {
                    miPerfil = document.toObject(FBProfile::class.java)

                    Log.d(TAG, "DocumentSnapshot data: ${document.data!!}")
                    Log.d(TAG, "Mi Perfil data NAME: ${miPerfil!!.name}")

                    val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
                    requireActivity().startActivity(intentHomeActivity)
                    requireActivity().finish()

                } else {
                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                    Log.d(TAG, "No existe el documento con los datos del perfil para esta ID")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        /*val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
        requireActivity().startActivity(intentHomeActivity)
        requireActivity().finish()*/
    }

    override fun onClick(p0: View?) {

        if(p0!!.id==btnLogin.id){
            val email=edTxtUsuario.text.toString()
            val password=edTxtPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                val taskLogin=auth.signInWithEmailAndPassword(email, password)
                taskLogin.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        comprobarPerfil()
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