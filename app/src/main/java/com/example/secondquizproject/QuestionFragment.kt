package com.example.secondquizproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.collection.arrayMapOf
import androidx.navigation.findNavController
import com.example.secondquizproject.Chapitre.ChapiterDataManager
import com.example.secondquizproject.Question.QuestionDataManager
import kotlinx.android.synthetic.main.fragment_question.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionFragment : Fragment(), AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var selctech:String = ""
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
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dbHelper: DbHelper = DbHelper(requireContext())
        var spnier = view.findViewById<Spinner>(R.id.sChapitre)
        var chapiterListe   = ChapiterDataManager.getAllChapitre(dbHelper)
        var data  = arrayListOf<String>()
       for (ch in chapiterListe!!){
           hashMap.put(ch.nomchapiter.toString(),ch.id)
           data.add(ch.nomchapiter.toString())
       }

        spnier.adapter= ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1,data)
        spnier.onItemSelectedListener=this

        floating__button_chap.setOnClickListener {
            requireView().findNavController().navigate(R.id.adminFragment)
        }

        textAnser.editText?.filters=arrayOf<InputFilter>(MinMaxFilter("1", "3"))

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.back_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //@SuppressLint("UseRequireInsteadOfGet")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item!!.itemId) {
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
         * @return A new instance of fragment QuestionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var questionDbHelper: DbHelper = DbHelper(requireContext())

    selctech =parent?.getItemAtPosition(position).toString()
        var id: Int = hashMap[selctech].toString().toInt()
//        println(Toast.makeText(this!!.activity!!, id, Toast.LENGTH_LONG).show())
        btnaddQ.setOnClickListener {
            if (textQestion.editText?.text.isNullOrEmpty() ||
                textOp1.editText?.text.isNullOrEmpty() ||
                textOp2.editText?.text.isNullOrEmpty() ||
                textOp3.editText?.text.isNullOrEmpty() ||
                textAnser.editText?.text.isNullOrEmpty()
            ) {
                println(
                    Toast.makeText(
                        this!!.activity!!,
                        "Empty Input",
                        Toast.LENGTH_SHORT
                    ).show()
                )
            } else {
                var textq =  textQestion.editText?.text.toString()
                var textoption1 = textOp1.editText?.text.toString()
                var textoption2 = textOp2.editText?.text.toString()
                var textoption3 = textOp3.editText?.text.toString()
                var textAnserOption  = textAnser.editText?.text.toString().toInt()
                var  rows  = QuestionDataManager.InsertQuestion(questionDbHelper,textq,textoption1,textoption2,textoption3,textAnserOption,id)
                if(rows!! >0){
                    println(Toast.makeText(this!!.activity!!, "Success"+selctech, Toast.LENGTH_SHORT).show())
                    requireView().findNavController().navigate(R.id.adminFragment)
                }
                else{
                    println(Toast.makeText(this!!.activity!!, "Error", Toast.LENGTH_SHORT).show())
                }
            }



        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}