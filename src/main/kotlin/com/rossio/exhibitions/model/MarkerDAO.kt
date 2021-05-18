package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.MarkerDTO
import javax.persistence.*

@Entity
data class MarkerDAO(
    @GeneratedValue
    @Id
    val markerId : Long,
    @ManyToOne
    val mapItem: MapItemDAO,
    val coordinates : Long,
    val description : String,
    @OneToMany
    val digitalResources : List<DigitalResourceDAO>
) {
    constructor() : this(0, MapItemDAO(),0,"description", emptyList())

    constructor(markerDTO: MarkerDTO, mapItem: MapItemDAO) : this(markerDTO.markerId,mapItem,markerDTO.coordinates,markerDTO.description, markerDTO.digitalResources.map { DigitalResourceDAO(it) })

}

