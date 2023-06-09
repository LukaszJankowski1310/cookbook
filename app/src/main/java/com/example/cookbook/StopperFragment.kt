package com.example.cookbook

import android.content.Context
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class StopperFragment : Fragment(), View.OnClickListener {

    private var seconds = 0
    private var running : Boolean = false;
    private var wasRunning : Boolean = false;
    private var vibrating : Boolean = false


    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           if (savedInstanceState != null) {
               seconds = savedInstanceState.getInt(SECONDS)
               running = savedInstanceState.getBoolean(RUNNING)
               wasRunning = savedInstanceState.getBoolean(WAS_RUNNING)
           }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout : View = inflater.inflate(R.layout.fragment_stopper, container, false)
        runStopper(layout)

        startButton = layout.findViewById(R.id.start_button)
        stopButton = layout.findViewById(R.id.stop_button)
        resetButton = layout.findViewById(R.id.reset_button)

        startButton.setOnClickListener(this)
        stopButton.setOnClickListener(this)
        resetButton.setOnClickListener(this)



        return layout
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SECONDS, seconds)
        outState.putBoolean(RUNNING, running)
        outState.putBoolean(WAS_RUNNING, wasRunning)
    }


    fun setSeconds(secs : Int) {
        seconds = secs
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
    }

     fun runStopper(view : View) {
         val timeView = view.findViewById(R.id.time_view) as TextView
         val handler = Handler()
         handler.post(object : Runnable {
             override fun run() {
                 val hours = seconds / 3600
                 val minutes = seconds % 3600 / 60
                 val secs = seconds % 60
                 val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                 timeView.text = time
                 if (vibrating) {
                     vibratePhone(1000)
                     Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                     vibrating = false
                 }

                 if (running) {
                     if (seconds > 0) seconds--

                     if (seconds == 0) {
                         running = false
                         wasRunning = false
                         vibrating = true
                     }

                 }
                 handler.postDelayed(this, 1000)
             }
         })
     }


    private fun vibratePhone(durationInMillis: Long) {
        Log.i("VIBRATING", "VIBRATING")
        val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(durationInMillis, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(durationInMillis)
        }
    }



    companion object {
       private const val SECONDS = "seconds"
        private const val RUNNING = "running"
        private const val WAS_RUNNING = "wasRunning"

    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.start_button -> onClickStart()
                R.id.stop_button -> onClickStop()
                R.id.reset_button -> onClickReset()

            }
        }
    }
}