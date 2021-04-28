package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.DigitalResourceDAO
import com.rossio.exhibitions.model.UserDAO
import java.util.*







data class UserDTO(
    val id: Long,
    val username: String
){

    constructor() : this(0,"")

    constructor(user: UserDAO) : this(user.id,user.username)
}

data class AdminDTO(
    val id: Long,
    val username: String
){

    constructor() : this(0,"")

    constructor(user: UserDAO) : this(user.id,user.username)
}


data class EditorDTO(
    val id: Long,
    val username: String
){

    constructor() : this(0,"")

    constructor(user: UserDAO) : this(user.id,user.username)
}



data class DigitalResourceDTO(
    val id:Long,
    val name: String
){

    constructor() : this(0,"")

    constructor(resource: DigitalResourceDAO) : this(resource.id,resource.name)
}