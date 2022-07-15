package com.example.homework1

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.databinding.ActivityMainBinding

private const val REQ_CODE = 100;

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var airplaneReceiver: AirplaneModeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("OnCreate", "Activity A onCreate accessed")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        airplaneReceiver = AirplaneModeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also { registerReceiver(airplaneReceiver, it) }
        setupButtonsOnClickListeners()
    }

    override fun onStart() {
        Log.i("OnStart", "Activity A onStart accessed")
        super.onStart()
    }

    override fun onRestart() {
        Log.i("onRestart", "Activity A onRestart accessed")
        super.onRestart()
    }

    override fun onResume() {
        Log.i("onResume", "Activity A onResume accessed")
        super.onResume()
    }

    override fun onPause() {
        Log.i("onPause", "Activity A onPause accessed")
        super.onPause()
    }

    override fun onStop() {
        Log.i("onStop", "Activity A onStop accessed")
        super.onStop()
        unregisterReceiver(airplaneReceiver)
    }

    override fun onDestroy() {
        Log.i("onDestroy", "Activity A onDestroy accessed")
        super.onDestroy()
    }

    fun setupButtonsOnClickListeners() {
        binding.btnGoToB.setOnClickListener {
            val intent = Intent(this, activity_b::class.java)
            startActivity(intent)
        }

        binding.buttonUrl.setOnClickListener {
            val openUrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
            startActivity(openUrlIntent)
        }

        binding.btnGoToC.setOnClickListener {
            if (binding.editTextInt.text.toString()
                    .isNotEmpty() && binding.editTextString.text.toString().isNotEmpty()
            ) {
                val fromAtoCIntent = Intent(this, activity_c::class.java)
                val numar = binding.editTextInt.text.toString()
                fromAtoCIntent.putExtra("int", Integer.parseInt(numar))
                fromAtoCIntent.putExtra("string", binding.editTextString.text.toString())
                startActivityForResult(fromAtoCIntent, REQ_CODE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE)
            if (resultCode == RESULT_OK) {
                Log.d("Return", "RESULT_OK")
                if (data?.getStringExtra("text") != null) {
                    val text = data.getStringExtra("text")
                    binding.textView.text = text
                    Log.d("ReturnValueOK", text ?: "null")
                }
            } else {
                if (resultCode == RESULT_CANCELED) {
                    Log.d("Return", "RESULT_CANCELED")
                    if (data?.getStringExtra("text") != null) {
                        binding.textView.text = data.getStringExtra("text")
                        Log.d("ReturnValueCanceled", data.getStringExtra("text") ?: "null")
                    }
                }
            }

    }
}