package com.example.gestfut.data

import kotlinx.serialization.Serializable

@Serializable
data class Partido(var equipo_local:String,var equipo_visitante:String,var fecha:Long,var jornada:Int,var goles_local:Int?=null,var goles_visitante:Int?=null,var puntos:Int?=null)
{
    //Defino 2 propiedades(get) no editables (val) que permitan devolver los escudos de los equipos que participan en
    // un partido
    val escudo_local:String? get(){
       return EquipoProveedor.equipos.find { equipo_local.equals(it.nombre) }?.escudo?:null
    }
    val escudo_visitante:String?
        get(){
            return EquipoProveedor.equipos.find { equipo_visitante.equals(it.nombre) }?.escudo?:null
        }
}
