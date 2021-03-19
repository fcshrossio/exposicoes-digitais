package com.rossio.exhibitions.dto

import java.util.*

data class ExhibitionDTO(
    val id: Long,
    val title: String,
    val subtitle: String,
    val creator: UserDTO,
    val collaborators: List<UserDTO>,
    val creationDate: String,
    val status: String,
    val keywords: String
)
{
    constructor() : this(0,"","", UserDTO(0,"user"), emptyList(),"data","status","keywords")
}


open class ExhibitionItemDTO(
    open val id: Long,
    open val itemType: String,
    open val order: Long
)
{
    constructor() : this(0,"",0)
}

data class IntroductionItemDTO(
    override val id: Long,
    override val itemType: String,
    override val order: Long,
    val text: String

    ) : ExhibitionItemDTO(id, itemType, order) {

        constructor(itemDTO: ExhibitionItemDTO, text: String) : this(itemDTO.id,itemDTO.itemType,itemDTO.order,text) {

        }

        constructor() : this(0,"",0, "")

    }



data class TextItemDTO(
    override val id: Long,
    override val itemType: String,
    override val order: Long,
    val text: String

) : ExhibitionItemDTO(id, itemType, order) {

    constructor(itemDTO: ExhibitionItemDTO, text: String) : this(itemDTO.id,itemDTO.itemType,itemDTO.order,text) {

    }

    constructor() : this(0,"",0, "")

}

data class MapItemDTO(
    override val id: Long,
    override val itemType: String,
    override val order: Long,
    val text: String

) : ExhibitionItemDTO(id, itemType, order) {

    constructor(itemDTO: ExhibitionItemDTO, text: String) : this(itemDTO.id,itemDTO.itemType,itemDTO.order,text) {

    }

    constructor() : this(0,"",0, "")

}

data class AboutItemDTO(
    override val id: Long,
    override val itemType: String,
    override val order: Long,
    val text: String

) : ExhibitionItemDTO(id, itemType, order) {

    constructor(itemDTO: ExhibitionItemDTO, text: String) : this(itemDTO.id,itemDTO.itemType,itemDTO.order,text)

    constructor() : this(0,"",0, "")

}


data class UserDTO(
    val id: Long,
    val username: String
)