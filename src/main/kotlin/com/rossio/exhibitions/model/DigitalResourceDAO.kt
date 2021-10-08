package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.DigitalResourceDTO
import java.util.*
import javax.persistence.*

@Entity
data class DigitalResourceDAO(
    @GeneratedValue
    @Id
    val id : Long,
    val title: String,
    val description: String,
    val dataProvider: String,
    val date: String,
    val physicalDescription: String,
    @ElementCollection
    @CollectionTable
    val authors: List<String>,
    val subject: String,
    @ElementCollection
    @CollectionTable
    val rights: List<String>,
    val digitalFormat: String,
    val identifier: String
) {
    constructor() : this(0,"","","","","", emptyList(), "", emptyList(), "","")

    constructor(resource: DigitalResourceDTO) : this(resource.id, resource.title,resource.description,resource.dataProvider,resource.date,resource.physicalDescription,resource.authors,resource.subject, resource.rights, resource.digitalFormat, resource.identifier)
}


