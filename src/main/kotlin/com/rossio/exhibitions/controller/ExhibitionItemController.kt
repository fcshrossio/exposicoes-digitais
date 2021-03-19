package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.ExhibitionItemDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("item")
class ExhibitionItemController {

    @Operation(summary = "Get List of All Exhibition Items ")
    @GetMapping("")
    fun getAllExhibitionItems() : List<ExhibitionDTO> = emptyList()

    @Operation(summary = "Get One Exhibition Item ")
    @GetMapping("/{id}")
    fun getOneExhibitionItem() : ExhibitionItemDTO = ExhibitionItemDTO()

    @Operation(summary = "Create One Exhibition Item ")
    @PostMapping("")
    fun createExhibitionItem() : ExhibitionItemDTO = ExhibitionItemDTO(0,"",0)

    @Operation(summary = "Edit One Exhibition Item ")
    @PutMapping("/{id}")
    fun editExhibitionItem() : ExhibitionItemDTO = ExhibitionItemDTO(0,"",0)

    @Operation(summary = "Remove One Exhibition Item ")
    @DeleteMapping("/{id}")
    fun deleteExhibitionItem() : ExhibitionItemDTO = ExhibitionItemDTO(0,"",0)



}