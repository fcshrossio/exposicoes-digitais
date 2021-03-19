package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("exhibition")
class ExhibitionController {


    @Operation(summary = "Get List of All Exhibitions ")
    @GetMapping("")
    fun getAllExhibitions() : List<ExhibitionDTO> = emptyList()

    @Operation(summary = "Get a single Exhibition ")
    @GetMapping("/{id}")
    fun getOneExhibition() : ExhibitionDTO = ExhibitionDTO()

    @Operation(summary = "Create a new Exhibition ")
    @PostMapping("")
    fun createExhibition() : ExhibitionDTO = ExhibitionDTO()

    @Operation(summary = "Edit a Exhibition")
    @PutMapping("/{id}")
    fun editExhibition() : ExhibitionDTO = ExhibitionDTO()

    @Operation(summary = "Delete a Exhibition")
    @DeleteMapping("/{id}")
    fun deleteExhibition() : ExhibitionDTO = ExhibitionDTO()

    @Operation(summary = "Add a Collaborator to Exhibition ")
    @PostMapping("/collaborator")
    fun addCollaboratorExhibition() : UserDTO = UserDTO(0,"user")

    @Operation(summary = "Remove a Collaborator to Exhibition ")
    @DeleteMapping("/collaborator")
    fun removeCollaboratorExhibition() : UserDTO = UserDTO(0,"user")



}