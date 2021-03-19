package com.rossio.exhibitions.controller

import com.rossio.exhibitions.dto.UserDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController {

    @Operation(summary = "Get List of Admins")
    @GetMapping("")
    fun getAllAdmins() : List<UserDTO> = emptyList()
}