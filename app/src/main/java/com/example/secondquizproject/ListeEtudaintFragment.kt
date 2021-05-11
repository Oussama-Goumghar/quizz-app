package com.example.secondquizproject

import adapterListeEtudaint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondquizproject.Etudiant.Etudiant
import com.example.secondquizproject.Etudiant.EtudiantDataManager
import com.example.secondquizproject.Question.QuestionDataManager
import kotlinx.android.synthetic.main.fragment_liste_etudaint.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListeEtudaintFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListeEtudaintFragment : Fragment() {
    var etudaint = ArrayList<Etudiant>()
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
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_etudaint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dbHelper: DbHelper = DbHelper(requireContext())
        etudaint= EtudiantDataManager.getAllEtudaint(dbHelper)!!
        var madpter = adapterListeEtudaint(etudaint)
        listeEtud.layoutManager = LinearLayoutManager(context)
        listeEtud.setHasFixedSize(true)
        listeEtud.adapter = madpter

        floating__button_chap.setOnClickListener {
            requireView().findNavController().navigate(R.id.adminFragment)
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
         * @return A new instance of fragment ListeEtudaintFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListeEtudaintFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}