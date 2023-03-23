package com.ragtag.ttub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ragtag.ttub.databinding.FragmentMapsBinding
import com.ragtag.ttub.ui.maps.MapsViewModel
import kotlinx.android.synthetic.main.fragment_maps.*

class CreateMonster : DialogFragment() {

    protected var ctx: Context? = null
    var pickedPhoto : Uri? = null
    var pickedBitMap : Bitmap? = null
    private lateinit var _binding: FragmentMapsBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapsViewModel =
            ViewModelProvider(this).get(MapsViewModel::class.java)

        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMaps
        mapsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val imageView = root.findViewById<ImageView>(R.id.imageView)
//        imageView.setImageResource(R.drawable.ic_dashboard_black_24dp)
        imageView.setOnClickListener { view ->
            pickedPhoto(view) //android.app.ContextImpl$ApplicationContentResolver@9a4c52d
//            startActivity(Intent(activity, IINE::class.java))
        }
        //called pickedphoto() when users click the image view


        return root
    }

    fun pickedPhoto (view: View){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
            //This function is created if the user didnt recieve permission the request code is set to 1 to get the permission.
        } else {
            val galleryIntext = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntext,2)
        }   //call start activity for result to show the selected image
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            val galleryIntext = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntext,2)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

//    public override fun getContext(): Context? {
//        return super.getContext()
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPhoto = data.data
            val myLayout: View = LayoutInflater.from(this.context).inflate(com.ragtag.ttub.R.layout.fragment_maps, null)
            if (Build.VERSION.SDK_INT >= 28){
                val source = ImageDecoder.createSource(this.requireActivity().contentResolver, pickedPhoto!!)
                pickedBitMap = ImageDecoder.decodeBitmap(source)

                imageView.setImageBitmap(pickedBitMap)
                // if newer phone uses ImageDecoder.createSource to create source first
            } else {
                pickedBitMap = MediaStore.Images.Media.getBitmap(this.requireActivity().contentResolver,pickedPhoto)
                imageView.setImageBitmap(pickedBitMap)
            }   //uses MediaStore.Images.Media.getBitmap if older phone
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}