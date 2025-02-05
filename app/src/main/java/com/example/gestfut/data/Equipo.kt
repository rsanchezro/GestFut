package com.example.gestfut.data

import kotlinx.serialization.Serializable

@Serializable
data class Equipo(val nombre:String,var pg:Int,var pp:Int,var PE:Int,val escudo:String)
