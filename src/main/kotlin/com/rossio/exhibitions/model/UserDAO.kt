package com.rossio.exhibitions.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class UserDAO(
    @Id
    @GeneratedValue
    val id: Long,
    val username: String
) {
    constructor() : this(0,"")
}