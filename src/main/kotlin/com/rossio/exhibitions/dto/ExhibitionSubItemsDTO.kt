package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.SubAboutDAO
import com.rossio.exhibitions.model.SubTextDAO

data class SubAboutItemDTO(
    val id: Long,
    val position: Long,
    val link: String
) {
    constructor(item: SubAboutDAO) : this(item.id,item.position,item.link)
}


data class SubTextItemDTO(
    val id: Long,
    val position: Long,
    val itemType: String,
    val textSections: List<String>,
    val digitalResources: List<DigitalResourceDTO>
) {
    constructor(item: SubTextDAO) : this(item.id,item.position, item.itemType, item.textSections, item.digitalResources.map { DigitalResourceDTO(it) })

    constructor() : this(0,0, "", emptyList(), emptyList())
}
