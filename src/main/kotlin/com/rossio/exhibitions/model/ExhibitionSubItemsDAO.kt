package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.SubItemDTO
import javax.persistence.*


@Entity
data class SubItemDAO(
    @Id
    @GeneratedValue
    val id: Long,
    val position: Long,
    val itemType: String,
    @ElementCollection
    @CollectionTable
    @Column(length = 10000000)
    val textSections: List<String>,
    @OneToMany
    val digitalResources : List<DigitalResourceDAO>
) {
    constructor() : this(0,0,"texttext", mutableListOf(), mutableListOf())

    constructor(subitem: SubItemDTO) : this(subitem.id, subitem.position, subitem.itemType, subitem.textSections, subitem.digitalResources.map { DigitalResourceDAO(it) })
}