package com.example.demo.fragment.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.model.Regulation
import com.example.demo.adapter.RegulationListAdapter
import com.example.demo.databinding.FragmentRegulationListBinding

class RegulationListFragment : Fragment() {

    private var _binding: FragmentRegulationListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentRegulationListBinding.inflate(inflater, container, false)
        _binding!!.homeActionButton.setOnClickListener{ goHome() }

        if (findNavController().previousBackStackEntry?.destination?.displayName!! == "com.example.demo:id/main_fragment") {
            _binding!!.homeActionButton.hide()
        }

        val regulationList: RecyclerView = binding.list
        val regulationListAdapter = RegulationListAdapter()
        regulationList.adapter = regulationListAdapter
        regulationListAdapter.regulations = Regulation.data

        val view = binding.root
        return view
    }

    private fun goHome() {
        findNavController().navigate(R.id.home_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.regulation_filter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.regulation_action_filter) {
            Toast.makeText(activity, "holanda", Toast.LENGTH_LONG).show()
            true
        } else super.onOptionsItemSelected(item)
    }

}