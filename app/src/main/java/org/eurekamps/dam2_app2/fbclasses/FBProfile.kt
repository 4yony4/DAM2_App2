package org.eurekamps.dam2_app2.fbclasses

import com.google.firebase.Timestamp

data class FBProfile(val name: String? = null,
                     val age: Int = 0,
                     val birthdate:Timestamp? = null,
                     val hobbies:List<String>? = null,
                     val height:Double = 0.0,
)
