package com.example.secondquizproject

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.secondquizproject.Etudiant.EtudiantDataManager
import com.example.secondquizproject.Question.QuestionDataManager
import kotlinx.android.synthetic.main.fragment_singup.*
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val pickImage = 100
    private var imageUri: Uri? = null
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_singup, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnsingup: com.google.android.material.textview.MaterialTextView =
            view.findViewById(R.id.btnSingUp)
        btnsingup.setOnClickListener {
            addEtudaint()

        }
        addImg.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        floating__button_chap.setOnClickListener {
            requireView().findNavController().navigate(R.id.adminFragment)
        }

    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.context!!.contentResolver, imageUri)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.back_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //@SuppressLint("UseRequireInsteadOfGet")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item!!.itemId) {
            R.id.btn_back -> {
                requireView().findNavController()
                    .navigate(R.id.adminFragment)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SingupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun addEtudaint() {


        var nom: com.google.android.material.textfield.TextInputLayout =
            view!!.findViewById(R.id.nomEdit)
        var pernom: com.google.android.material.textfield.TextInputLayout =
            view!!.findViewById(R.id.pernomEdit)
        var username: com.google.android.material.textfield.TextInputLayout =
            view!!.findViewById(R.id.txtusernam)
        var password: com.google.android.material.textfield.TextInputLayout =
            view!!.findViewById(R.id.txtpassword)
        var dbHelper: DbHelper = DbHelper(requireContext())

        if (nom.editText?.text.isNullOrEmpty() || pernom.editText?.text.isNullOrEmpty() || username.editText?.text.isNullOrEmpty() || password.editText?.text.isNullOrEmpty()) {
            println(
                Toast.makeText(
                    this!!.activity!!,
                    "Empty Input",
                    Toast.LENGTH_SHORT
                ).show()
            )
        } else {
            var nomText = nom.editText?.text.toString()
            var pernomTxt = pernom.editText?.text.toString()
            var usernameTxt = username.editText?.text.toString()
            var passtext = password.editText?.text.toString()
            var insertEtudaint = EtudiantDataManager.Insertetudaint(
                dbHelper,
                nomText,
                pernomTxt,
                usernameTxt,
                passtext,
                fromBitmap(bitmap),
                0
            )
            if (insertEtudaint!! > 0) {
                println(Toast.makeText(this!!.activity!!, "Success", Toast.LENGTH_LONG).show())

            } else {
                println(Toast.makeText(this!!.activity!!, "Error ", Toast.LENGTH_LONG).show())
            }

            requireView().findNavController().navigate(R.id.adminFragment)
        }


    }

    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
}