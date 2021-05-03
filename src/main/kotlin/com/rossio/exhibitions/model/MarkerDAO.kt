package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.MarkerDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class MarkerDAO(
    @GeneratedValue
    @Id
    val markerId : Long,
    @ManyToOne
    val mapItem: MapItemDAO,
    val coordinates : Long,
    val description : String
) {
    constructor() : this(0, MapItemDAO(),0,"description")

    constructor(markerDTO: MarkerDTO, mapItem: MapItemDAO) : this(markerDTO.markerId,mapItem,markerDTO.coordinates,markerDTO.description)

}

