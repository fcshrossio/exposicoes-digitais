package com.rossio.exhibitions.dto


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
    val digitalResources: List<DigitalResourceDTO>,
    val credits: String,
    var onlineResourcesNova : String,
    var bibliography : String,
    var audioVisualResources: String,
    var webPlaces: String
)
{

    constructor(exhibition : ExhibitionDAO) : this(
        exhibition.id,
        EditorDTO(exhibition.editor),
        exhibition.items.map { ExhibitionItemDTO(it) },
        exhibition.title,
        exhibition.subtitle,
        DigitalResourceDTO(exhibition.cover),
        exhibition.collaborators.map { UserDTO(it) },
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.digitalResources.map { DigitalResourceDTO(it) },
        exhibition.credits,
        exhibition.onlineResourcesNova,
        exhibition.bibliography,
        exhibition.audioVisualResources,
        exhibition.webPlaces
    )


}

data class ExhibitionDetailsDTO(
    val exhibitionId: Long,
    val title: String,
    val subtitle: String
)

