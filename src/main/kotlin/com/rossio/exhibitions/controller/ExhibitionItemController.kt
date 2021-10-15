package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.model.*


import com.rossio.exhibitions.service.ExhibitionItemService
import com.rossio.exhibitions.service.ExhibitionService
import com.rossio.exhibitions.service.ExhibitionSubItemService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("item")
class ExhibitionItemController(
    val exhibitionItemService: ExhibitionItemService,
    val exhibitionService: ExhibitionService,
    val exhibitionSubItemService: ExhibitionSubItemService
) {

    @Operation(summary = "Get List of All Exhibition Items ")
    @GetMapping("")
    fun getAllExhibitionItems() : List<ExhibitionItemDTO> =
        exhibitionItemService.getAllExhibitionItems().map { ExhibitionItemDTO(it) }

    @Operation(summary = "Get One Exhibition Item ")
    @GetMapping("/{id}")
    fun getOneExhibitionItem(@PathVariable id:Long ) : ExhibitionItemDTO =
        ExhibitionItemDTO(exhibitionItemService.getOneExhibitionItem(id))

    @Operation(summary = "Edit One Exhibition Item ")
    @PutMapping("/{id}")
    fun editExhibitionItem(@PathVariable id: Long, @RequestBody item: ExhibitionItemDTO) : ExhibitionItemDTO =
        ExhibitionItemDTO(
            exhibitionItemService.editOneExhibitionItem(
                exhibitionItemService.getOneExhibitionItem(id),
                ExhibitionItemDAO(item)
            )
        )


    @Operation(summary = "Post One Exhibition Item ")
    @PostMapping("")
    fun addExhibitionItem(@RequestBody item: ExhibitionItemDTO) =
        ExhibitionItemDTO(exhibitionItemService.createOneExhibitionItem(ExhibitionItemDAO(item)))


    @Operation(summary = "Remove One Exhibition Item ")
    @DeleteMapping("/{id}")
    fun deleteExhibitionItem(@PathVariable id: Long) = exhibitionItemService.deleteOneExhibitionItem(id)

    @Operation(summary = "Get All Map Marker ")
    @GetMapping("/markers")
    fun getAllMarkers() =
        exhibitionSubItemService.getAllMarkers().map { MarkerDTO(it) }

    @Operation(summary = "Create Map Marker ")
    @PostMapping("/{itemId}/addmarker")
    fun createMarker(@PathVariable itemId: Long, @RequestBody markerDTO: MarkerDTO){
        val item = exhibitionItemService.getOneExhibitionItem(itemId)
            exhibitionItemService.addMarker(itemId, MarkerDAO(markerDTO))
    }

    @Operation(summary = "Delete Marker ")
    @DeleteMapping("/marker/{itemId}")
    fun deleteMarker(@PathVariable itemId: Long) =
        exhibitionItemService.removeMarker(exhibitionSubItemService.getOneMarker(itemId))


    @Operation(summary = "Get All Sub About Items ")
    @GetMapping("/subabout")
    fun getAllSubAbouts() =
        exhibitionSubItemService.getAllSubAboutItems().map { SubAboutItemDTO(it) }


    @Operation(summary = "Create Sub About Items")
    @PostMapping("/{itemId}/addsubabout")
    fun createSubAbout(@PathVariable itemId: Long, @RequestBody subAboutItemDTO: SubAboutItemDTO){
        val item = exhibitionItemService.getOneExhibitionItem(itemId)
            exhibitionItemService.addSubAbout(itemId, SubAboutDAO(subAboutItemDTO))
    }

    @Operation(summary = "Remove One Sub About Item ")
    @DeleteMapping("/subabout/{id}")
    fun deleteSubAboutItem(@PathVariable id: Long) =
        exhibitionItemService.removeSubAbout(exhibitionSubItemService.getOneSubAboutItem(id))

    @Operation(summary = "Get All Sub Items ")
    @GetMapping("/subitems")
    fun getAllSubItems() =
        exhibitionSubItemService.getAllSubTextItems().map { SubItemDTO(it) }



    @Operation(summary = "Create Sub Items")
    @PostMapping("/{itemId}/addsubitem")
    fun createSubItem(@PathVariable itemId: Long, @RequestBody subItemDTO: SubItemDTO) {
        val item = exhibitionItemService.getOneExhibitionItem(itemId)
        var subitem = exhibitionSubItemService.createSubItem(SubItemDAO(subItemDTO))
        exhibitionItemService.addSubItem(item,subitem)
    }

    @Operation(summary = "Remove One Sub Item ")
    @DeleteMapping("/subitem/{itemId}")
    fun deleteSubTextItem(@PathVariable itemId: Long) =
        exhibitionItemService.removeSubText(exhibitionSubItemService.getOneSubTextItem(itemId))


}