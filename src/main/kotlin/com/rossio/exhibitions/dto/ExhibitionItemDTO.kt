package com.rossio.exhibitions.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.rossio.exhibitions.model.AboutItemDAO
import com.rossio.exhibitions.model.IntroductionItemDAO
import com.rossio.exhibitions.model.MapItemDAO
import com.rossio.exhibitions.model.TextItemDAO

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "itemType", visible = true
)
@JsonSubTypes(
        JsonSubTypes.Type(TextItemDTO::class, name = "text"),
        JsonSubTypes.Type(IntroductionItemDTO::class, name = "introduction"),
        JsonSubTypes.Type(MapItemDTO::class, name = "map"),
        JsonSubTypes.Type(AboutItemDTO::class, name = "about")
)
abstract class ExhibitionItemDTO(
    open val id: Long,
    open val position: Long
)


data class IntroductionItemDTO(
    override val id: Long,
    override val position: Long,
    val text: String

) : ExhibitionItemDTO(id, position) {

    constructor(item: IntroductionItemDAO) : this(item.id,item.position,item.text)

    constructor() : this(0,0, "")

}



data class TextItemDTO(
    override val id: Long,
    override val position: Long,
    val text: String

) : ExhibitionItemDTO(id, position) {

    constructor(item: TextItemDAO) : this(item.id,item.position,item.text)

    constructor() : this(0,0, "")

}

data class MapItemDTO(
    override val id: Long,
    override val position: Long,
    val text: String

) : ExhibitionItemDTO(id, position) {

    constructor(item: MapItemDAO) : this(item.id,item.position,item.text)

    constructor() : this(0,0, "")

}

data class AboutItemDTO(
    override val id: Long,
    override val position: Long,
    val text: String

) : ExhibitionItemDTO(id, position) {

    constructor(item: AboutItemDAO) : this(item.id,item.position,item.text)

    constructor() : this(0,0, "")

}