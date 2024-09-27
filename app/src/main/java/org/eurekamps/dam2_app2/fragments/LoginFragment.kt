package org.eurekamps.dam2_app2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        btnLogin.setOnClickListener(this)
        btnRegistrar.setOnClickListener(this)

    }



    override fun onClick(p0: View?) {
        val fragmentoRegistro:RegisterFragment= RegisterFragment()

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragmentoRegistro)
        //transaction.addToBackStack(null) // Optional: allows the user to navigate back
        transaction.commit()

    }
}