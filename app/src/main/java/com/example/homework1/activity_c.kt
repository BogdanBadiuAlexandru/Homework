package com.example.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import com.example.homework1.databinding.ActivityCBinding
import com.example.homework1.databinding.ActivityMainBinding

class activity_c : AppCompatActivity() {
    private lateinit var binding : ActivityCBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_c)
        setContentView(binding.root)
        val numar = intent.getIntExtra("int", 0)
        val propozitie = intent.getStringExtra("string")
        val afisare = "$numar, $propozitie"
        binding.editTextToSend.setText(afisare)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners()
    {
        binding.btnOk.setOnClickListener{
            val intentOK = Intent(this, MainActivity::class.java)
            intentOK.putExtra("text", binding.editTextToSend.text.toString())
            setResult(RESULT_OK, intentOK)
            this@activity_c.finish()
        }

        binding.btnCanceled.setOnClickListener{
            val intentCanceled = Intent(this, MainActivity::class.java)
            intentCanceled.putExtra("text", binding.editTextToSend.text.toString())
            setResult(RESULT_CANCELED, intentCanceled)
            finish()
        }
    }
}