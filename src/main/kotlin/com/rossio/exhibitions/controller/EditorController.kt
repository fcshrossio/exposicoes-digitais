package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.model.EditorDAO
import com.rossio.exhibitions.model.UserDAO
import com.rossio.exhibitions.service.EditorService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("editor")
class EditorController(
    val editorService: EditorService
) {

    @Operation(summary = "Get List of All Editors ")
    @GetMapping("")
    fun getAllEditors() : List<UserDTO> = editorService.getAllEditors().map { UserDTO(it) }

    @Operation(summary = "Get One Editor by id ")
    @GetMapping("/{id}")
    fun getOneEditor(@PathVariable id:Long) : UserDTO = UserDTO(editorService.getOneEditor(id))

    @Operation(summary = "Add one Editor ")
    @PostMapping("")
    fun createEditor(@RequestBody userDTO: UserDTO) : UserDTO = UserDTO(editorService.addOneEditor(EditorDAO(userDTO)))

    @Operation(summary = "Edit a Editor ")
    @PutMapping("/{id}")
    fun editEditor(@PathVariable id:Long) : UserDTO = UserDTO(0,"USERNAME")

    @Operation(summary = "Delete a Editor")
    @DeleteMapping("/{id}")
    fun deleteEditor(@PathVariable id:Long) = editorService.deleteEditor(id)

    @Operation(summary = "Get List of All Editor Exhibitions ")
    @GetMapping("/{id}/exhibitions")
    fun getCollaboratorExhibitions(@PathVariable id:Long) : ExhibitionDTO = ExhibitionDTO()
}