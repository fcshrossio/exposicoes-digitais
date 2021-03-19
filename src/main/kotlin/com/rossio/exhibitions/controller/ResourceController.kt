package com.rossio.exhibitions.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("resource")
class ResourceController {

    @GetMapping("")
    fun getAllresources() {

    }

    @GetMapping("/{id}")
    fun getOneResource() {

    }


    @PostMapping("/{id}")
    fun createResource() {

    }

    @PutMapping("/{id}")
    fun editResource() {

    }

    @DeleteMapping("/{id}")
    fun deleteResource() {

    }
}