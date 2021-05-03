package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.MarkerDTO
import javax.persistence.*


@Entity
data class DigitalResourceDAO(
    @GeneratedValue
    @Id
    val id : Long,
    val name: String
) {
    constructor() : this(0,"")

    constructor(resource: DigitalResourceDTO) : this(resource.id, resource.name)
}



