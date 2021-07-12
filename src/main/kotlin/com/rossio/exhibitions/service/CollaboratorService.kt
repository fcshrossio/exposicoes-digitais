package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.CollaboratorDAO
import com.rossio.exhibitions.model.CollaboratorRepository
import com.rossio.exhibitions.model.ExhibitionDAO
import com.rossio.exhibitions.model.UserDAO
import org.springframework.stereotype.Service

@Service
class CollaboratorService(
    val collaboratorRepository: CollaboratorRepository
) {

    fun getAllCollaborators() : List<CollaboratorDAO> =
        collaboratorRepository.findAll()

    fun getOneCollaborator(id: Long) =
        collaboratorRepository.findById(id).orElseThrow { NotFoundException("No Collaborator with ID: $id found") }

    fun addOneCollaborator(collaboratorDAO: CollaboratorDAO) =
        collaboratorRepository.save(collaboratorDAO)

    fun deleteCollaborator(id:Long) =
        collaboratorRepository.delete(getOneCollaborator(id))

}