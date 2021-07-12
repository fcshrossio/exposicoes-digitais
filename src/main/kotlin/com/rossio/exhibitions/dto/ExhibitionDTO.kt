package com.rossio.exhibitions.dto

import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.IntroductionItemDTO
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import java.util.*

data class ExhibitionDTO(
    val id: Long,
    val editor: EditorDTO,
    val items: List<ExhibitionItemDTO>,
    val title: String,
    val subtitle: String,
    val cover: DigitalResourceDTO,
    val collaborators: List<UserDTO>,
    val creationDate: Date,
    val status: Status,
    val keywords: MutableList<Keywords>,
    val digitalResources: List<DigitalResourceDTO>
)
{

    constructor(exhibition : ExhibitionDAO) : this(
        exhibition.id,
        EditorDTO(exhibition.editor),
        exhibition.items.map { mapItemDAOtoDTO(it) },
        exhibition.title,
        exhibition.subtitle,
        DigitalResourceDTO(exhibition.cover),
        exhibition.collaborators.map { UserDTO(it) },
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.digitalResources.map { DigitalResourceDTO(it) }
    )


}

data class ExhibitionDetailsDTO(
    val exhibitionId: Long,
    val title: String,
    val subtitle: String
)


fun mapItemDAOtoDTO(item: ExhibitionItemDAO) : ExhibitionItemDTO =

    when (item) {
        is IntroductionItemDAO -> IntroductionItemDTO(item)
        is TextItemDAO -> TextItemDTO(item)
        is MapItemDAO ->  MapItemDTO(item)
        is AboutItemDAO -> AboutItemDTO(item)
        else -> throw NotFoundException("") //TODO exception
    }