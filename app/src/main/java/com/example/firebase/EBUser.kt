package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityEbUserBinding
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EBUser : AppCompatActivity() {

    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityEbUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        if (extras != null) {
            binding.nomns.setText(extras.getString("nombre"))
            binding.apellidoss.setText(extras.getString("apellidos"))
            binding.numss.setText(extras.getString("numero"))
        }
        val numID: String = binding.numss.text.toString()
        binding.button.setOnClickListener {
            db.collection("user").document(numID).set(
                Usuario(
                    binding.nomns.text.toString(),
                    binding.apellidoss.text.toString(),
                    binding.numss.text.toString()
                )
            )
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(applicationContext, "Usuario actualizado", Toast.LENGTH_LONG)
                        .show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext, "error al guardar $e", Toast.LENGTH_LONG)
                        .show()
                }
        }
        binding.buttonborrar.setOnClickListener {
            db.collection("user").document(numID)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "se borro", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext, "error al guardar $e", Toast.LENGTH_LONG)
                        .show()
                }
        }
        binding.buttonCancelar.setOnClickListener {
            onBackPressed()
        }
    }
}