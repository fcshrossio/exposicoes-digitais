package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import org.springframework.stereotype.Service

@Service
class ExhibitionSubItemService(
    val subTextRepository: SubTextRepository,
    val markersRepository: MarkersRepository
) {

    fun getAllMarkers(): List<MarkerDAO> =
        markersRepository.findAll()

    fun getOneMarker(id:Long): MarkerDAO =
        markersRepository.findById(id).orElseThrow { NotFoundException("No Item with id: $id found") }

    fun getAllSubTextItems(): List<SubItemDAO> =
        subTextRepository.findAll()

    fun getOneSubTextItem(id:Long) : SubItemDAO =
        subTextRepository.findById(id).orElseThrow { NotFoundException("No Item with id: $id found") }

    fun createSubItem(subItemDAO: SubItemDAO): SubItemDAO {
        if(subItemDAO.id == 0L){
            return subTextRepository.save(subItemDAO)
        }
        throw Exception("id is not 0")
    }

    fun editSubItem(subItemDAO: SubItemDAO, newItem : SubItemDAO) : SubItemDAO
    {
        if (subItemDAO.editSubItem(newItem)) {
            return subTextRepository.save(newItem)
        }
        return subItemDAO
    }

    fun deleteSubTextItem(id: Long) =
        subTextRepository.delete(getOneSubTextItem(id))

}