package com.example.pokemonapp.activities

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding //usuario generico
    private var user = "eevee"
    private var clave = "pokemon123"
    private val arrayGenres = arrayOf("Masculino", "Femenino", "No Defenido")
    private lateinit var selectedSpinner: String
    private val genreMale = "Masculino"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        spinner()
        validateUser()

    }

    private fun spinner() {
        val arrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, arrayGenres)
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_item)
        binding.spinnerGenre.adapter = arrayAdapter
        binding.spinnerGenre.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                l: Long
            ) {
                selectedSpinner = binding.spinnerGenre.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    private fun validateUser() {

        binding.buttonLogin.setOnClickListener {


            var userString: String = binding.edtUser.text.toString()
            var passString: String = binding.edtPass.text.toString()

            if (user == userString && clave == passString && selectedSpinner == genreMale) {
                sendToMainActivity()
                Toast.makeText(this, "Inicio De Sesion Exitoso", Toast.LENGTH_SHORT)
                    .show() //texto que se muestra al colocar los datos correctamente
            } else if (userString.isEmpty() || passString.isEmpty()) {
                Toast.makeText(this, "Complete el espacio en blanco", Toast.LENGTH_SHORT)
                    .show() //texto que se muestra cuando no rellenas los datos
            } else {
                Toast.makeText(this, "usuario invalido", Toast.LENGTH_SHORT).show()
            } //texto que se muestra cuando los datos o uno de los datos son incorrectos
        }
    }

    private fun sendToMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }
}