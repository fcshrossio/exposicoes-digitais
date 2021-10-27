package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.SavedResourcesDTO
import com.rossio.exhibitions.service.SavedResourcesService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("savedresources")
class SavedResourcesController(
    val savedResourcesService: SavedResourcesService
) {
    @Operation(summary = "Get List of All Exhibitions ")
    @GetMapping("")

    fun getAllSavedResources() : List<SavedResourcesDTO> =
        savedResourcesService.getSavedResources().map { SavedResourcesDTO(it) }
}