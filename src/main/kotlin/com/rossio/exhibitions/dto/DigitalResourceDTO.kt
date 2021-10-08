package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.DigitalResourceDAO
import java.util.*


data class DigitalResourceDTO(
    val id:Long,
    val title: String,
    val description: String,
    val dataProvider: String,
    val date: String,
    val physicalDescription: String,
    val authors: List<String>,
    val subject: String,
    val rights: List<String>,
    val digitalFormat: String,
    val identifier : String
){

    constructor() : this(0,"","","","", "", emptyList(), "", emptyList(),"","")

    constructor(resource: DigitalResourceDAO) : this(resource.id,resource.title,resource.description,resource.dataProvider,resource.date,resource.physicalDescription,resource.authors,resource.subject, resource.rights, resource.digitalFormat, resource.identifier)
}
