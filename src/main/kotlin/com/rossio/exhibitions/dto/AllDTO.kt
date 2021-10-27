package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.DigitalResourceDAO
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
    var digitalResources : List<DigitalResourceDTO>
) {
    constructor() : this(0,"", mutableListOf())


}








