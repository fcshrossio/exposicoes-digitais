package com.rossio.exhibitions.dto

import com.rossio.exhibitions.model.UserDAO

data class UserDTO(
    val id: Long,
    val username: String,
    val password: String
){
    constructor(user: UserDAO) : this(user.id,user.username,user.password)
}

data class UserPasswordDTO (
    val username: String,
    val password: String
)



data class AdminDTO(
    val id: Long,
    val username: String
){

    constructor(user: UserDAO) : this(user.id,user.username)
}


data class EditorDTO(
    val id: Long,
    val username: String
){

    constructor(user: UserDAO) : this(user.id,user.username)
}

