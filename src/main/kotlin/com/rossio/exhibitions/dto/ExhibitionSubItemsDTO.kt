package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.SubItemDAO




data class SubItemDTO(
    val id: Long,
    val position: Long,
    val itemType: String,
    val textSections: List<String>,
    val digitalResources: List<DigitalResourceDTO>
) {
    constructor(item: SubItemDAO) : this(item.id,item.position, item.itemType, item.textSections, item.digitalResources.map { DigitalResourceDTO(it) })

    constructor() : this(0,0, "", emptyList(), emptyList())
}
