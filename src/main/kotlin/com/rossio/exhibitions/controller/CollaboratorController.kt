package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.model.CollaboratorDAO
import com.rossio.exhibitions.model.UserDAO
import com.rossio.exhibitions.service.CollaboratorService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("collaborator")
class CollaboratorController(
    val collaboratorService: CollaboratorService
) {

    @Operation(summary = "Get List of All Collaborators ")
    @GetMapping("")
    fun getAllCollaborators() : List<UserDTO> = collaboratorService.getAllCollaborators().map { UserDTO(it) }

    @Operation(summary = "Get One Collaborator")
    @GetMapping("/{id}")
    fun getOneCollaborator(@PathVariable id:Long) : UserDTO = UserDTO(collaboratorService.getOneCollaborator(id))

    @PostMapping("Add one Collaborator")
    fun createCollaborator(@RequestBody userDTO: UserDTO) :  UserDTO =
        UserDTO(collaboratorService.addOneCollaborator(CollaboratorDAO(userDTO)))

    @Operation(summary = "Edit Collaborator")
    @PutMapping("/{id}")
    fun editCollaborator() :  UserDTO = UserDTO(0,"USERNAME","password")

    @Operation(summary = "Delete Collaborator")
    @DeleteMapping("/{id}")
    fun deleteCollaborator(@PathVariable id:Long) :  UserDTO = UserDTO(collaboratorService.getOneCollaborator(id))

    @Operation(summary = "Get Collaborator Exhibitions")
    @GetMapping("/{id}/exhibitions")
    fun getCollaboratorExhibitions(@PathVariable id:Long) : List<ExhibitionDTO> =
        collaboratorService.getOneCollaborator(id).collaborationList.map { ExhibitionDTO(it) }
}