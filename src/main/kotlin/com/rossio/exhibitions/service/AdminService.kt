package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.UserDAO
import com.rossio.exhibitions.model.UserRepository
import org.springframework.stereotype.Service

@Service
class AdminService(
    val userRepository: UserRepository
) {

    fun getAllAdmins() : List<UserDAO> =
        userRepository.findAll()

    fun getOneAdmin(id: Long) =
        userRepository.findById(id).orElseThrow { NotFoundException("No Editor with ID: $id found") }

    fun addOneAdmin(editorDAO: UserDAO) =
        userRepository.save(editorDAO)

    fun deleteAdmin(id:Long) =
        userRepository.delete(getOneAdmin(id))

}