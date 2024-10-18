package org.eurekamps.dam2_app2.viewholders

import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.eurekamps.dam2_app2.R
import org.eurekamps.dam2_app2.fbclasses.FBProfile
import org.eurekamps.dam2_app2.home_fragments.ListProfilesViewModel
import org.eurekamps.dam2_app2.singletone.DataHolder

class StringViewHolder(val view: View,val fragmentoPadre: Fragment) : RecyclerView.ViewHolder(view),OnClickListener {
    val textView: TextView = view.findViewById(R.id.list_cell1_tvname)
    val tvMas: TextView = view.findViewById(R.id.list_cell1_tvmas)
    val ivAvatar:ImageView = view.findViewById(R.id.list_cell1_ivAvatar)
    val constrains : ConstraintLayout = view.findViewById(R.id.list1_cell_raiz)
    lateinit var fbProfile: FBProfile



    init {
        view.setOnClickListener(this)
    }

    fun asignarDatos(profile:FBProfile){
        textView.text= profile.name
        tvMas.text=profile.hobbies.toString()
        Picasso.get().load(profile.sImgUrl).into(ivAvatar)
        fbProfile=profile
    }

    override fun onClick(p0: View?) {
        val viewModel: ListProfilesViewModel by fragmentoPadre.activityViewModels()

        viewModel.setProfile(fbProfile)

        fragmentoPadre.findNavController().navigate(R.id.action_listProfilesFragment_to_homeProfileFragment)
        Log.v("StringViewHolder","------>>>>>> CLICK CELDA "+fbProfile.name)

    }

}