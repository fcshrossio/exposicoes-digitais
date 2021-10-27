package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.SubItemDTO
import javax.persistence.*


@Entity
data class SubItemDAO(
    @Id
    @GeneratedValue
    val id: Long,
    var position: Long,
    val itemType: String,
    @ElementCollection
    @CollectionTable
    @Column(length = 10000000)
    var textSections: List<String>,
    @OneToMany
    var digitalResources : List<DigitalResourceDAO>
) {
    constructor() : this(0,0,"texttext", mutableListOf(), mutableListOf())

    constructor(subitem: SubItemDTO) : this(subitem.id, subitem.position, subitem.itemType, subitem.textSections, subitem.digitalResources.map { DigitalResourceDAO(it) })

    fun editSubItem(subitem:SubItemDAO) : Boolean
    {
        if(subitem.id == this.id)
        {

            this.position = subitem.position
            this.textSections = subitem.textSections
            this.digitalResources = subitem.digitalResources
            return true
        }
        return false
    }
}