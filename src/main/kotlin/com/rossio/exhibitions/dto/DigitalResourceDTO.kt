package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.DigitalResourceDAO


data class DigitalResourceDTO(
    val id:Long,
    val name: String
){

    constructor() : this(0,"")

    constructor(resource: DigitalResourceDAO) : this(resource.id,resource.name)
}
