package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.model.AdminDAO
import com.rossio.exhibitions.model.UserDAO
import com.rossio.exhibitions.service.AdminService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin")
class AdminController(
    val adminService: AdminService
) {

    @Operation(summary = "Get List of Admins")
    @GetMapping("")
    fun getAllAdmins() : List<UserDTO> = adminService.getAllAdmins().map { UserDTO(it) }

    @Operation(summary = "Get One Collaborator")
    @GetMapping("/{id}")
    fun getOneAdmin(@PathVariable id:Long) : UserDTO = UserDTO(adminService.getOneAdmin(id))

    @PostMapping("Add one Collaborator")
    fun createAdmin(@RequestBody userDTO: UserDTO) :  UserDTO =
        UserDTO(adminService.addOneAdmin(AdminDAO(userDTO)))

    @Operation(summary = "Edit Collaborator")
    @PutMapping("/{id}")
    fun editAdmin() :  UserDTO = UserDTO(0,"USERNAME","password")

    @Operation(summary = "Delete Collaborator")
    @DeleteMapping("/{id}")
    fun deleteAdmin(@PathVariable id:Long) :  UserDTO = UserDTO(adminService.getOneAdmin(id))
}