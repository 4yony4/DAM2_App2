package org.eurekamps.dam2_app2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_app2.fragments.LoginFragment
import org.eurekamps.dam2_app2.singletone.DataHolder

class HomeActivity : AppCompatActivity() {

    lateinit var txtDatos:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtDatos=findViewById(R.id.textView)
        FirebaseAuth.getInstance().currentUser
        txtDatos.text=DataHolder.fbProfileUser!!.name

    }

    override fun onPause() {
        super.onPause()

    }
}