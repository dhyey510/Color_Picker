/*Name: Dhyey Harihar Desai
  Email: dhyeydesai@csu.fullerton.edu
  CWID: 885451609
 */

package com.example.project1_colormaker

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    //initialize variable for application
    var RED: Float = 0.0f
    var BLUE:Float = 0.0f
    var GREEN:Float = 0.0f
    var redPrevious:Float = 0.0f;
    var bluePrevious:Float = 0.0f;
    var greenPrevious:Float = 0.0f;

    private lateinit var view: View;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var redSwitch: Switch;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var blueSwitch: Switch;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var greenSwitch: Switch;
    private lateinit var redSeekBar: SeekBar;
    private lateinit var greenSeekBar: SeekBar;
    private lateinit var blueSeekBar: SeekBar;
    private lateinit var redTextBox: EditText;
    private lateinit var blueTextBox: EditText;
    private lateinit var greenTextBox: EditText;
    private lateinit var resetButton: Button;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //call connectView() to connect elements to pointer
        connectView()

        //Initialize view
        initializeView()

        //resetbutton code
        resetButtonCallback()

        //callback for fetch entered value in edittext
        textBoxCallBack()

        //callback for seekbar progress change
        seekBarCallBack()

        //callback for switch on-off
        switchCallBack()

    }

    private fun connectView(){
        this.view = this.findViewById(R.id.view)

        //assigned blue text, seekbar & switch and initialize as disable
        this.blueSwitch = this.findViewById(R.id.blueSwitch)
        this.blueSeekBar = this.findViewById(R.id.blueSeekBar)
        this.blueTextBox = this.findViewById(R.id.blueText)

        //assigned red text, seekbar & switch and initialize as disable
        this.redSwitch = this.findViewById(R.id.redSwitch)
        this.redSeekBar = this.findViewById(R.id.redSeekBar)
        this.redTextBox = this.findViewById(R.id.redText)

        //assigned green text, seekbar & switch and initialize as disable
        this.greenSwitch = this.findViewById(R.id.greenSwitch)
        this.greenSeekBar = this.findViewById(R.id.greenSeekBar)
        this.greenTextBox = this.findViewById(R.id.greenText)

        //reset button
        this.resetButton = this.findViewById(R.id.resetButton)
    }

    private fun initializeView(){

        //Initialize swicth, seekbar and textbox of blue
        blueSwitch.isChecked = false
        blueSeekBar.setEnabled(false)
        blueTextBox.setFocusableInTouchMode(false)
        blueTextBox.isEnabled = false

        //Initialize seekbar and textbox of red
        redSwitch.isChecked = false
        redSeekBar.setEnabled(false)
        redTextBox.setFocusableInTouchMode(false)
        redTextBox.isEnabled = false

        //Initialize seekbar and textbox of green
        greenSwitch.isChecked = false
        greenSeekBar.setEnabled(false)
        greenTextBox.setFocusableInTouchMode(false)
        greenTextBox.isEnabled = false

        //Initialize view as black
        view.setBackgroundColor(Color.rgb(0, 0, 0));

    }
    private fun seekBarCallBack(){
        //redSeekBar callback and store value to textbox as well as "RED" variable
        redSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val seekBarValue = p1.toFloat() / 100
                redTextBox.setText(seekBarValue.toString())
                RED = seekBarValue
                view.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        //greenSeekBar callback and store value to textbox as well as "GREEN" variable
        greenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val seekBarValue = p1.toFloat() / 100
                greenTextBox.setText(seekBarValue.toString())
                GREEN = seekBarValue
                view.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        //blueSeekBar callback and store value to textbox as well as "BLUE" variable
        blueSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(sb: SeekBar?, p1: Int, p2: Boolean) {
                val seekBarValue = p1.toFloat() / 100
                blueTextBox.setText(seekBarValue.toString())
                BLUE = seekBarValue
                view.setBackgroundColor(Color.rgb(RED, GREEN, BLUE));
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun textBoxCallBack(){
        //redTextBox callback to fetch value from user input when they press enter
        redTextBox.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val txt = v as EditText
                if(v.text.toString() == ""){
                    redSeekBar.setProgress(0)
                }else{
                    redSeekBar.setProgress((txt.text.toString().toFloat() * 100).toInt())
                }
                return@OnKeyListener true
            }
            false
        })

        //blueTextBox callback to fetch value from user input when they press enter
        blueTextBox.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                val txt = v as EditText
                if(v.text.toString() == ""){
                    blueSeekBar.setProgress(0)
                }else{
                    blueSeekBar.setProgress((txt.text.toString().toFloat() * 100).toInt())
                }
                return@OnKeyListener true
            }
            false
        })

        //greenTextBox callback to fetch value from user input when they press enter
        greenTextBox.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                val txt = v as EditText
                if(v.text.toString() == ""){
                    greenSeekBar.setProgress(0)
                }else{
                    greenSeekBar.setProgress((txt.text.toString().toFloat() * 100).toInt())
                }
                return@OnKeyListener true
            }
            false
        })
    }

    private fun switchCallBack(){
        //redSwitch callback
        redSwitch.setOnClickListener {
            val sw: Switch = it as Switch

            if (sw.isChecked == false) {
                redSeekBar.setEnabled(false)
                redTextBox.setFocusable(false)
                redTextBox.isEnabled = false

                //take previous value to variable and set 0
                redPrevious = redSeekBar.progress.toFloat() / 100
                redTextBox.setText(R.string.autoFillHint)
                redSeekBar.setProgress(0)
            } else {
                redSeekBar.setEnabled(true)
                redTextBox.setFocusableInTouchMode(true)
                redTextBox.isEnabled = true
                redTextBox.setText(redPrevious.toString())
                redSeekBar.setProgress((redPrevious * 100).toInt())
                Log.i(TAG, RED.toString())
            }
        }

        //blueSwitch callback
        blueSwitch.setOnClickListener {
            val sw: Switch = it as Switch
            Log.i(TAG, "switch clicked " + sw.isChecked)
            if (sw.isChecked == false) {
                blueSeekBar.setEnabled(false)
                blueTextBox.setFocusable(false)
                blueTextBox.isEnabled = false

                //take previous value to variable ans set 0
                bluePrevious = blueSeekBar.progress.toFloat() / 100
                blueTextBox.setText(R.string.autoFillHint)
                blueSeekBar.setProgress(0)
            } else {
                blueSeekBar.setEnabled(true)
                blueTextBox.setFocusableInTouchMode(true)
                blueTextBox.isEnabled = true
                blueTextBox.setText(bluePrevious.toString())
                blueSeekBar.setProgress((bluePrevious * 100).toInt())
            }
        }

        //greenSwitch callback
        greenSwitch.setOnClickListener {
            val sw: Switch = it as Switch
            Log.i(TAG, "switch clicked " + sw.isChecked)
            if (sw.isChecked == false) {
                greenSeekBar.setEnabled(false)
                greenTextBox.setFocusable(false)
                greenTextBox.isEnabled = false

                //take previous value to variable and set 0
                greenPrevious = greenSeekBar.progress.toFloat() / 100
                greenTextBox.setText(R.string.autoFillHint)
                greenSeekBar.setProgress(0)

            } else {
                greenSeekBar.setEnabled(true)
                greenTextBox.setFocusableInTouchMode(true)
                greenTextBox.isEnabled = true
                greenTextBox.setText(greenPrevious.toString())
                greenSeekBar.setProgress((greenPrevious * 100).toInt())
            }
        }
    }

    private fun resetButtonCallback(){
        resetButton.setOnClickListener { view: View ->
            val btn = view as Button


            Log.i(TAG, "reset Button called")
            redTextBox.setText(R.string.autoFillHint)
            blueTextBox.setText(R.string.autoFillHint)
            greenTextBox.setText(R.string.autoFillHint)

            redSeekBar.setProgress(0)
            blueSeekBar.setProgress(0)
            greenSeekBar.setProgress(0)

            initializeView()

            RED = 0f
            GREEN = 0f
            BLUE = 0f
            redPrevious = 0f
            bluePrevious = 0f
            greenPrevious = 0f
        }
    }
}