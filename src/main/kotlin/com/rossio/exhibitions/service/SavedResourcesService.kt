package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.SavedResourcesDAO
import com.rossio.exhibitions.model.SavedResourcesRepository
import org.springframework.stereotype.Service

@Service
class SavedResourcesService(
    val savedResourcesRepository: SavedResourcesRepository
) {

    fun getSavedResources(): List<SavedResourcesDAO> =
        savedResourcesRepository.findAll()

    fun getOneSavedResources(id:Long): SavedResourcesDAO =
        savedResourcesRepository.findById(id).orElseThrow { NotFoundException("No Item with id: $id found") }

    fun createSavedResources(savedResourcesDAO: SavedResourcesDAO) : SavedResourcesDAO {
        if(savedResourcesDAO.id == 0L){
            return savedResourcesRepository.save(savedResourcesDAO)
        }
        throw Exception("id is not 0")
    }

    fun editSavedResources(savedResourcesDAO: SavedResourcesDAO, newSavedResourcesDAO: SavedResourcesDAO) : SavedResourcesDAO{

        return savedResourcesDAO
    }

    fun deleteSavedResources(id : Long){
        savedResourcesRepository.delete(getOneSavedResources(id))
    }
}