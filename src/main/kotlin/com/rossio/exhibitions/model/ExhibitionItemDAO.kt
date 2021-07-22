package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.exception.WrongTypeException
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
    open var title: String,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    open var exhibition : ExhibitionDAO
) {

    abstract fun editItem(item:ExhibitionItemDAO) : Boolean
}

@Entity
@DiscriminatorValue(value = "introduction")
data class IntroductionItemDAO(
    override var id: Long,
    override var position: Long,
    override var title: String,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String

) : ExhibitionItemDAO(id, position,title, exhibition) {

    constructor(item: IntroductionItemDTO, exhibition: ExhibitionDAO) : this(item.id,0, item.title, exhibition, item.text)


    override fun editItem(item: ExhibitionItemDAO): Boolean {
        if ( item is IntroductionItemDAO && item.id === this.id)
        {
            this.text = item.text
        }
        else
        {
            WrongTypeException("Wrong type")
        }
        return false
    }
}

@Entity
@DiscriminatorValue(value = "text")
data class TextItemDAO(
    override var id: Long,
    override var position: Long,
    override var title: String,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var subTextItems : MutableList<SubTextDAO>

) : ExhibitionItemDAO(id, position,title, exhibition) {

    constructor(item: TextItemDTO, exhibition: ExhibitionDAO) : this(item.id,item.position, item.title, exhibition, item.text, mutableListOf())

    fun addSubText(subTextDAO: SubTextDAO) {
        subTextItems.add(subTextDAO)
    }

    fun removeSubText(subTextDAO: SubTextDAO) {
        subTextItems.remove(subTextDAO)
    }

    override fun editItem(item: ExhibitionItemDAO): Boolean {
        if ( item is TextItemDAO && item.id === this.id)
        {
            this.text = item.text
        }
        else
        {
            WrongTypeException("Wrong type")
        }
        return false
    }
}

@Entity
@DiscriminatorValue(value = "map")
data class MapItemDAO(
    override var id: Long,
    override var position: Long,
    override var title: String,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var markers : MutableList<MarkerDAO>

) : ExhibitionItemDAO(id,  position, title,  exhibition) {

    constructor(item: MapItemDTO, exhibition: ExhibitionDAO) : this(item.id,item.position, item.title, exhibition, item.text, mutableListOf() )


    fun addMarker(markerDAO: MarkerDAO) {
        markers.add(markerDAO)
    }

    fun removeMarker(markerDAO: MarkerDAO) {
        markers.remove(markerDAO)
    }

    override fun editItem(item: ExhibitionItemDAO): Boolean {
        if ( item is MapItemDAO && item.id === this.id)
        {
            this.text = item.text
        }
        else
        {
            WrongTypeException("Wrong type")
        }
        return false
    }
}

@Entity
@DiscriminatorValue(value = "about")
data class AboutItemDAO(
    override var id: Long,
    override var position: Long,
    override var title: String,
    @ManyToOne
    @JoinColumn(name = "exhibition_id",referencedColumnName="id")
    override var exhibition: ExhibitionDAO,
    var text: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var subItems : MutableList<SubAboutDAO>

) : ExhibitionItemDAO(id, position, title, exhibition) {

    constructor(item: AboutItemDTO, exhibition: ExhibitionDAO) : this(item.id,item.position, item.title, exhibition, item.text, mutableListOf())

    fun addSubAbout(subAboutDAO: SubAboutDAO) {
        subItems.add(subAboutDAO)
    }

    fun removeSubAbout(subAboutDAO: SubAboutDAO) {
        subItems.remove(subAboutDAO)
    }

    override fun editItem(item: ExhibitionItemDAO): Boolean {
        if ( item is AboutItemDAO && item.id === this.id)
        {
            this.text = item.text
        }
        else
        {
            WrongTypeException("Wrong type")
        }
        return false
    }

}

