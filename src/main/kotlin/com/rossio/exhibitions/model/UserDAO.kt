package com.rossio.exhibitions.model

import com.rossio.exhibitions.dto.EditorDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.dto.UserPasswordDTO
import com.rossio.exhibitions.dto.UserSimpleDTO
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
    open val role: Roles,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    open val savedResources: MutableList<SavedResourcesDAO>
) {

    constructor(user: UserDTO) : this(user.id,user.username,"",Roles.EDITOR, mutableListOf())
    constructor(user: UserSimpleDTO) : this(user.id,user.username,"",Roles.EDITOR, mutableListOf())
    constructor() : this(0,"","",Roles.ADMIN, mutableListOf())
}

@Entity
open class AdminDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
    override var password: String,
    override val role: Roles,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    override val savedResources: MutableList<SavedResourcesDAO>
) : UserDAO(id, username, password, role, savedResources) {

    constructor(user: UserDTO) : this(user.id,user.username,user.password,Roles.ADMIN, mutableListOf())
    constructor(user: UserPasswordDTO) : this(0,user.username,user.password,Roles.ADMIN, mutableListOf())
    constructor() : this(0,"","",Roles.ADMIN, mutableListOf())
}

@Entity
open class EditorDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
    override var password: String,
    override val role: Roles,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    override val savedResources: MutableList<SavedResourcesDAO>,
    @OneToMany(mappedBy = "editor")
    var exhibitionsList : MutableList<ExhibitionDAO>
) : UserDAO(id, username, password, role,savedResources) {

    constructor(user: UserDTO) : this(user.id,user.username,user.password,Roles.EDITOR,mutableListOf(), mutableListOf())
    constructor(user: UserPasswordDTO) : this(0,user.username,user.password,Roles.EDITOR,mutableListOf(), mutableListOf())
    constructor(user: UserSimpleDTO) : this(0,user.username,"",Roles.EDITOR,mutableListOf(), mutableListOf())
    constructor() : this(0,"","",Roles.EDITOR, mutableListOf(), mutableListOf())
}


@Entity
open class CollaboratorDAO(
    @Id
    @GeneratedValue
    override val id: Long,
    override val username: String,
    override var password: String,
    override val role: Roles,
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL])
    override val savedResources: MutableList<SavedResourcesDAO>,
    @ManyToMany(mappedBy = "collaborators")
    val collaborationList:  MutableList<ExhibitionDAO>
) : UserDAO(id, username, password, role, savedResources) {

    fun removeExhibition(exhibitionDAO: ExhibitionDAO) = {
        if(collaborationList.contains(exhibitionDAO))
            collaborationList.remove(exhibitionDAO)
    }

    constructor(user: UserDTO) : this(user.id,user.username,user.password,Roles.COLLABORATOR, mutableListOf(), mutableListOf())
    constructor(user: UserPasswordDTO) : this(0,user.username,user.password,Roles.COLLABORATOR, mutableListOf(), mutableListOf())
    constructor() : this(0,"","",Roles.COLLABORATOR,  mutableListOf(), mutableListOf())
}
