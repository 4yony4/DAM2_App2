package org.eurekamps.dam2_app2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.R


class ProfileFragment : Fragment(),OnClickListener {

    lateinit var btnGuarder:Button
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth

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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGuarder=view.findViewById(R.id.button7)
        btnGuarder.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnGuarder.id){
            val profiles = db.collection("Profiles")

            val data1 = hashMapOf(
                "name" to "Yony",
                "age" to 40,
            )
            profiles.document(auth.currentUser!!.uid).set(data1).addOnSuccessListener {
                val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
                requireActivity().startActivity(intentHomeActivity)
                requireActivity().finish()
            }
            .addOnFailureListener {
                

            }

        }
    }
}