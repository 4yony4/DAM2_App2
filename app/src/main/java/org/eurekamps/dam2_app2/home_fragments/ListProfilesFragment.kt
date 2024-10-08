package org.eurekamps.dam2_app2.home_fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.eurekamps.dam2_app2.R
import org.eurekamps.dam2_app2.adapters.RvListProfilesAdapter
import org.eurekamps.dam2_app2.fbclasses.FBProfile
import java.util.Timer
import java.util.TimerTask

class ListProfilesFragment : Fragment() {

    val db = Firebase.firestore

    lateinit var rvListProfiles:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_profiles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvListProfiles=view.findViewById(R.id.rvListProfiles)
        var profilesList = mutableListOf(FBProfile(name = "Pablo", age = 19),
            FBProfile(name = "Andrea"),FBProfile(name = "Hartman"),
            FBProfile(name = "Alejandro"))

        val rvListProfilesAdapter=RvListProfilesAdapter(profilesList)
        rvListProfiles.layoutManager = LinearLayoutManager(requireContext())
        rvListProfiles.adapter=rvListProfilesAdapter

        val docRef = db.collection("Profiles")
        /*docRef.get().addOnSuccessListener { resultQuery ->
            profilesList.clear()
            rvListProfilesAdapter.notifyItemRangeRemoved(0,profilesList.size-1)

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({

                if(!resultQuery.isEmpty){
                    val posIni=profilesList.size
                    for (document in resultQuery.documents){
                        val fbProfileTemp=document.toObject(FBProfile::class.java)
                        profilesList.add(fbProfileTemp!!)
                        //Log.v("FIREBASE","NOMBRE DOCUMENTO DESCARGO: "+fbProfileTemp!!.name)
                    }
                    val posFin=profilesList.size-1
                    rvListProfilesAdapter.notifyItemRangeInserted(posIni,posFin)
                    //rvListProfilesAdapter.notifyDataSetChanged()
                }

            }, 2000)
        }.addOnFailureListener {}
        */

        docRef.addSnapshotListener { resultQuery, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (resultQuery != null && !resultQuery.isEmpty) {
                val tempSize=profilesList.size
                profilesList.clear()
                rvListProfilesAdapter.notifyItemRangeRemoved(0,tempSize)


                val posIni=0
                for (document in resultQuery.documents){
                    val fbProfileTemp=document.toObject(FBProfile::class.java)
                    profilesList.add(fbProfileTemp!!)
                    //Log.v("FIREBASE","NOMBRE DOCUMENTO DESCARGO: "+fbProfileTemp!!.name)
                }
                val posFin=profilesList.size
                rvListProfilesAdapter.notifyItemRangeInserted(posIni,posFin)
                //rvListProfilesAdapter.notifyDataSetChanged()
            } else {

            }
        }

    }

}