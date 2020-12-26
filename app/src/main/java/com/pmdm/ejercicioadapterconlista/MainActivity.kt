package com.pmdm.ejercicioadapterconlista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmdm.ejercicioadapterconlista.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapter : StringAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createRecyclerView()
    }
    private fun createRecyclerView() {
        val rand = Random()
        val numElementos = rand.nextInt(5..10)
        val listaString = mutableListOf<String>()
        for(i in 1..numElementos) {
            listaString.add("PC-$i")
        }
        adapter = StringAdapter(listaString.toMutableList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}

fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
}
