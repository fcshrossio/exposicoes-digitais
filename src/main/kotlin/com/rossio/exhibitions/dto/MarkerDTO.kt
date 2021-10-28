package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.MarkerDAO

data class MarkerDTO(
    val id: Long,
    val coordinates : MutableList<Double>,
    val title : String,


    ) {
    constructor() : this(0, mutableListOf(0.0,0.0),"title")

    constructor(markerDAO: MarkerDAO) : this(markerDAO.id, markerDAO.coordinates, markerDAO.title)
}