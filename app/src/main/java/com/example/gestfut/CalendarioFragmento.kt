package com.example.gestfut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestfut.adaptador.AdaptadorPartido
import com.example.gestfut.data.Partido
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut.databinding.LayoutFragmentCalendarioBinding


class CalendarioFragmento : Fragment() {

    lateinit var mibinding:LayoutFragmentCalendarioBinding
    lateinit var mirecycler:RecyclerView
    lateinit var miadaptador:AdaptadorPartido
    lateinit var mispinner:Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mibinding=LayoutFragmentCalendarioBinding.inflate(inflater,container,false)

        return mibinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inicializar recyclerView
        inicializar_recycler()

        //Inicializar spinner
        inicializar_spinner()
    }

    private fun inicializar_spinner() {
        val jornadas=PartidoProveedor.partidos.map { it.jornada }.distinct().sorted().toMutableList()
        jornadas.add(0,0)
        val adaptador_spinner=ArrayAdapter<Int>(requireContext(),android.R.layout.simple_spinner_item,jornadas)
        adaptador_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mibinding.spinnerJornada.adapter=adaptador_spinner

        //Controlar el filtrado de Partidos


        mibinding.spinnerJornada.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var jornada_sel=parent?.getItemAtPosition(position)
                if(jornada_sel!=0)
                     miadaptador.establecerPartidos(PartidoProveedor.partidos.filter { it.jornada==jornada_sel })
                else
                    miadaptador.establecerPartidos(PartidoProveedor.partidos)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }
    }

    private fun inicializar_recycler() {
            this.mirecycler= mibinding.reciclerCalendario
            this.mirecycler.layoutManager =LinearLayoutManager(context)
            this.miadaptador=AdaptadorPartido(PartidoProveedor.partidos)
            this.mirecycler.adapter=this.miadaptador

    }
}