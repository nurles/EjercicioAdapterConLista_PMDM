package com.pmdm.ejercicioadapterconlista

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class StringAdapter (var listaString : MutableList<String>) : RecyclerView.Adapter<StringAdapter.StringViewHolder>() {
    class StringViewHolder(var root: View, var textView: TextView, var checkbox : CheckBox) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val twTextView = view.findViewById<TextView>(R.id.textView)
        val checkbox = view.findViewById<CheckBox>(R.id.checkbox)
        return StringViewHolder(view, twTextView, checkbox)
    }

    override fun getItemCount(): Int {
        return listaString.size+3
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        val rand = Random()
        val total = rand.nextInt(0..listaString.size)
        for(i in 0..total){
            val num = rand.nextInt(0..listaString.size)
            if(num == position){
                holder.checkbox.isChecked = true
            }
        }
        //Esto establece los fondos para los marcados aleatoriamente
        if(holder.checkbox.isChecked){
            holder.root.setBackgroundColor(Color.parseColor("#6DBB32"))
        }
        else{
            holder.root.setBackgroundColor(Color.parseColor("#C8150B"))
        }

        //Por eso hago este clickListener para que si se marca o desmarca un checkbox tambien cambie
        holder.checkbox.setOnClickListener {
            if(holder.checkbox.isChecked){
                holder.root.setBackgroundColor(Color.parseColor("#6DBB32"))
            }
            else{
                holder.root.setBackgroundColor(Color.parseColor("#C8150B"))
            }
        }

        if(position != 0 && position != itemCount-1) {
            //No se por que no me funciona el listaString[position], no se ejecuta la aplicacion
            holder.textView.text = listaString.toString()
        }

        if(position == 0){
            holder.textView.text = "Borrar"
            holder.root.setOnClickListener {
                if(listaString.size > 0) {
                    val rand = Random()
                    listaString.removeAt(rand.nextInt(1..listaString.size))
                    notifyDataSetChanged()
                }
            }
            return
        }

        if(position == itemCount-1){
            holder.textView.text = "Insertar"
            holder.root.setOnClickListener {
                listaString.add("PC-${listaString.size+1}")
                notifyDataSetChanged()
            }
            return
        }

        if (position == itemCount-2){
            holder.textView.text = "Contar encendidos"
            var cont = 0
            holder.root.setOnClickListener {
                //Cont solo cuenta si esta marcada la casilla de contar encendidos y el numero de veces que lo has pulsado estando marcada
                    if (holder.checkbox.isChecked)
                        cont++


                val toast = Toast.makeText(it.context, "$cont",Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }

    }
}