package org.eurekamps.dam2_app2.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import org.eurekamps.dam2_app2.R
import java.util.Timer
import java.util.TimerTask


class SplashFragment : Fragment() {

    lateinit var progressBar2:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTimer()
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
        progressBar2.progress=50
    }

    fun startTimer() {
        val timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    // Code to execute on the main thread after 5 seconds
                    // For example, updating UI elements
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
                // Code to execute after 5 seconds

            }
        }, 5000) // 5000 milliseconds = 5 seconds
    }

}