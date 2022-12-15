package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db.collection("user").get().addOnSuccessListener { it ->
            var user = arrayListOf<Usuario>()
            it.documents.forEach { its ->
                user.add(
                    Usuario(
                        its.get("nombre").toString(),
                        its.get("apellido").toString(),
                        its.get("numero").toString()
                    )
                )
            }
            var customAdapter=CustomAdapter(applicationContext, user)
            binding.recycler.adapter=customAdapter
            binding.recycler.layoutManager=LinearLayoutManager(applicationContext)

        }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "no se pudo", Toast.LENGTH_SHORT).show()
            }
        binding.button.setOnClickListener {
            startActivity(Intent(applicationContext, RegistroUsuario::class.java))
        }
    }


}