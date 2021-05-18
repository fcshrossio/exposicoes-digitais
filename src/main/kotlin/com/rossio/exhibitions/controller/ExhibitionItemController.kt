package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.dto.IntroductionItemDTO
import com.rossio.exhibitions.dto.MapItemDTO
import com.rossio.exhibitions.dto.TextItemDTO
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.exception.WrongTypeException
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.model.MapItemDAO
import com.rossio.exhibitions.model.IntroductionItemDAO
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

    /**
    @Operation(summary = "Create One Exhibition Item ")
    @PostMapping("")
    fun createExhibitionItem(@RequestBody itemDTO: ExhibitionItemDTO) : ExhibitionItemDTO =
        mapItemDAOtoDTO(exhibitionItemService.createOneExhibitionItem(mapItemDTOtoDAO(itemDTO)))

    **/
    @Operation(summary = "Edit One Exhibition Item ")
    @PutMapping("/{id}")
    fun editExhibitionItem(@RequestParam id: Long) : ExhibitionItemDTO =
        IntroductionItemDTO()
    //TODO edit items

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
        var item = exhibitionItemService.getOneExhibitionItem(itemId)
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
        var item = exhibitionItemService.getOneExhibitionItem(itemId)
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
        var item = exhibitionItemService.getOneExhibitionItem(itemId)
        if(item is TextItemDAO)
            exhibitionItemService.addSubText(itemId,SubTextDAO(subTextItemDTO,item))
        else
            WrongTypeException("Item is not a Text type item")
    }

    @Operation(summary = "Remove One Sub Text Item ")
    @DeleteMapping("/subtext/{itemId}")
    fun deleteSubTextItem(@PathVariable itemId: Long) =
        exhibitionItemService.removeSubText(exhibitionSubItemService.getOneSubTextItem(itemId))


    fun mapItemDAOtoDTO(item: ExhibitionItemDAO) : ExhibitionItemDTO =

        when (item) {
            is IntroductionItemDAO -> IntroductionItemDTO(item)
            is TextItemDAO -> TextItemDTO(item)
            is MapItemDAO ->  MapItemDTO(item)
            is AboutItemDAO -> AboutItemDTO(item)
            else -> throw NotFoundException("") //TODO exception
        }


    fun mapItemDTOtoDAO(item: ExhibitionItemDTO,exhibition: ExhibitionDAO) : ExhibitionItemDAO =

        when (item) {
            is IntroductionItemDTO ->  IntroductionItemDAO(item,exhibition)
            is TextItemDTO -> com.rossio.exhibitions.model.TextItemDAO(item, exhibition)
            is MapItemDTO ->  MapItemDAO(item,exhibition)
            is AboutItemDTO ->  AboutItemDAO(item,exhibition)
            else -> throw NotFoundException("") //TODO exception
        }

}