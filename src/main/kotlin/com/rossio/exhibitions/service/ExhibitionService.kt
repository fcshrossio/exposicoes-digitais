package com.rossio.exhibitions.service

import com.rossio.exhibitions.dto.ExhibitionDetailsDTO
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class ExhibitionService(
    val exhibitionsRepository: ExhibitionsRepository
) {

    fun getAllExhibitions(): List<ExhibitionDAO> =
        exhibitionsRepository.findAll()

    fun getOneExhibition(id : Long) : ExhibitionDAO =
        exhibitionsRepository.findById(id).orElseThrow { NotFoundException("No Exhibition with id: $id found") }

    fun createExhibition(exhibition: ExhibitionDAO) : ExhibitionDAO =
        exhibitionsRepository.save(exhibition)

    fun editExhibitionDetails(details: ExhibitionDetailsDTO, exhibition: ExhibitionDAO) : ExhibitionDAO =
        if (exhibition.editDetails(details)) {
            exhibitionsRepository.save(exhibition)
        } else
            exhibition


    fun deleteExhibition(exhibition: ExhibitionDAO) =
        exhibitionsRepository.delete(exhibition)

    fun addCollaborator(exhibition: ExhibitionDAO, collaborator: CollaboratorDAO) {
        exhibition.addCollaborator(collaborator)
        exhibitionsRepository.save(exhibition)
    }

    fun removeCollaborator(exhibition: ExhibitionDAO, collaborator: CollaboratorDAO) {
        if(exhibition.removeCollaborator(collaborator))
            exhibitionsRepository.save(exhibition)
    }

    fun addKeyword(exhibition: ExhibitionDAO, keyword: Keywords) {
        if(exhibition.addKeyword(keyword))
            exhibitionsRepository.save(exhibition)
    }

    fun removeKeyword(exhibition: ExhibitionDAO, keyword: Keywords) {
        if(exhibition.removeKeyword(keyword))
            exhibitionsRepository.save(exhibition)
    }


    fun changeStatus(exhibition: ExhibitionDAO, status: Status) {
        if(exhibition.changeStatus(status))
            exhibitionsRepository.save(exhibition)
    }


    fun recentExhibitions() =
        exhibitionsRepository.findAll(Sort.by("date").descending())

}
