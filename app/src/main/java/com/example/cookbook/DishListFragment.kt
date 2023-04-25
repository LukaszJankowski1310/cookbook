package com.example.cookbook

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class DishListFragment : ListFragment() {

    private lateinit var listener: Listener
    private lateinit var viewModel : DishesViewModel

    private val titles : ArrayList<String> = ArrayList()
    interface Listener {
        fun itemClicked(id : Long);
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
        viewModel = ViewModelProvider(requireActivity())[DishesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, titles)
        listAdapter = adapter
        viewModel.getData().observe(viewLifecycleOwner, Observer { newData ->
            titles.clear()
            newData.results.forEach { item ->
                run {
                    titles.add(item.title)
                }
            }
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, titles)
            listAdapter = adapter

        })
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener.itemClicked(id)
    }


}