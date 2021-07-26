package com.rossio.exhibitions.service

import com.rossio.exhibitions.model.ExhibitionsRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class SecurityService(
    var exhibitionsRepository: ExhibitionsRepository
) {

    fun canEditorEditExhibition(principal: UserDetails, exhibitionId: Long) : Boolean {
        val exhibition = exhibitionsRepository.findById(exhibitionId)
        return (exhibition.isPresent && exhibition.get().editor.username == principal.username)
    }
}