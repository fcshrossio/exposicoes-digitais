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


    @Operation(summary = "Get All Sub Items ")
    @GetMapping("/subitems")
    fun getAllSubItems() =
        exhibitionSubItemService.getAllSubItems().map { SubItemDTO(it) }



    @Operation(summary = "Create Sub Items")
    @PostMapping("/{itemId}/addsubitem")
    fun createSubItem(@PathVariable itemId: Long, @RequestBody subItemDTO: SubItemDTO) : ExhibitionItemDTO {
        var subitem = exhibitionSubItemService.createSubItem(SubItemDAO(subItemDTO))
        return ExhibitionItemDTO(exhibitionItemService.addSubItem(exhibitionItemService.getOneExhibitionItem(itemId),subitem))
    }

    @Operation(summary = "Remove One Sub Item ")
    @DeleteMapping("/{itemId}/subitem/{subItemId}")
    fun deleteSubTextItem(@PathVariable itemId: Long,@PathVariable subItemId: Long) : ExhibitionItemDTO {
        //exhibitionItemService.removeSubText(exhibitionSubItemService.getOneSubTextItem(subItemId))
        var item : ExhibitionItemDAO = exhibitionItemService.removeSubItem(itemId, exhibitionSubItemService.getOneSubItem(subItemId))
        //exhibitionSubItemService.deleteSubTextItem(subItemId)
        print("item deleted")
        return ExhibitionItemDTO(item)
    }
}