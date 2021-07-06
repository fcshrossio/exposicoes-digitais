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
    @ManyToOne
    val aboutItemDAO: AboutItemDAO,
    val link: String

) {
    constructor(subitem: SubAboutItemDTO, aboutItemDAO: AboutItemDAO) : this(subitem.id, subitem.position, aboutItemDAO, subitem.link)

}


@Entity
data class SubTextDAO(
    @Id
    @GeneratedValue
    val id: Long,
    val position: Long,
    val text: String,
    @ManyToOne
    val textItemDAO: TextItemDAO,
    @OneToMany
    val digitalResources : List<DigitalResourceDAO>
) {
    constructor(subitem: SubTextItemDTO, textItemDAO: TextItemDAO) : this(subitem.id, subitem.position, subitem.text, textItemDAO, subitem.digitalResources.map { DigitalResourceDAO(it) })
}