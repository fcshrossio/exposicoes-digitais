package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.EditorDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.dto.UserPasswordDTO
import com.rossio.exhibitions.enums.Roles
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class UserDAO(
    @Id
    @GeneratedValue
    open val id: Long,
    open val username: String,
    open var password: String,
    open val role: Roles
) {

    constructor(user: UserDTO) : this(user.id,user.username,"",Roles.EDITOR)
    constructor() : this(0,"","",Roles.ADMIN)
}

@Entity
open class AdminDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
    override var password: String,
    override val role: Roles
) : UserDAO(id, username, password, role) {

    constructor(user: UserDTO) : this(user.id,user.username,user.password,Roles.ADMIN)
    constructor(user: UserPasswordDTO) : this(0,user.username,user.password,Roles.ADMIN)
    constructor() : this(0,"","",Roles.ADMIN)
}

@Entity
open class EditorDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
    override var password: String,
    override val role: Roles
) : UserDAO(id, username, password, role) {

    constructor(user: UserDTO) : this(user.id,user.username,user.password,Roles.EDITOR)
    constructor(user: UserPasswordDTO) : this(0,user.username,user.password,Roles.EDITOR)
    constructor() : this(0,"","",Roles.EDITOR)
}


@Entity
open class CollaboratorDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
    override var password: String,
    override val role: Roles,
    @OneToMany
    val collaborationList:  MutableList<ExhibitionDAO>
) : UserDAO(id, username, password, role) {

    fun removeExhibition(exhibitionDAO: ExhibitionDAO) = {
        if(collaborationList.contains(exhibitionDAO))
            collaborationList.remove(exhibitionDAO)
    }

    constructor(user: UserDTO) : this(user.id,user.username,user.password,Roles.COLLABORATOR, mutableListOf())
    constructor(user: UserPasswordDTO) : this(0,user.username,user.password,Roles.COLLABORATOR, mutableListOf())
    constructor() : this(0,"","",Roles.COLLABORATOR,  mutableListOf())
}
