package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.exception.WrongTypeException
import com.rossio.exhibitions.mapItemDTOtoDAO
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.model.MapItemDAO
import com.rossio.exhibitions.model.AboutItemDAO


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
        exhibitionItemService.getAllExhibitionItems().map { mapItemDAOtoDTO(it) }

    @Operation(summary = "Get One Exhibition Item ")
    @GetMapping("/{id}")
    fun getOneExhibitionItem(@PathVariable id:Long ) : ExhibitionItemDTO =
        mapItemDAOtoDTO(exhibitionItemService.getOneExhibitionItem(id))

    @Operation(summary = "Edit One Exhibition Item ")
    @PutMapping("/{id}")
    fun editExhibitionItem(@PathVariable id: Long, @RequestBody item: ExhibitionItemDTO) : ExhibitionItemDTO =
        mapItemDAOtoDTO(
            exhibitionItemService.editOneExhibitionItem(
                exhibitionItemService.getOneExhibitionItem(id),
                mapItemDTOtoDAO(item,exhibitionService.getOneExhibition(item.exhibitionId))
            )
        )


    @Operation(summary = "Post One Exhibition Item ")
    @PostMapping("")
    fun addExhibitionItem(@RequestBody item: ExhibitionItemDTO) =
        mapItemDAOtoDTO(exhibitionItemService.createOneExhibitionItem(mapItemDTOtoDAO(item,exhibitionService.getOneExhibition(item.exhibitionId))))


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
        if(item is MapItemDAO)
            exhibitionItemService.addMarker(itemId, MarkerDAO(markerDTO,item))
        else
            WrongTypeException("Item is not a Map type item")
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
        if(item is AboutItemDAO)
            exhibitionItemService.addSubAbout(itemId, SubAboutDAO(subAboutItemDTO,item))
        else
            WrongTypeException("Item is not an About type item")
    }

    @Operation(summary = "Remove One Sub About Item ")
    @DeleteMapping("/subabout/{id}")
    fun deleteSubAboutItem(@PathVariable id: Long) =
        exhibitionItemService.removeSubAbout(exhibitionSubItemService.getOneSubAboutItem(id))

    @Operation(summary = "Get All Sub Text Items ")
    @GetMapping("/subtext")
    fun getAllSubTextItems() =
        exhibitionSubItemService.getAllSubTextItems().map { SubTextItemDTO(it) }



    @Operation(summary = "Create Sub Text Items")
    @PostMapping("/{itemId}/addsubtext")
    fun createSubText(@PathVariable itemId: Long, @RequestBody subTextItemDTO: SubTextItemDTO) {
        val item = exhibitionItemService.getOneExhibitionItem(itemId)
        if(item is TextItemDAO)
            exhibitionItemService.addSubText(itemId,SubTextDAO(subTextItemDTO,item))
        else
            WrongTypeException("Item is not a Text type item")
    }

    @Operation(summary = "Remove One Sub Text Item ")
    @DeleteMapping("/subtext/{itemId}")
    fun deleteSubTextItem(@PathVariable itemId: Long) =
        exhibitionItemService.removeSubText(exhibitionSubItemService.getOneSubTextItem(itemId))


}