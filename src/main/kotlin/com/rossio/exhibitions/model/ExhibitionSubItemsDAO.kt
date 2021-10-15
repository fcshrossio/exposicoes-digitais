package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.SubAboutItemDTO
import com.rossio.exhibitions.dto.SubItemDTO
import javax.persistence.*

@Entity
data class SubAboutDAO(
    @Id
    @GeneratedValue
    val id: Long,
    val position: Long,
    val link: String

) {
    constructor(subitem: SubAboutItemDTO) : this(subitem.id, subitem.position, subitem.link)

}


@Entity
data class SubItemDAO(
    @Id
    @GeneratedValue
    val id: Long,
    val position: Long,
    val itemType: String,
    @ElementCollection
    @CollectionTable
    val textSections: List<String>,
    @OneToMany
    val digitalResources : List<DigitalResourceDAO>
) {
    constructor() : this(0,0,"texttext", mutableListOf(), mutableListOf())

    constructor(subitem: SubItemDTO) : this(subitem.id, subitem.position, subitem.itemType, subitem.textSections, subitem.digitalResources.map { DigitalResourceDAO(it) })
}