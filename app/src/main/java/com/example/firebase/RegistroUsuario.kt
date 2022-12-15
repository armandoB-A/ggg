package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.firebase.databinding.ActivityMainBinding
import com.example.firebase.databinding.ActivityRegistroUsuarioBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistroUsuario : AppCompatActivity() {

    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
            }
        onBackPressedDispatcher.addCallback(this, callback)

        setContentView(binding.root)
        binding.button.setOnClickListener {
            Toast.makeText(applicationContext, "guardando usuario", Toast.LENGTH_SHORT).show()
            val user = Usuario(
                binding.nomns.text.toString(),
                binding.apellidoss.text.toString(),
                binding.numss.text.toString()
            )
            db.collection("user").document(binding.numss.text.toString()).set(user).addOnSuccessListener {
                Toast.makeText(applicationContext, "usuario guardado con exito", Toast.LENGTH_SHORT)
                    .show()
                binding.nomns.setText("")
                binding.apellidoss.setText("")
                binding.numss.setText("")
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "el usuario no se guardo", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}