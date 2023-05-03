package com.example.cookbook.tmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.cookbook.R

class TmpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmp)
        if (savedInstanceState == null) {
            val ft2 = supportFragmentManager.beginTransaction()
            val stopper : StopperFragment = StopperFragment()
            stopper.setSeconds(5)
            ft2.replace(R.id.stopper_fragment, stopper);
            ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft2.addToBackStack(null)
            ft2.commit()
        }

    }
}