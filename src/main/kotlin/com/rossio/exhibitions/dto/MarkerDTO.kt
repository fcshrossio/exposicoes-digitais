package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.MarkerDAO

data class MarkerDTO(
    val markerId: Long,
    val mapItemId: Long,
    val coordinates : Long,
    val description : String,
    val digitalResources : List<DigitalResourceDTO>

) {
    constructor() : this(0, 0,0,"description", emptyList())

    constructor(markerDAO: MarkerDAO) : this(markerDAO.markerId,0, markerDAO.coordinates, markerDAO.description , markerDAO.digitalResources.map { DigitalResourceDTO(it) })
}