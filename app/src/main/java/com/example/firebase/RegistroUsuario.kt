package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase.databinding.ActivityMainBinding
import com.example.firebase.databinding.ActivityRegistroUsuarioBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistroUsuario : AppCompatActivity() {

    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val user=Usuario(
                binding.nomns.text.toString(),
                binding.apellidoss.text.toString(),
                binding.numss.text.toString()
            )
            db.collection("user").add(user).addOnSuccessListener {

            }.addOnFailureListener {

            }
        }
    }
}