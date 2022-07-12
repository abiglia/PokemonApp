package com.example.pokemonapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemonapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private var user = "eevee"
    private var clave = "pokemon123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateUser()

    }

    private fun validateUser(){

        binding.buttonLogin.setOnClickListener {


            var userString: String = binding.edtUser.text.toString()
            var passString: String = binding.edtPass.text.toString()

            if (user == userString && clave == passString) {
                Toast.makeText(this, "Inicio De Sesion Exitoso", Toast.LENGTH_SHORT).show()
            }
            else if (userString.isEmpty() || passString.isEmpty()){
                Toast.makeText(this, "Complete el espacio en blanco", Toast.LENGTH_SHORT).show()
            }else {Toast.makeText(this, "usuario invalido", Toast.LENGTH_SHORT).show()}
        }
    }
}