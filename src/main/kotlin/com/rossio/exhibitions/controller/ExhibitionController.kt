package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.*
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("exhibition")
class ExhibitionController(
    val exhibitionService: ExhibitionService,
    val digitalResourceService: DigitalResourceService,
    val editorService: EditorService,
    val collaboratorService : CollaboratorService,
    val exhibitionItemService: ExhibitionItemService,
    val markerService: MarkerService,
    val exhibitionSubItemService: ExhibitionSubItemService
) {

    //@CrossOrigin
    @Operation(summary = "Get List of All Exhibitions ")
    @GetMapping("")
   // @PreAuthorize("hasRole('admin')")
    fun getAllExhibitions() : List<ExhibitionDTO> =
        exhibitionService.getAllExhibitions().map { ExhibitionDTO(it) }

    @Operation(summary = "Get List of All published Exhibitions ")
    @GetMapping("/published")
    fun getAllPublishedExhibitions() : List<ExhibitionDTO> =
        exhibitionService.getAllPublicExhibitions().map { ExhibitionDTO(it) }


    @Operation(summary = "Get a single Exhibition ")
    @GetMapping("/{id}")
    fun getOneExhibition(@PathVariable id: Long) : ExhibitionDTO = ExhibitionDTO(exhibitionService.getOneExhibition(id))


    @Operation(summary = "Create a new Exhibition ")
    @PostMapping("")
    //@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
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
    @PostMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN','EDITOR') and @securityService.canEditorEditExhibition(principal, #id)")
    fun editExhibition(@RequestBody exhibition: ExhibitionDTO, @PathVariable id: Long) : ExhibitionDTO {
        var exhibitionDAO: ExhibitionDAO = exhibitionService.getOneExhibition(id)
        exhibitionDAO = exhibitionService.editExhibitionDetails(exhibition, exhibitionDAO)
        exhibition.items.forEach{ item ->
            exhibitionItemService.editOneExhibitionItem(exhibitionItemService.getOneExhibitionItem(item.id),
                ExhibitionItemDAO(item,exhibitionDAO)
            )
            item.subItems.forEach{
                exhibitionSubItemService.editSubItem(exhibitionSubItemService.getOneSubItem(it.id),
                SubItemDAO(it,exhibitionItemService.getOneExhibitionItem(item.id))
                )
            }
        }
        exhibition.markers.forEach{
                markerService.editOneMarker(markerService.getOneMarker(it.id), MarkerDAO(it, exhibitionDAO))
        }
        return ExhibitionDTO(exhibitionService.getOneExhibition(id))
    }

    @Operation(summary = "Delete a Exhibition")
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    fun deleteExhibition(@PathVariable id: Long) {
        var exhibition = exhibitionService.getOneExhibition(id)
        exhibitionService.deleteExhibition(exhibition)

    }


    @Operation(summary = "Add a Collaborator to Exhibition ")
    @PostMapping("/{id}/collaborator")
    @PreAuthorize("hasRole('ADMIN')")
    fun addCollaboratorExhibition(@PathVariable id: Long,@RequestBody collaborator: UserDTO) =
        exhibitionService.addCollaborator(exhibitionService.getOneExhibition(id), collaboratorService.getOneCollaborator(collaborator.id))

    @Operation(summary = "Remove a Collaborator to Exhibition ")
    @DeleteMapping("/{id}/collaborator")
    @PreAuthorize("hasRole('ADMIN')")
    fun removeCollaboratorExhibition(@PathVariable id: Long,@RequestBody collaborator: UserDTO) =
        exhibitionService.removeCollaborator(exhibitionService.getOneExhibition(id), collaboratorService.getOneCollaborator(collaborator.id))

    @Operation(summary = "Add Exhibition Item to Exhibition ")
    @PostMapping("/additem/{exhibitionId}")
    //@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
    fun addItemExhibition(@PathVariable exhibitionId: Long,@RequestBody item : ExhibitionItemDTO) : ExhibitionItemDTO =
       ExhibitionItemDTO(exhibitionItemService.createOneExhibitionItem(ExhibitionItemDAO(item,exhibitionService.getOneExhibition(exhibitionId))))


    @Operation(summary = "Show Recent Exhibitions")
    @GetMapping("/recent")
    fun recentExhibitions() : List<ExhibitionDTO> =
        exhibitionService.recentExhibitions().map { ExhibitionDTO(it) }

    @Operation(summary = "Add Keyword to Exhibition")
    @GetMapping("/{id}/keywords")
    @PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
    fun addKeyword(@PathVariable id: Long,@RequestParam value : Keywords) =
        exhibitionService.addKeyword(exhibitionService.getOneExhibition(id), value)
        //TODO KEYWORD ROUTE FUNCTION

    @Operation(summary = "Remove Keyword to Exhibition")
    @DeleteMapping("/{id}/keywords")
    @PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
    fun removeKeyword(@PathVariable id: Long,@RequestParam value : Keywords) =
        exhibitionService.removeKeyword(exhibitionService.getOneExhibition(id), value)
    //TODO KEYWORD ROUTE FUNCTION

    @Operation(summary = "Change Status")
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
    fun changeStatus(@PathVariable id: Long,@RequestParam value : Status) =
        exhibitionService.changeStatus(exhibitionService.getOneExhibition(id), value)


    @Operation(summary = "Change Credits")
    @PostMapping("/{id}/credits")
    //@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
    fun editCredits(@PathVariable id: Long,@RequestBody creditsDTO: CreditsDTO) : ExhibitionDTO =
        ExhibitionDTO(exhibitionService.editExhibitionCredits(creditsDTO,id))

    @Operation(summary = "Change Auxiliary Materials")
    @PutMapping("/{id}/materials")
    //@PreAuthorize("hasAnyRole('ADMIN','EDITOR')")
    fun editAuxiliaryMaterials(@PathVariable id: Long,@RequestParam value : String) =
        exhibitionService.editExhibitionAuxiliaryMaterials(value,value,value,value,exhibitionService.getOneExhibition(id))

    @Operation(summary = "Get All Map Marker ")
    @GetMapping("/markers")
    fun getAllMarkers() =
        markerService.getAllMarkers().map { MarkerDTO(it) }

    @Operation(summary = "Create Map Marker ")
    @PostMapping("/{exhibitionId}/addmarker")
    fun createMarker(@PathVariable exhibitionId: Long, @RequestBody markerDTO: MarkerDTO) : MarkerDTO =
         MarkerDTO(markerService.createOneMarker(MarkerDAO(markerDTO,exhibitionService.getOneExhibition(exhibitionId))))

    @Operation(summary = "Delete Marker ")
    @DeleteMapping("{exhibitionId}/marker/{itemId}")
    fun deleteMarker(@PathVariable exhibitionId: Long, @PathVariable itemId: Long) : ExhibitionDTO {

       var exhibition : ExhibitionDAO = exhibitionService.removeExhibitionMarker(exhibitionId,markerService.getOneMarker(itemId))
        return ExhibitionDTO(exhibitionService.getOneExhibition(exhibitionId))
    }



}