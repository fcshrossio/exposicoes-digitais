package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.exception.WrongTypeException
import com.rossio.exhibitions.model.ExhibitionDAO
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
    var subTextItems : MutableList<SubTextDAO>
) {
    constructor(exhibitionItemDTO: ExhibitionItemDTO) : this(
        exhibitionItemDTO.id,
        exhibitionItemDTO.position,
        exhibitionItemDTO.title,
        exhibitionItemDTO.text,
        mutableListOf()
    )

    constructor() : this(0,0,"","", mutableListOf())



    fun editItem(item:ExhibitionItemDAO) : Boolean {
        return true
    }
}






