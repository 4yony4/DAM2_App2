package org.eurekamps.dam2_app2.home_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.eurekamps.dam2_app2.R
import org.eurekamps.dam2_app2.adapters.RvListProfilesAdapter
import org.eurekamps.dam2_app2.fbclasses.FBProfile

class ListProfilesFragment : Fragment() {

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
        val profilesList = listOf(FBProfile(name = "Pablo", age = 19),
            FBProfile(name = "Andrea"),FBProfile(name = "Hartman"),
            FBProfile(name = "Alejandro"))
        val rvListProfilesAdapter=RvListProfilesAdapter(profilesList)
        rvListProfiles.layoutManager = LinearLayoutManager(requireContext())
        rvListProfiles.adapter=rvListProfilesAdapter


    }

}