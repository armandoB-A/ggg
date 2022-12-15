package com.example.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class
CustomAdapter (private var context: Context, var usuaioss: ArrayList<Usuario>?) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var alumnosOriginales = usuaioss
    private val db = Firebase.firestore
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        viewHolder.textView.text = "nombre: "+usuaioss!![position].nombre
        viewHolder.textView2.text = "apelldio: ${usuaioss!![position].apellido}"
        viewHolder.textView3.text = "numero: " + usuaioss!![position].numero
        viewHolder.card.setOnClickListener {
            Toast.makeText(context, "click "+ usuaioss!![position].nombre, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, EBUser::class.java)
            intent.putExtra("nombre", usuaioss!![position].nombre)
            intent.putExtra("apellido", usuaioss!![position].apellido)
            intent.putExtra("numero", usuaioss!![position].numero)
            context.startActivity(intent)

        }
    /*
        viewHolder.borrar.setOnClickListener {

            db.collection("users").document(usuaioss!![position].id.toString())
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "se borro", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, Inicio::class.java))

                    notifyDataSetChanged()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "error al guardar $e", Toast.LENGTH_LONG).show()
                }
        }
        viewHolder.actualizar.setOnClickListener {
            val intent = Intent(context, EditarUsuario::class.java)
            intent.putExtra("nombre", usuaioss!![position].nombre)
            intent.putExtra("telefono", usuaioss!![position].telefono)
            intent.putExtra("correo", usuaioss!![position].correo)
            intent.putExtra("domicilio", usuaioss!![position].domicilio)
            intent.putExtra("id", usuaioss!![position].id)
            context.startActivity(intent)
            //(context as Inicio::class.java).finish()

        }*/
    }

    override fun getItemCount(): Int {
        return usuaioss!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(strSearch: String) {
        if (strSearch.isEmpty()) {

            usuaioss = alumnosOriginales
        } else {
            val collect: ArrayList<Usuario> =
                alumnosOriginales!!.filter { s ->
                    s.nombre?.toLowerCase()!!.contains(strSearch.toLowerCase())
                } as ArrayList<Usuario>
            /*.filter(ss-> ss.)
            .collect(Collectors.toList<Any>())*/
            usuaioss = collect
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val textView2: TextView
        val textView3: TextView
        var card: CardView


        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById<View>(R.id.tnombre) as TextView
            textView2 = view.findViewById<View>(R.id.tapellido) as TextView
            textView3 = view.findViewById<View>(R.id.tnumero) as TextView
            card=view.findViewById(R.id.idcard) as CardView
        }
    }
}
