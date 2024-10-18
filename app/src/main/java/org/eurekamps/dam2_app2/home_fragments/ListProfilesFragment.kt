package org.eurekamps.dam2_app2.home_fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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



    private val viewModelProfiles: ListProfilesViewModel by activityViewModels()

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

        val rvListProfilesAdapter=RvListProfilesAdapter(viewModelProfiles.sharedProfilesList.value!!,this)
        rvListProfiles.layoutManager = LinearLayoutManager(requireContext())
        rvListProfiles.adapter=rvListProfilesAdapter

        viewModelProfiles.sharedProfilesList.observe(viewLifecycleOwner) { value ->
            // Use the value, e.g., display it in a TextView
            rvListProfilesAdapter.listaDeProfiles=value
            rvListProfilesAdapter.notifyDataSetChanged()

        }

        viewModelProfiles.descargarDatos()

    }

}