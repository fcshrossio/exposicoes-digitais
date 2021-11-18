package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.exception.NotFoundException
import javax.persistence.*

@Entity
data class ExhibitionItemDAO(
    @Id
    @GeneratedValue
    var id: Long,
    var position: Int,
    var title: String,
    @ManyToOne
    var exhibition : ExhibitionDAO,

    @OneToMany(mappedBy = "exhibitionItem", cascade = [CascadeType.ALL], orphanRemoval = true)
    var subItems : MutableList<SubItemDAO>
) {
    constructor(exhibitionItemDTO: ExhibitionItemDTO, exhibition: ExhibitionDAO ) : this(
        exhibitionItemDTO.id,
        exhibitionItemDTO.position,
        exhibitionItemDTO.title,
        exhibition,
        exhibitionItemDTO.subItems.map {  } as MutableList<SubItemDAO>
    )

    constructor() : this(0,0,"", ExhibitionDAO(), mutableListOf())


    fun removeSubItem(subItemDAO: SubItemDAO)
    {
        if(subItems.contains(subItemDAO))
        {
            subItems.remove(subItemDAO)
        }
        else
        {
            throw NotFoundException("sub item does not exist")
        }
    }

    fun editItem(item:ExhibitionItemDAO) : Boolean
    {
        if(item.id == this.id)
        {
            this.title = item.title
            this.position = item.position
            return true
        }
       return false
    }
}






