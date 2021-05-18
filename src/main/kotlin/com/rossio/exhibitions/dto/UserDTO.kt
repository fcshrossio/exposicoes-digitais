package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.UserDAO

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

