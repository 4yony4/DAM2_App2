package org.eurekamps.dam2_app2.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.R
import java.util.Timer
import java.util.TimerTask


class SplashFragment : Fragment() {

    lateinit var progressBar2:ProgressBar
    var iProgress:Int=0
    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iProgress=0
        startTimer()
        auth=FirebaseAuth.getInstance()
        auth.signOut()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar2=view.findViewById(R.id.progressBar2)

    }

    fun comprobarUsuarioLogeado(){
        if(auth.currentUser!=null){
            val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
            requireActivity().startActivity(intentHomeActivity)
            requireActivity().finish()
        }
        else{
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.auth_nav, true)
                .build()

            findNavController().navigate(R.id.action_splashFragment_to_loginFragment, null, navOptions)
            //findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

        }
    }

    fun startTimer() {
        val timer = Timer()
        var counter = 0
        val totalRuns = 4

        timer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    // Code to execute on the main thread after 5 seconds
                    // For example, updating UI elements
                    //findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    counter++
                    progressBar2.progress=(counter)*20

                    if(counter==totalRuns){
                        timer.cancel()
                        comprobarUsuarioLogeado()
                    }
                }

            }
        }, 0,1000)
    }

}