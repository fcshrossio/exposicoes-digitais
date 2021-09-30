package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.DigitalResourceDTO
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class DigitalResourceDAO(
    @GeneratedValue
    @Id
    val id : Long,
    val title: String,
    val description: String,
    val dataProvider: String,
    val date: Date,
    val physicalDescription: String,
    val authors: String,
    val subject: String
) {
    constructor() : this(0,"","","",Date(),"","", "")

    constructor(resource: DigitalResourceDTO) : this(resource.id, resource.title,resource.description,resource.dataProvider,resource.date,resource.physicalDescription,resource.authors,resource.subject)
}


