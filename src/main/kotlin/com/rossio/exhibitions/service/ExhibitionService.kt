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

    fun addCollaborator(exhibitionId: Long, collaborator: CollaboratorDAO) =
        getOneExhibition(exhibitionId).let { it.addCollaborator(collaborator); exhibitionsRepository.save(it)}

    fun removeCollaborator(exhibitionId: Long, collaborator: CollaboratorDAO) =
        getOneExhibition(exhibitionId).let { it.removeCollaborator(collaborator); exhibitionsRepository.save(it)}

    fun addKeyword(exhibitionId: Long, keyword: Keywords) =
        getOneExhibition(exhibitionId).let { it.addKeyword(keyword); exhibitionsRepository.save(it)}

    fun removeKeyword(exhibitionId: Long, keyword: Keywords) =
        getOneExhibition(exhibitionId).let { it.removeKeyword(keyword); exhibitionsRepository.save(it)}

    fun changeStatus(exhibitionId: Long, status: Status) =
        getOneExhibition(exhibitionId).let { it.changeStatus(status); exhibitionsRepository.save(it)}

    fun recentExhibitions() =
        exhibitionsRepository.findAll(Sort.by("date").descending())
}
