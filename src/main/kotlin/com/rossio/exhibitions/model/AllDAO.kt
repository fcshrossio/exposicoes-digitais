package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.DigitalResourceDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass



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






