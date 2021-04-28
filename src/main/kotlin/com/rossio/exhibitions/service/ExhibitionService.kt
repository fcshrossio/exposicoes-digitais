package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.CollaboratorDAO
import com.rossio.exhibitions.model.ExhibitionDAO
import com.rossio.exhibitions.model.ExhibitionItemDAO
import com.rossio.exhibitions.model.ExhibitionsRepository
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

    fun addExhibitionItem(exhibitionId: Long, item: ExhibitionItemDAO) =
        getOneExhibition(exhibitionId).let { it.addExhibitionItem(item); exhibitionsRepository.save(it) }
}
