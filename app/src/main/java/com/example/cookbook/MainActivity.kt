package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.api.model.RecipesList


class MainActivity : AppCompatActivity(), DishListFragment.Listener {
    private lateinit var viewModel : DishesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(this)[DishesViewModel::class.java]
        viewModel.getData().observe(this
        ) { data -> // Update the UI with the data
            setDefaultDetailItem()
        }



    }


    override fun itemClicked(id: Long) {
      //  Log.i("info", id.toString());
        val fragmentContainer : View = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            // tablet
            Log.i("ID", id.toString())
            val details = DishDetailFragment();
            details.setDishIds(id.toInt())
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.addToBackStack(null)
            ft.commit()

        } else {
            val intent: Intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.DISH_ID, id.toInt())
            startActivity(intent)
        }
    }

    private fun setDefaultDetailItem(){
        val details : DishDetailFragment = DishDetailFragment();
        details.setDishIds(0)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, details);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.addToBackStack(null)
        ft.commit()
    }



}


//val apiKey = "691bb0541f544c4ab7888a600b242b7c"
//val baseUrl = "https://jsonplaceholder.typicode.com/"