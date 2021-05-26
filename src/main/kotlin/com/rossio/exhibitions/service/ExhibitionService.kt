package com.rossio.exhibitions.service

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

    fun deleteExhibition(id: Long) =
        exhibitionsRepository.delete(getOneExhibition(id))

    fun addCollaborator(exhibition: ExhibitionDAO, collaborator: CollaboratorDAO) {
        exhibition.addCollaborator(collaborator)
        exhibitionsRepository.save(exhibition)
    }

    fun removeCollaborator(exhibition: ExhibitionDAO, collaborator: CollaboratorDAO) {
        exhibition.removeCollaborator(collaborator)
        exhibitionsRepository.save(exhibition)
    }

    fun addKeyword(exhibition: ExhibitionDAO, keyword: Keywords) {
        exhibition.addKeyword(keyword)
        exhibitionsRepository.save(exhibition)
    }

    fun removeKeyword(exhibition: ExhibitionDAO, keyword: Keywords) {
        exhibition.removeKeyword(keyword)
        exhibitionsRepository.save(exhibition)
    }


    fun changeStatus(exhibitionId: Long, status: Status) =
        getOneExhibition(exhibitionId).let { it.changeStatus(status); exhibitionsRepository.save(it)}

    fun recentExhibitions() =
        exhibitionsRepository.findAll(Sort.by("date").descending())
}
