package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.model.DigitalResourceDAO
import com.rossio.exhibitions.service.DigitalResourceService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("resource")
class ResourceController(
    val digitalResourceService: DigitalResourceService
) {

    @GetMapping("")
    fun getAllresources() : List<DigitalResourceDTO> =
        digitalResourceService.getAllDigitalResources().map { DigitalResourceDTO(it) }

    @GetMapping("/{id}")
    fun getOneResource(@PathVariable id:Long) =
        DigitalResourceDTO(digitalResourceService.getOneDigitalResource(id))


    @PostMapping("")
    fun createResource(@RequestBody resourceDTO: DigitalResourceDTO) =
        digitalResourceService.addOneDigitalResource(DigitalResourceDAO(resourceDTO))

    @PutMapping("/{id}")
    fun editResource() {
            //TODO digital resource edit
    }

    @DeleteMapping("/{id}")
    fun deleteResource(@PathVariable id:Long) =
        digitalResourceService.removeDigitalResource(id)
}