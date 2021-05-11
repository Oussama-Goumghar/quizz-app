package com.example.secondquizproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.findNavController
import com.example.secondquizproject.Chapitre.ChapiterDataManager
import kotlinx.android.synthetic.main.fragment_start_quiz.*

//import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartQuiz.newInstance] factory method to
 * create an instance of this fragment.
 */

class StartQuiz : Fragment(), AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var selctech:String = ""
    var nom:String ="";
    var prenom:String ="";
    var scoure:Int = 0;
    val hashMap:HashMap<String,Int> = HashMap<String, Int>()
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
        return inflater.inflate(R.layout.fragment_start_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnlogout.setOnClickListener {
            requireView().findNavController().navigate(R.id.loginFragment2)
        }
         nom = arguments?.getString("nom").toString()
        scoure = arguments?.getInt("scoure")!!
        prenom = arguments?.getString("prenom").toString()

        txtnom.text =nom+" "+prenom
        txtScour.text = "scoure "+scoure.toString()
        var dbHelper: DbHelper = DbHelper(requireContext())
        var spnier = view.findViewById<Spinner>(R.id.spinnerChapiter)
        var chapiterListe   = ChapiterDataManager.getAllChapitre(dbHelper)
        var data  = arrayListOf<String>()
        for (ch in chapiterListe!!){
            hashMap.put(ch.nomchapiter.toString(),ch.id)
            data.add(ch.nomchapiter.toString())
        }

        spnier.adapter= ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1,data)
        spnier.onItemSelectedListener=this


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartQuiz.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartQuiz().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var questionDbHelper: DbHelper = DbHelper(requireContext())

        selctech =parent?.getItemAtPosition(position).toString()
        var id: String = hashMap[selctech].toString()
        btnStart.setOnClickListener {
            val bundle = Bundle()
            val idEtu = arguments?.getInt("idetud")
            bundle.putString("id",id)
            bundle.putString("nom",nom )
            bundle.putString("prenom",prenom )
            bundle.putInt("idEtu", idEtu!!)
            requireView().findNavController().navigate(R.id.action_startQuiz2_to_quizQuestionList2,bundle)
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}