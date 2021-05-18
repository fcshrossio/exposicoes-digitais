package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.model.ExhibitionDAO
import javax.persistence.*

@Entity
@Inheritance
@DiscriminatorColumn(name="itemType", discriminatorType = DiscriminatorType.STRING)
//@MappedSuperclass
abstract class ExhibitionItemDAO(
    @Id
    @GeneratedValue
    open var id: Long,
    open var position: Long,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    open var exhibition : ExhibitionDAO
) {
    constructor() : this(0,0,ExhibitionDAO())
}

@Entity
@DiscriminatorValue(value = "introduction")
data class IntroductionItemDAO(
    override var id: Long,
    override var position: Long,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String

) : ExhibitionItemDAO(id, position, exhibition) {

    constructor(item: IntroductionItemDTO, exhibition: ExhibitionDAO) : this(item.id,0, exhibition, item.text)



    constructor() : this(0,0,ExhibitionDAO(),"")
}

@Entity
@DiscriminatorValue(value = "text")
data class TextItemDAO(
    override var id: Long,
    override var position: Long,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var subTextItems : MutableList<SubTextDAO>

) : ExhibitionItemDAO(id, position, exhibition) {

    constructor(item: TextItemDTO, exhibition: ExhibitionDAO) : this(item.id,item.position, exhibition, item.text, mutableListOf())
    constructor() : this(0,0,ExhibitionDAO(),"", mutableListOf())

    fun addSubText(subTextDAO: SubTextDAO) {
        subTextItems.add(subTextDAO)
    }

    fun removeSubText(subTextDAO: SubTextDAO) {
        subTextItems.remove(subTextDAO)
    }
}

@Entity
@DiscriminatorValue(value = "map")
data class MapItemDAO(
    override var id: Long,
    override var position: Long,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var markers : MutableList<MarkerDAO>

) : ExhibitionItemDAO(id,  position, exhibition) {

    constructor(item: MapItemDTO, exhibition: ExhibitionDAO) : this(item.id,item.position, exhibition, item.text, mutableListOf() )

    constructor() : this(0,0,ExhibitionDAO(),"", mutableListOf())

    fun addMarker(markerDAO: MarkerDAO) {
        markers.add(markerDAO)
    }

    fun removeMarker(markerDAO: MarkerDAO) {
        markers.remove(markerDAO)
    }
}

@Entity
@DiscriminatorValue(value = "about")
data class AboutItemDAO(
    override var id: Long,
    override var position: Long,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var subItems : MutableList<SubAboutDAO>

) : ExhibitionItemDAO(id, position, exhibition) {

    constructor(item: AboutItemDTO, exhibition: ExhibitionDAO) : this(item.id,item.position, exhibition, item.text, mutableListOf())
    constructor() : this(0,0,ExhibitionDAO(),"", mutableListOf())

    fun addSubAbout(subAboutDAO: SubAboutDAO) {
        subItems.add(subAboutDAO)
    }

    fun removeSubAbout(subAboutDAO: SubAboutDAO) {
        subItems.remove(subAboutDAO)
    }

}

