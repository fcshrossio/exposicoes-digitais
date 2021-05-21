package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.DigitalResourceService
import com.rossio.exhibitions.service.EditorService
import com.rossio.exhibitions.service.ExhibitionItemService
import com.rossio.exhibitions.service.ExhibitionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("exhibition")
class ExhibitionController(
    val exhibitionService: ExhibitionService,
    val digitalResourceService: DigitalResourceService,
    val editorService: EditorService,
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
    fun createExhibition(@RequestBody exhibition: ExhibitionDTO) : ExhibitionDTO =
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
    fun editExhibition() : ExhibitionDTO = ExhibitionDTO()

    @Operation(summary = "Delete a Exhibition")
    @DeleteMapping("/{id}")
    fun deleteExhibition(@PathVariable id: Long) =
        exhibitionService.deleteExhibition(id)

    @Operation(summary = "Add a Collaborator to Exhibition ")
    @PostMapping("/{id}/collaborator")
    fun addCollaboratorExhibition(@PathVariable id: Long,@RequestBody collaborator: UserDTO) =
        exhibitionService.addCollaborator(id, CollaboratorDAO(collaborator))

    @Operation(summary = "Remove a Collaborator to Exhibition ")
    @DeleteMapping("/{id}/collaborator")
    fun removeCollaboratorExhibition(@PathVariable id: Long,@RequestBody collaborator: UserDTO) =
        exhibitionService.removeCollaborator(id, CollaboratorDAO(collaborator))

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
        exhibitionService.addKeyword(id, value)
        //TODO KEYWORD ROUTE FUNCTION

    @Operation(summary = "Remove Keyword to Exhibition")
    @DeleteMapping("/{id}/keywords")
    fun removeKeyword(@PathVariable id: Long,@RequestParam value : Keywords) =
        exhibitionService.removeKeyword(id, value)
    //TODO KEYWORD ROUTE FUNCTION

    @Operation(summary = "Change Status")
    @PutMapping("/{id}/status")
    fun changeStatus(@PathVariable id: Long,@RequestParam value : Status) =
        exhibitionService.changeStatus(id, value)

    fun mapItemDTOtoDAO(item: ExhibitionItemDTO,exhibition: ExhibitionDAO) : ExhibitionItemDAO =

        when (item) {
            is IntroductionItemDTO ->  IntroductionItemDAO(item,exhibition)
            is TextItemDTO ->  TextItemDAO(item,exhibition)
            is MapItemDTO ->  MapItemDAO(item,exhibition)
            is AboutItemDTO ->  AboutItemDAO(item,exhibition)
            else -> throw NotFoundException("") //TODO exception
        }

    fun mapItemDAOtoDTO(item: ExhibitionItemDAO) : ExhibitionItemDTO =

        when (item) {
            is IntroductionItemDAO -> IntroductionItemDTO(item)
            is TextItemDAO -> TextItemDTO(item)
            is MapItemDAO ->  MapItemDTO(item)
            is AboutItemDAO -> AboutItemDTO(item)
            else -> throw NotFoundException("") //TODO exception
        }


}