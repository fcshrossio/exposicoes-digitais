package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.DigitalResourceDAO
import com.rossio.exhibitions.model.SavedResourcesDAO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany


data class CreditsDTO(
    val value: String
)

data class SavedResourcesDTO(

    val id : Long,
    val name: String,
    var digitalResources : List<DigitalResourceDTO>,
    var owner : UserSimpleDTO
) {
    constructor() : this(0,"", mutableListOf(), UserSimpleDTO(0,""))

    constructor(savedResources: SavedResourcesDAO) : this(savedResources.id, savedResources.name, savedResources.digitalResources.map { DigitalResourceDTO(it) }, UserSimpleDTO(savedResources.owner))
}








