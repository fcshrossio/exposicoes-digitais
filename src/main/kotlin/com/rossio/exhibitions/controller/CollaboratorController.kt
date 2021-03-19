package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("collaborator")
class CollaboratorController {

    @Operation(summary = "Get List of All Collaborators ")
    @GetMapping("")
    fun getAllCollaborators() : List<UserDTO> = emptyList()

    @Operation(summary = "Get One Collaborator")
    @GetMapping("/{id}")
    fun getOneCollaborator() : UserDTO = UserDTO(0,"USERNAME")

    @PostMapping("Add one Collaborator")
    fun createCollaborator() :  UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Edit Collaborator")
    @PutMapping("/{id}")
    fun editCollaborator() :  UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Delete Collaborator")
    @DeleteMapping("/{id}")
    fun deleteCollaborator() :  UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Get Collaborator Exhibitions")
    @GetMapping("/{id}/exhibitions")
    fun getCollaboratorExhibitions() : ExhibitionDTO = ExhibitionDTO()
}