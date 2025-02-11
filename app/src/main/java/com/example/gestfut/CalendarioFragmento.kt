package com.example.gestfut

import android.app.AlertDialog
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
import com.example.gestfut.databinding.EdicionresultadoPartidoBinding
import com.example.gestfut.databinding.LayoutFragmentCalendarioBinding
import java.text.SimpleDateFormat
import java.util.Date


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

            this.miadaptador=AdaptadorPartido(PartidoProveedor.partidos,{posicion->
                editarPartido(posicion)
            })

            this.mirecycler.adapter=this.miadaptador

    }

    private fun editarPartido(posicion: Int) {
        //Inflamos la vista para poder tener acceso a los elementos de la vista que
        //permite editar el resultado
           // val vista=layoutInflater.inflate(R.layout.edicionresultado_partido,null)
            val vistabinding=EdicionresultadoPartidoBinding.inflate(layoutInflater)
            val partido=PartidoProveedor.partidos.get(posicion)
        vistabinding.logoequipolocalEdi.setImageResource(resources.getIdentifier(partido.escudo_local,"drawable",requireContext().packageName))
        vistabinding.logoequipovisEdi.setImageResource(resources.getIdentifier(partido.escudo_visitante,"drawable",requireContext().packageName))
            vistabinding.golesLocalEditext.hint=partido.goles_local.toString()
        vistabinding.golesVisitanteEditext.hint=partido.goles_visitante.toString()
        vistabinding.nombreEquipolocalEdi.text=partido.equipo_local
        vistabinding.nombreequipovisEdi.text=partido.equipo_visitante
       vistabinding.fechaPartidoEdi.text= SimpleDateFormat("dd/MM/yyyy - HH:mm").format(Date(partido.fecha*1000))

        AlertDialog.Builder(requireContext()).apply {
                setTitle("EDICION DE PARTIDO")

                setPositiveButton("Modificar"){ boton,di->
                    //Modifico el resultado del partido, solo si se ha modificado

                    vistabinding.golesLocalEditext.text.toString().apply {
                        //No se ha modificado el resultado, retorno que tenia
                        if(!this.equals(""))
                             partido.goles_local=this.toString().toInt()
                       }
                  vistabinding.golesVisitanteEditext.text.toString().apply {
                      if(!this.equals("")) partido.goles_visitante=this.toString().toInt()
                  }
                    //notifico de los cambios

                    miadaptador.notifyItemChanged(posicion)
                }
            setNegativeButton("Cancelar")
            { boton,dia->
                boton.dismiss()
            }
            setCancelable(false)
                setView(vistabinding.root)
            }.create().show()
    }
}