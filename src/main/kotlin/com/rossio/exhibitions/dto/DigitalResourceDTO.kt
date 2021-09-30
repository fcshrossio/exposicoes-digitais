package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.DigitalResourceDAO
import java.util.*


data class DigitalResourceDTO(
    val id:Long,
    val title: String,
    val description: String,
    val dataProvider: String,
    val date: Date,
    val physicalDescription: String,
    val authors: String,
    val subject: String
){

    constructor() : this(0,"","","",Date(), "", "", "")

    constructor(resource: DigitalResourceDAO) : this(resource.id,resource.title,resource.description,resource.dataProvider,resource.date,resource.physicalDescription,resource.authors,resource.subject)
}
