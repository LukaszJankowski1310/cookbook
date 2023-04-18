package com.example.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(), DishListFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val details : DishDetailFragment = DishDetailFragment();
//        details.setDishIds(0)
//        val ft = supportFragmentManager.beginTransaction()
//        ft.replace(R.id.fragment_container, details);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        ft.addToBackStack(null)
//        ft.commit()
//        setContentView(R.layout.activity_main)

    }

    override fun itemClicked(id: Long) {
      //  Log.i("info", id.toString());
        val fragmentContainer : View = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            // tablet
            val details : DishDetailFragment = DishDetailFragment();
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
}