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

        //HAY QUE RELLENAR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPartido {
    return HolderPartido(LayoutInflater.from(parent.context).inflate(R.layout.elementopartido,parent,false))
    }

    /* Funcion que permite cambiar el listado de partidos a visualizar en el RecyclerView */
    fun establecerPartidos(p:List<Partido>)
    {
        this.partidos=p
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int=partidos.size

    override fun onBindViewHolder(holder: HolderPartido, position: Int) {
        //HAY QUE RELLENAR
    }

}