package com.example.gestfut.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestfut.R
import com.example.gestfut.data.Partido
import com.example.gestfut.databinding.ElementopartidoBinding
import java.text.SimpleDateFormat
import java.util.Date

class AdaptadorPartido(var partidos:List<Partido>):RecyclerView.Adapter<AdaptadorPartido.HolderPartido>() {
    class HolderPartido(vista: View):RecyclerView.ViewHolder(vista) {
        val bindingholderpartido=ElementopartidoBinding.bind(vista)

        fun render(partido:Partido)
        {
            bindingholderpartido.nombreEquipoloc.text=partido.equipo_local
            bindingholderpartido.nombreEquipovis.text=partido.equipo_visitante
            bindingholderpartido.golesLocal.text=partido.goles_local.toString()
            bindingholderpartido.golesVisitante.text=partido.goles_visitante.toString()
            bindingholderpartido.jornadaTextview.text="Jornada:${partido.jornada.toString()}"

          //  bindingholderpartido.logoequipoLocal.setImageResource(partido.)
            bindingholderpartido.fechaPartido.text=SimpleDateFormat("dd/MM/yyyy - HH:mm").format(
                Date(partido.fecha*1000)
            )
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPartido {
    return HolderPartido(LayoutInflater.from(parent.context).inflate(R.layout.elementopartido,parent,false))
    }

    fun establecerPartidos(p:List<Partido>)
    {
        this.partidos=p
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int=partidos.size

    override fun onBindViewHolder(holder: HolderPartido, position: Int) {
        holder.render(partidos.get(position))
    }

}