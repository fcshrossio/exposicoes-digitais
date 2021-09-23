package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.MarkerDTO
import javax.persistence.*

@Entity
data class MarkerDAO(
    @GeneratedValue
    @Id
    val markerId : Long,
    val coordinates : Long,
    val description : String,
    @OneToMany
    val digitalResources : List<DigitalResourceDAO>
) {
    constructor(markerDTO: MarkerDTO) : this(markerDTO.markerId,markerDTO.coordinates,markerDTO.description, markerDTO.digitalResources.map { DigitalResourceDAO(it) })

}

