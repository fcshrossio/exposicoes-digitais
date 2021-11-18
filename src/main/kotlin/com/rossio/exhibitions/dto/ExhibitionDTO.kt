package com.rossio.exhibitions.dto


import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.model.*
import java.util.*

data class ExhibitionDTO(
    val id: Long,
    val editor: UserSimpleDTO,
    val items: List<ExhibitionItemDTO>,
    val title: String,
    val subtitle: String,
    val estimatedViewingTime: String,
    val introduction : String,
    val cover: DigitalResourceDTO,
    val coverEditable : String,
    val collaborators: List<UserDTO>,
    val creationDate: Date,
    val status: Status,
    val keywords: MutableList<Keywords>,
    val credits: String,
    var onlineResourcesNova : String,
    var bibliography : String,
    var audiovisualResources: String,
    var webPlaces: String,
    var markers : List<MarkerDTO>
)
{

    constructor(exhibition : ExhibitionDAO) : this(
        exhibition.id,
        UserSimpleDTO(exhibition.editor),
        exhibition.items.map { ExhibitionItemDTO(it) },
        exhibition.title,
        exhibition.subtitle,
        exhibition.estimatedViewingTime,
        exhibition.introduction,
        DigitalResourceDTO(exhibition.cover),
        exhibition.coverEditable,
        exhibition.collaborators.map { UserDTO(it) },
        exhibition.creationDate,
        exhibition.status,
        exhibition.keywords,
        exhibition.credits,
        exhibition.onlineResourcesNova,
        exhibition.bibliography,
        exhibition.audiovisualResources,
        exhibition.webPlaces,
        exhibition.markers.map { MarkerDTO(it) }
    )


}

data class ExhibitionDetailsDTO(
    val exhibitionId: Long,
    val title: String,
    val subtitle: String
) {

    constructor(exhibition: ExhibitionDAO) : this(
        exhibition.id,
        exhibition.title,
        exhibition.subtitle
    )
}