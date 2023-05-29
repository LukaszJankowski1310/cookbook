package com.example.cookbook.tmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R


class BlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val dishListRV = view.findViewById<RecyclerView>(R.id.dish_list_rv)

//        val data : List<String> = listOf("Ala", "ma", "kota");
//        val adapter = MyAdapter(data)
//
//        dishListRV.adapter = adapter
//        dishListRV.layoutManager = LinearLayoutManager(context)

        return view
    }

}