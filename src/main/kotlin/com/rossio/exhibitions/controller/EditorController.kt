package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("editor")
class EditorController {

    @Operation(summary = "Get List of All Editors ")
    @GetMapping("")
    fun getAllEditors() : List<UserDTO> = emptyList()

    @Operation(summary = "Get One Editor by id ")
    @GetMapping("/{id}")
    fun getOneEditor() : UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Add one Editor ")
    @PostMapping("")
    fun createEditor() : UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Edit a Editor ")
    @PutMapping("/{id}")
    fun editEditor() : UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Delete a Editor")
    @DeleteMapping("/{id}")
    fun deleteEditor() : UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Get List of All Editor Exhibitions ")
    @GetMapping("/{id}/exhibitions")
    fun getCollaboratorExhibitions() : ExhibitionDTO = ExhibitionDTO()
}