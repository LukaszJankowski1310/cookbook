package com.example.cookbook

import android.content.Context
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment


class DishListFragment : ListFragment() {

    private lateinit var listener: Listener

    interface Listener {
        fun itemClicked(id : Long);
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val names = ArrayList<String>()
        for (dish in  Dish.dishes) {
            names.add(dish.name)
        }

        val adapter : ArrayAdapter<String> =
            ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, names)
        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener.itemClicked(id)
    }


}