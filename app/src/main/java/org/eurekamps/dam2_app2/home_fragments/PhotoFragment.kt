import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build.VERSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import org.eurekamps.dam2_app2.HomeActivity
import org.eurekamps.dam2_app2.R
import org.eurekamps.dam2_app2.fbclasses.FBProfile
import org.eurekamps.dam2_app2.home_fragments.ListProfilesViewModel
import org.eurekamps.dam2_app2.singletone.DataHolder
import java.io.ByteArrayOutputStream

class PhotoFragment : Fragment(),OnClickListener {

    private lateinit var imageView: ImageView
    val firebaseStorage= FirebaseStorage.getInstance()
    val storageRef = firebaseStorage.reference
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    private val viewModelListProfiles: ListProfilesViewModel by activityViewModels()

    // ActivityResultLauncher for capturing a picture from the camera
    /*private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        bitmap?.let {
            imageView.setImageBitmap(it)
            subirImagen()
        } ?: run {
            showToast("Failed to capture image")
        }
    }*/

    // ActivityResultLauncher for picking an image from the gallery
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageView.setImageURI(it)
            subirImagen()
        } ?: run {
            showToast("Failed to select image")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=FirebaseAuth.getInstance()
        db = Firebase.firestore
    }

    fun subirImagen(){
        val mountainsRef = storageRef.child("mountains.jpg")

        // Get the data from an ImageView as bytes
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->

            taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                val downloadURL = uri.toString() // This is the URL to the uploaded image
                val profiles = db.collection("Profiles")
                viewModelListProfiles.fbProfileUserSelected.value?.sImgUrl=downloadURL

                profiles.document(viewModelListProfiles.fbProfileUserSelected.value?.sUID!!).set(viewModelListProfiles.fbProfileUserSelected!!).addOnSuccessListener {
                    // Navigate after successful Firestore update
                    findNavController().navigate(R.id.action_photoFragment_to_homeProfileFragment)
                }.addOnFailureListener {
                    // Handle Firestore update failure
                }

            }.addOnFailureListener {
                // Handle failure to get download URL
            }

            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.imageView)
        Picasso.get().load(viewModelListProfiles.fbProfileUserSelected.value?.sImgUrl).into(imageView)

        // Set up buttons for opening camera and gallery
        view.findViewById<Button>(R.id.btnCamera).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnGallery).setOnClickListener(this)
    }


    private fun respuestaCamera(bitmap: Bitmap?){
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            subirImagen()
        } else {
            showToast("Failed to capture image")
        }
    }

    // Function to open the camera
    private fun openCamera() {
        registerForActivityResult(
            ActivityResultContracts.TakePicturePreview(),
            ::respuestaCamera).launch(null)

        //takePictureLauncher.launch(null)
    }

    // Function to open the gallery
    private fun openGallery() {
        pickImageLauncher.launch("image/*")
    }

    // Check for necessary permissions
    private fun checkPermissions(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        val storagePermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
        return cameraPermission == PackageManager.PERMISSION_GRANTED && storagePermission == PackageManager.PERMISSION_GRANTED
    }

    // Request camera and storage permissions
    /*private fun requestPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
            1001
        )
    }*/

    // ActivityResultLauncher for requesting permissions
    private val requestPermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
        val storageGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false

        if (cameraGranted && storageGranted) {
            showToast("Permissions granted")
        } else {
            showToast("Permissions denied")
        }
    }

    // Request necessary permissions using ActivityResultLauncher
    private fun requestPermissions() {
        requestPermissionsLauncher.launch(
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
        )
    }

    // Handle permission request result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1001) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                showToast("Permissions granted")
            } else {
                showToast("Permissions denied")
            }
        }
    }

    // Show toast message
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(p0: View?) {
        if(p0?.id==R.id.btnCamera){
            if (checkPermissions()) {
                openCamera()
            } else {
                requestPermissions()
            }
        }
        else if(p0?.id==R.id.btnGallery){
            if (checkPermissions()) {
                openGallery()
            } else {
                requestPermissions()
            }
        }
    }
}