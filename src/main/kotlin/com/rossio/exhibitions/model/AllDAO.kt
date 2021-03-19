package com.rossio.exhibitions.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@Entity
data class ExhibitionDAO(
    @Id
    @GeneratedValue
    var id: Long,
    var title: String,
    var subtitle: String
) {
    constructor() : this(0,"","")

    constructor(exhibition : ExhibitionDAO) : this(exhibition.id,exhibition.title,exhibition.subtitle)
}

@MappedSuperclass
open class ExhibitionItemDAO(
    @Id
    @GeneratedValue
    open var id: Long,
    open var itemType: String,
    open var order: Long
)

@Entity
data class IntroductionItemDAO(
    override var id: Long,
    override var itemType: String,
    override var order: Long,
    var text: String

    ) : ExhibitionItemDAO(id, itemType, order) {

    constructor(item: ExhibitionItemDAO, text: String ) : this(item.id,item.itemType,item.order, text) {

    }

    constructor() : this(0,"",0,"") {

    }
}

@Entity
data class TextItemDAO(
    override var id: Long,
    override var itemType: String,
    override var order: Long,
    var text: String

) : ExhibitionItemDAO(id, itemType, order) {

    constructor(item: ExhibitionItemDAO, text: String ) : this(item.id,item.itemType,item.order, text) {

    }

    constructor() : this(0,"",0,"") {

    }
}

@Entity
data class MapItemDAO(
    override var id: Long,
    override var itemType: String,
    override var order: Long,
    var text: String

) : ExhibitionItemDAO(id, itemType, order) {

    constructor(item: ExhibitionItemDAO, text: String ) : this(item.id,item.itemType,item.order, text) {

    }

    constructor() : this(0,"",0,"") {

    }
}

@Entity
data class AboutItemDAO(
    override var id: Long,
    override var itemType: String,
    override var order: Long,
    var text: String

) : ExhibitionItemDAO(id, itemType, order) {

    constructor(item: ExhibitionItemDAO, text: String ) : this(item.id,item.itemType,item.order, text) {

    }

    constructor() : this(0,"",0,"") {

    }
}

