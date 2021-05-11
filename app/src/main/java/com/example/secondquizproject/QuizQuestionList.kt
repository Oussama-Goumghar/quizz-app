package com.example.secondquizproject

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondquizproject.Etudiant.EtudiantDataManager
import com.example.secondquizproject.Question.QuestionDataManager
import com.example.secondquizproject.Question.Question
import kotlinx.android.synthetic.main.fragment_quiz_question_list.*
import kotlinx.android.synthetic.main.my_dialog_danger.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizQuestionList.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizQuestionList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var questionList: ArrayList<Question>

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
        val id = arguments?.getString("id")
        var view = inflater.inflate(R.layout.fragment_quiz_question_list, container, false)
        var questionDbHelper: DbHelper = DbHelper(requireContext())
        questionList = QuestionDataManager.getBychapitre(questionDbHelper, id!!)!!
        val  recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.adapter = myadapter(questionList)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            var count=0
            for (question in questionList) {
                if (question.answerSelected != null) {
                    if (question.answerSelected==question.answer){
                        count++
                    }
                }
            }
            val idetu = arguments?.getInt("idEtu").toString()
//            Toast.makeText(context,"hada howa=="+idetu,Toast.LENGTH_LONG).show()
//            Toast.makeText(context,"hada howa=="+count,Toast.LENGTH_LONG).show()
            var DbHelper: DbHelper = DbHelper(requireContext())
            EtudiantDataManager.updateinfo(DbHelper,idetu,count)
            showCustomDialog(count,questionList.size)
        }

    }
    private fun showCustomDialog(score:Int,ender:Int) {
        var dialogView: View? = null
        if (((score * 100) / ender) > 50) {
            dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.my_dialog_success, recycler_view, false)
        } else {
            dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.my_dialog_danger, recycler_view, false)
        }


        //Now we need an AlertDialog.Builder object

        if (dialogView != null) {
            dialogView.textAlertScore.text = "" + score + "/" + ender
        }

        val builder = AlertDialog.Builder(requireContext())
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView)
        //finally creating the alert dialog and displaying it
        val alertDialog = builder.create()
        alertDialog.show()
        if (dialogView != null) {
            dialogView.buttonOk.setOnClickListener {
                var DbHelper: DbHelper = DbHelper(requireContext())

                val bundle = Bundle()

                val idEtu = arguments?.getInt("idEtu")
                var etudaint =   EtudiantDataManager.GetEtudaintById(DbHelper, idEtu.toString())
                val nom = arguments?.getString("nom")
                val pernom =  arguments?.getString("prenom")
                bundle.putInt("idetud", idEtu!!)
                bundle.putInt("scoure", etudaint[0].Scoure!!)
                bundle.putString("nom", nom)
                bundle.putString("prenom", pernom)
                bundle.putSerializable("lstQestion", questionList )
                requireView().findNavController()
                    .navigate(R.id.action_quizQuestionList2_to_showScourFragment, bundle)
                alertDialog.dismiss()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.back_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //@SuppressLint("UseRequireInsteadOfGet")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item!!.itemId) {
            R.id.btn_back -> {
                val bundle = Bundle()
                val idEtu = arguments?.getInt("idEtu")
                bundle.putInt("idetud", idEtu!!)
                requireView().findNavController()
                    .navigate(R.id.action_quizQuestionList2_to_startQuiz2,bundle)

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
         * @return A new instance of fragment QuizQuestionList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizQuestionList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    }

