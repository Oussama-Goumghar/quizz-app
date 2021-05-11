package com.example.secondquizproject

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.secondquizproject.Admin.AdminDataManager
import com.example.secondquizproject.Etudiant.EtudiantDataManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [loginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class loginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


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

        var view = inflater.inflate(R.layout.fragment_login, container, false)

        return view
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user: androidx.appcompat.widget.AppCompatEditText = view.findViewById(R.id.textusername)
        var pass: androidx.appcompat.widget.AppCompatEditText = view.findViewById(R.id.textpassword)
        var btn: android.widget.FrameLayout = view.findViewById(R.id.btnlogin)


        var dbHelper: DbHelper = DbHelper(requireContext())
        var insertadmi = AdminDataManager.InsertIAdmin(dbHelper, "admin", "admin")
        btn.setOnClickListener {
            var txtuser = user.text.toString()
            var txtpass = pass.text.toString()
            var check = AdminDataManager.checkAdmin(dbHelper, txtuser, txtpass)
            if (check) {
                requireView().findNavController()
                    .navigate(R.id.action_loginFragment2_to_adminFragment)
            } else {
                var etudaint = EtudiantDataManager.checkEtudaint(dbHelper, txtuser, txtpass)
                if (etudaint.size > 0) {
                    val bundle = Bundle()
                    bundle.putInt("idetud", etudaint[0].id!!)
                    bundle.putInt("scoure", etudaint[0].Scoure!!)
                    bundle.putString("nom", etudaint[0].nom.toString())
                    bundle.putString("prenom", etudaint[0].pernom.toString())
                    requireView().findNavController().navigate(
                        R.id.action_loginFragment2_to_startQuiz2,
                        bundle
                    )
                } else {
                    println(
                        Toast.makeText(this!!.activity!!, "Error login", Toast.LENGTH_LONG).show()
                    )
                }


            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment loginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            loginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}