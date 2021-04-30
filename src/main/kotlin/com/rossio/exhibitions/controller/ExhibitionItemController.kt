package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.dto.IntroductionItemDTO
import com.rossio.exhibitions.dto.MapItemDTO
import com.rossio.exhibitions.dto.TextItemDTO
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.model.MapItemDAO
import com.rossio.exhibitions.model.IntroductionItemDAO
import com.rossio.exhibitions.model.AboutItemDAO


import com.rossio.exhibitions.service.ExhibitionItemService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("item")
class ExhibitionItemController(
    val exhibitionItemService: ExhibitionItemService
) {

    @Operation(summary = "Get List of All Exhibition Items ")
    @GetMapping("")
    fun getAllExhibitionItems() : List<ExhibitionItemDTO> =
        exhibitionItemService.getAllExhibitionItems().map { mapItemDAOtoDTO(it) }

    @Operation(summary = "Get One Exhibition Item ")
    @GetMapping("/{id}")
    fun getOneExhibitionItem(@PathVariable id:Long ) : ExhibitionItemDTO =
        mapItemDAOtoDTO(exhibitionItemService.getOneExhibitionItem(id))

    @Operation(summary = "Create One Exhibition Item ")
    @PostMapping("")
    fun createExhibitionItem(@RequestBody itemDTO: ExhibitionItemDTO) : ExhibitionItemDTO = mapItemDAOtoDTO(exhibitionItemService.createOneExhibitionItem(mapItemDTOtoDAO(itemDTO)))

    @Operation(summary = "Edit One Exhibition Item ")
    @PutMapping("/{id}")
    fun editExhibitionItem(@RequestParam id: Long) : ExhibitionItemDTO =
        IntroductionItemDTO()
    //TODO edit items

    @Operation(summary = "Remove One Exhibition Item ")
    @DeleteMapping("/{id}")
    fun deleteExhibitionItem(@PathVariable id: Long) = exhibitionItemService.deleteOneExhibitionItem(id)


    fun mapItemDAOtoDTO(item: ExhibitionItemDAO) : ExhibitionItemDTO =

        when (item) {
            is IntroductionItemDAO -> IntroductionItemDTO(item)
            is TextItemDAO -> TextItemDTO(item)
            is MapItemDAO ->  MapItemDTO(item)
            is AboutItemDAO -> com.rossio.exhibitions.dto.AboutItemDTO(item)
            else -> throw NotFoundException("") //TODO exception
        }


    fun mapItemDTOtoDAO(item: ExhibitionItemDTO) : ExhibitionItemDAO =

        when (item) {
            is IntroductionItemDTO -> com.rossio.exhibitions.model.IntroductionItemDAO(item)
            is TextItemDTO -> com.rossio.exhibitions.model.TextItemDAO(item)
            is MapItemDTO -> com.rossio.exhibitions.model.MapItemDAO(item)
            is AboutItemDTO ->  AboutItemDAO(item)
            else -> throw NotFoundException("") //TODO exception
        }


}