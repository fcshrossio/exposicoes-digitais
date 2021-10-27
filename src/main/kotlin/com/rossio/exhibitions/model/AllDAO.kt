package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.MarkerDTO
import com.rossio.exhibitions.dto.SavedResourcesDTO
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

    constructor(savedResources: SavedResourcesDTO) : this(savedResources.id, savedResources.name, savedResources.digitalResources.map { DigitalResourceDAO(it) })
}

