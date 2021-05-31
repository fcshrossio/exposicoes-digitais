package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.mapItemDTOtoDAO
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.*
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("exhibition")
class ExhibitionController(
    val exhibitionService: ExhibitionService,
    val digitalResourceService: DigitalResourceService,
    val editorService: EditorService,
    val collaboratorService : CollaboratorService,
    val exhibitionItemService: ExhibitionItemService
) {


    @Operation(summary = "Get List of All Exhibitions ")
    @GetMapping("")
    fun getAllExhibitions() : List<ExhibitionDTO> =
        exhibitionService.getAllExhibitions().map { ExhibitionDTO(it) }

    @Operation(summary = "Get a single Exhibition ")
    @GetMapping("/{id}")
    fun getOneExhibition(@PathVariable id: Long) : ExhibitionDTO = ExhibitionDTO(exhibitionService.getOneExhibition(id))


    @Operation(summary = "Create a new Exhibition ")
    @PostMapping("")
    fun createExhibition(@RequestBody exhibition: ExhibitionDTO)  =
        ExhibitionDTO(
            exhibitionService.createExhibition(
                ExhibitionDAO(
                    exhibition,
                    editorService.getOneEditor(exhibition.editor.id),
                    digitalResourceService.getOneDigitalResource(exhibition.cover.id)
                    )
            )
        )



    @Operation(summary = "Edit a Exhibition")
    @PutMapping("/{id}")
    fun editExhibition(@RequestBody exhibitionDetails: ExhibitionDetailsDTO, @PathVariable id: Long) : ExhibitionDTO =
        ExhibitionDTO(exhibitionService.editExhibitionDetails(exhibitionDetails, exhibitionService.getOneExhibition(id)))

    //TODO EDIT EXHIBITION

    @Operation(summary = "Delete a Exhibition")
    @DeleteMapping("/{id}")
    fun deleteExhibition(@PathVariable id: Long) =
        exhibitionService.deleteExhibition(exhibitionService.getOneExhibition(id))

    @Operation(summary = "Add a Collaborator to Exhibition ")
    @PostMapping("/{id}/collaborator")
    fun addCollaboratorExhibition(@PathVariable id: Long,@RequestBody collaborator: UserDTO) =
        exhibitionService.addCollaborator(exhibitionService.getOneExhibition(id), collaboratorService.getOneCollaborator(collaborator.id))

    @Operation(summary = "Remove a Collaborator to Exhibition ")
    @DeleteMapping("/{id}/collaborator")
    fun removeCollaboratorExhibition(@PathVariable id: Long,@RequestBody collaborator: UserDTO) =
        exhibitionService.removeCollaborator(exhibitionService.getOneExhibition(id), CollaboratorDAO(collaborator))

    @Operation(summary = "Add Exhibition Item to Exhibition ")
    @PostMapping("/{id}/additem")
    fun addItemExhibition(@PathVariable id: Long,@RequestBody item : ExhibitionItemDTO) : ExhibitionItemDTO =
        mapItemDAOtoDTO(exhibitionItemService.createOneExhibitionItem(mapItemDTOtoDAO(item,exhibitionService.getOneExhibition(id))))

    @Operation(summary = "Show Recent Exhibitions")
    @GetMapping("/recent")
    fun recentExhibitions() : List<ExhibitionDTO> =
        exhibitionService.recentExhibitions().map { ExhibitionDTO(it) }

    @Operation(summary = "Add Keyword to Exhibition")
    @GetMapping("/{id}/keywords")
    fun addKeyword(@PathVariable id: Long,@RequestParam value : Keywords) =
        exhibitionService.addKeyword(exhibitionService.getOneExhibition(id), value)
        //TODO KEYWORD ROUTE FUNCTION

    @Operation(summary = "Remove Keyword to Exhibition")
    @DeleteMapping("/{id}/keywords")
    fun removeKeyword(@PathVariable id: Long,@RequestParam value : Keywords) =
        exhibitionService.removeKeyword(exhibitionService.getOneExhibition(id), value)
    //TODO KEYWORD ROUTE FUNCTION

    @Operation(summary = "Change Status")
    @PutMapping("/{id}/status")
    fun changeStatus(@PathVariable id: Long,@RequestParam value : Status) =
        exhibitionService.changeStatus(exhibitionService.getOneExhibition(id), value)




}