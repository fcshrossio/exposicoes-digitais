package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.exception.NotFoundException
import javax.persistence.*

@Entity
data class ExhibitionItemDAO(
    @Id
    @GeneratedValue
    var id: Long,
    var position: Long,
    var title: String,
    var text: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var subItems : MutableList<SubItemDAO>
) {
    constructor(exhibitionItemDTO: ExhibitionItemDTO) : this(
        exhibitionItemDTO.id,
        exhibitionItemDTO.position,
        exhibitionItemDTO.title,
        exhibitionItemDTO.text,
        exhibitionItemDTO.subItems.map { SubItemDAO(it) } as MutableList<SubItemDAO>
    )

    constructor() : this(0,0,"","", mutableListOf())

    fun addSubItem(subItemDAO: SubItemDAO)
    {
        if(!subItems.contains(subItemDAO))
        {
            subItems.add(subItemDAO)
        }
        else
        {
            throw NotFoundException("sub item already exists")
        }
    }

    fun editItem(item:ExhibitionItemDAO) : Boolean {
        return true
    }
}






