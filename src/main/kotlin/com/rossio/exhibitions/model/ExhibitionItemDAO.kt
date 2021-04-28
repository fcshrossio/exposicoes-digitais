package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.AboutItemDTO
import com.rossio.exhibitions.dto.IntroductionItemDTO
import com.rossio.exhibitions.dto.MapItemDTO
import com.rossio.exhibitions.dto.TextItemDTO
import javax.persistence.*

@Entity
@Inheritance
@DiscriminatorColumn(name="itemType", discriminatorType = DiscriminatorType.STRING)
//@MappedSuperclass
abstract class ExhibitionItemDAO(
    @Id
    @GeneratedValue
    open var id: Long,
    open var position: Long
) {
    constructor() : this(0,0)
}

@Entity
@DiscriminatorValue(value = "introduction")
data class IntroductionItemDAO(
    override var id: Long,
    override var position: Long,
    var text: String

) : ExhibitionItemDAO(id, position) {

    constructor(item: IntroductionItemDTO) : this(item.id,item.position, item.text)



    constructor() : this(0,0,"")
}

@Entity
@DiscriminatorValue(value = "text")
data class TextItemDAO(
    override var id: Long,
    override var position: Long,
    var text: String

) : ExhibitionItemDAO(id, position) {

    constructor(item: TextItemDTO) : this(item.id,item.position, item.text)
    constructor() : this(0,0,"")
}

@Entity
@DiscriminatorValue(value = "map")
data class MapItemDAO(
    override var id: Long,
    override var position: Long,
    var text: String

) : ExhibitionItemDAO(id,  position) {

    constructor(item: MapItemDTO) : this(item.id,item.position, item.text)

    constructor() : this(0,0,"")
}

@Entity
@DiscriminatorValue(value = "about")
data class AboutItemDAO(
    override var id: Long,
    override var position: Long,
    var text: String

) : ExhibitionItemDAO(id, position) {

    constructor(item: AboutItemDTO) : this(item.id, item.position, item.text)
    constructor() : this(0, 0, "")
}