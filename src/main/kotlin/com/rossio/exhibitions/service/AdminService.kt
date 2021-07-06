package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.AdminDAO
import com.rossio.exhibitions.model.AdminRepository
import com.rossio.exhibitions.model.UserDAO
import com.rossio.exhibitions.model.UserRepository
import org.springframework.stereotype.Service

@Service
class AdminService(
    val adminRepository: AdminRepository
) {

    fun getAllAdmins() : List<AdminDAO> =
        adminRepository.findAll()

    fun getOneAdmin(id: Long) =
        adminRepository.findById(id).orElseThrow { NotFoundException("No Editor with ID: $id found") }

    fun addOneAdmin(adminDAO: AdminDAO) =
        adminRepository.save(adminDAO)

    fun deleteAdmin(id:Long) =
        adminRepository.delete(getOneAdmin(id))

}