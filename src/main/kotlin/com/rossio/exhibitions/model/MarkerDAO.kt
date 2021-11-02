package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.MarkerDTO
import javax.persistence.*

@Entity
data class MarkerDAO(
    @GeneratedValue
    @Id
    val id : Long,
    @ManyToOne
    var exhibition : ExhibitionDAO,
    @ElementCollection
    @CollectionTable
    var coordinates : MutableList<Double>,
    var title : String,
) {
    constructor(markerDTO: MarkerDTO, exhibition: ExhibitionDAO) : this(markerDTO.id,exhibition, markerDTO.coordinates,markerDTO.title)

    constructor() :  this(0,ExhibitionDAO(),mutableListOf(0.0,0.0),"")

    fun editMarker(marker:MarkerDAO) : Boolean
    {
        if(marker.id == this.id)
        {
            this.title = marker.title
            this.coordinates = marker.coordinates
            return true
        }
        return false
    }
}

