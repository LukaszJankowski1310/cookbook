package com.example.cookbook

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.api.model.Dish
import com.example.cookbook.tmp.MyAdapter


class DishListFragment : Fragment() {

    private lateinit var listener: Listener
    private val viewModel: DishesViewModel by lazy {
        (requireActivity().application as App).myViewModel
    }

//    private val titles : ArrayList<String> = ArrayList()
    private val dishes : ArrayList<Dish> = ArrayList()

    private lateinit var recyclerView: RecyclerView

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

        val view = inflater.inflate(R.layout.fragment_dish_list, container, false)
        recyclerView = view.findViewById(R.id.dish_list_rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val adapter = MyAdapter(dishes) { item ->
//            listener.itemClicked(item)
//        }
//        recyclerView.adapter = adapter

        viewModel.getData().observe(viewLifecycleOwner, Observer { newData ->
            dishes.clear()
            newData.results.forEach { item ->
                run {
                    dishes.add(Dish(item.title, item.image))
                }
            }

            val adapter = MyAdapter(dishes) { item ->
                listener.itemClicked(item)
            }
            recyclerView.adapter = adapter
        })
    }


}