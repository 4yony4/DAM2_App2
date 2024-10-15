package org.eurekamps.dam2_app2.fbclasses

import com.google.firebase.Timestamp

data class FBProfile(var name: String? = null,
                     var age: Int = 0,
                     var birthdate:Timestamp? = null,
                     var hobbies:List<String>? = null,
                     var height:Double = 0.0,
                     var colorPelo:String? = null,
                     var sImgUrl:String? = null,
                     var sUID:String?=null
)
