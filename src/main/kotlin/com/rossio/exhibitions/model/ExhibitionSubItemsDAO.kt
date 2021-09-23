package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.SubAboutItemDTO
import com.rossio.exhibitions.dto.SubTextItemDTO
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
data class SubTextDAO(
    @Id
    @GeneratedValue
    val id: Long,
    val position: Long,
    val text: String,
    @OneToMany
    val digitalResources : List<DigitalResourceDAO>
) {
    constructor(subitem: SubTextItemDTO) : this(subitem.id, subitem.position, subitem.text, subitem.digitalResources.map { DigitalResourceDAO(it) })
}