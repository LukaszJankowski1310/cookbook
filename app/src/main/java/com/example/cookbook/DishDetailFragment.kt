package com.example.cookbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DishDetailFragment : Fragment() {
     var dishId : Int = -1;
     private lateinit var dishTitle : TextView
     private lateinit var dishDescription : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_cocktail_detail, container, false)

        dishTitle = view.findViewById(R.id.textTitle)
        dishDescription = view.findViewById(R.id.textDescription)

        return view
    }

    override fun onStart() {
        super.onStart()
        val dish : Dish = Dish.dishes[dishId.toInt()]
        dishTitle.text = dish.name
        dishDescription.text = dish.recipe

    }

    fun setDishIds(id: Int) {
        this.dishId = id
    }


}