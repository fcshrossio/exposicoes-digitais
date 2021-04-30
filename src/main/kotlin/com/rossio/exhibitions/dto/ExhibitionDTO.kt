package com.rossio.exhibitions.dto

import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.IntroductionItemDTO
import com.rossio.exhibitions.model.ExhibitionDAO
import com.rossio.exhibitions.model.Keywords
import com.rossio.exhibitions.model.Status
import java.util.*

data class ExhibitionDTO(
    val id: Long,
    val editor: UserDTO,
    val items: List<ExhibitionItemDTO>,
    val title: String,
    val subtitle: String,
    val cover: DigitalResourceDTO,
    val collaborators: List<UserDTO>,
    val creationDate: Date,
    val status: Status,
    val keywords: Keywords,
    val digitalResources: List<DigitalResourceDTO>
)
{
    constructor() : this(0,UserDTO(), emptyList(),"" , "", DigitalResourceDTO(), emptyList(), Date(0), Status.PRIVATE, Keywords.Teste1, emptyList())

    constructor(exhibition : ExhibitionDAO) : this(
        exhibition.id,
        UserDTO(exhibition.editor),
        exhibition.items.map { IntroductionItemDTO() },
        exhibition.title,
        exhibition.subtitle,
        DigitalResourceDTO(),
        exhibition.collaborators.map { UserDTO(it) },
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.digitalResources.map { DigitalResourceDTO() }
    )


}