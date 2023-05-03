package com.example.cookbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.cookbook.api.model.RecipesList


class MainActivity : AppCompatActivity(), DishListFragment.Listener {
    private lateinit var viewModel : DishesViewModel
    private  var fragmentContainer : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer  = findViewById(R.id.fragment_container)
        viewModel = ViewModelProvider(this)[DishesViewModel::class.java]
        Log.i("viewmodel", viewModel.toString())
        viewModel.getData().observe(this
        ) {
            if (savedInstanceState == null) {
                setDefaultDetailItem()
            }
        }

    }


    override fun itemClicked(id: Long) {
        if (fragmentContainer != null) {
            // tablet
            val details = DishDetailFragment();
            val dish = viewModel.getData().value?.results?.get(id.toInt())
            if (dish != null) {
                viewModel.setDish(dish)
            }

            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()

        } else {
            val intent: Intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.DISH_ID, id.toInt())
            startActivity(intent)
        }
    }

    private fun setDefaultDetailItem() {
            if (fragmentContainer != null) {
                Log.i("SETDEFAULTITEM", "SETDEFAULTITEM")
                val details = DishDetailFragment();
                val dish = viewModel.getData().value?.results?.get(0)
                if (dish != null && viewModel.getDish().value == null) {
                    viewModel.setDish(dish)
                }
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragment_container, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.commit()
        }
    }

}



//val apiKey = "691bb0541f544c4ab7888a600b242b7c"
//val baseUrl = "https://jsonplaceholder.typicode.com/"