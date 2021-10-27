package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.MarkerDTO
import javax.persistence.*

@Entity
data class SavedResourcesDAO(
    @GeneratedValue
    @Id
    val id : Long,
    val name: String,
    @OneToMany
    var digitalResources : List<DigitalResourceDAO>
) {
    constructor() : this(0,"", mutableListOf())


}

