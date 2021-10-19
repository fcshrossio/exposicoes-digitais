package com.rossio.exhibitions.dto


import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.model.*
import java.util.*

data class ExhibitionDTO(
    val id: Long,
    val editor: EditorDTO,
    val items: List<ExhibitionItemDTO>,
    val title: String,
    val subtitle: String,
    val introduction : String,
    val cover: DigitalResourceDTO,
    val collaborators: List<UserDTO>,
    val creationDate: Date,
    val status: Status,
    val keywords: MutableList<Keywords>,
    val credits: String,
    var onlineResourcesNova : String,
    var bibliography : String,
    var audiovisualResources: String,
    var webPlaces: String
)
{

    constructor(exhibition : ExhibitionDAO) : this(
        exhibition.id,
        EditorDTO(exhibition.editor),
        exhibition.items.map { ExhibitionItemDTO(it) },
        exhibition.title,
        exhibition.subtitle,
        exhibition.introduction,
        DigitalResourceDTO(exhibition.cover),
        exhibition.collaborators.map { UserDTO(it) },
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.credits,
        exhibition.onlineResourcesNova,
        exhibition.bibliography,
        exhibition.audiovisualResources,
        exhibition.webPlaces
    )


}

data class ExhibitionDetailsDTO(
    val exhibitionId: Long,
    val title: String,
    val subtitle: String
)

