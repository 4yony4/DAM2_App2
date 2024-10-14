package org.eurekamps.dam2_app2.home_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.eurekamps.dam2_app2.R
import org.eurekamps.dam2_app2.singletone.DataHolder


/**
 * A simple [Fragment] subclass.
 * Use the [HomeProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeProfileFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgProfileSelected:ImageView=view.findViewById(R.id.ivProfileSelected)
        Picasso.get().load(DataHolder.fbProfileUserSelected?.sImgUrl).into(imgProfileSelected)
    }

}