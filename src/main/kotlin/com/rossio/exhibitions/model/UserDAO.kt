package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.UserDTO
import javax.persistence.*

@Entity
open class UserDAO(
    @Id
    @GeneratedValue
    open val id: Long,
    open val username: String
) {
    constructor() : this(0,"")

    constructor(user: UserDTO) : this(user.id,user.username)
}

@Entity
open class AdminDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String
) : UserDAO(id, username) {
    constructor() : this(0,"")

    constructor(user: UserDTO) : this(user.id,user.username)
}

@Entity
open class EditorDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
) : UserDAO(id, username) {
    constructor() : this(0,"")

    constructor(user: UserDTO) : this(user.id,user.username)
}


@Entity
open class CollaboratorDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
    @OneToMany
    val collaborationList:  List<ExhibitionDAO>
) : UserDAO(id, username) {
    constructor() : this(0,"", emptyList())

    constructor(user: UserDTO) : this(user.id,user.username, emptyList())
}
