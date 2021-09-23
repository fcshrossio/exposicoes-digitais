package com.rossio.exhibitions.dto

import com.fasterxml.jackson.annotation.*
import com.rossio.exhibitions.model.*



data class ExhibitionItemDTO(
    val id: Long,
    val position: Long,
    //val exhibitionId: Long,
    val title: String,
    val text: String,
    val subItems: List<SubTextItemDTO>
)
{
    constructor() : this(0,0, "","", emptyList())
    constructor(exhibitionItemDAO: ExhibitionItemDAO) : this(
        exhibitionItemDAO.id,
        exhibitionItemDAO.position,
        exhibitionItemDAO.title,
        exhibitionItemDAO.text,
        mutableListOf()
        )
}




data class MapItemDTO(
    val id: Long,
    val position: Long,
    val exhibitionId: Long,
    val title: String,
    val text: String,
    val markers : List<MarkerDTO>

) {

   // constructor(item: MapItemDAO) : this(item.id,item.position,item.exhibition.id,item.title,item.text,
     //   item.markers.map { MarkerDTO(it) } )

    constructor() : this(0,0,0, "","", emptyList())

}

data class AboutItemDTO(
    val id: Long,
    val position: Long,
    val exhibitionId: Long,
    val title: String,
    val text: String,
    val subItems : List<SubAboutItemDTO>

)  {

    //constructor(item: AboutItemDAO) : this(item.id,item.position,item.exhibition.id,item.title,item.text, item.subItems.map { SubAboutItemDTO( it) })

    constructor() : this(0,0,0, "","", emptyList())

}

