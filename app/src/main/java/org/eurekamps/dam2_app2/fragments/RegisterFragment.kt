package org.eurekamps.dam2_app2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.R

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(),OnClickListener {

    lateinit var btnRegistrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnRegistrar.id){
            val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
            requireActivity().startActivity(intentHomeActivity)
            requireActivity().finish()
        }
        else {
            /*val fragmentoLogin:LoginFragment= LoginFragment()

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragmentoLogin)
            //transaction.addToBackStack(null) // Optional: allows the user to navigate back
            transaction.commit()

             */
        }
    }
}