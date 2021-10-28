package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import org.springframework.stereotype.Service

@Service
class ExhibitionSubItemService(
    val subTextRepository: SubTextRepository
) {


    fun getAllSubItems(): List<SubItemDAO> =
        subTextRepository.findAll()

    fun getOneSubItem(id:Long) : SubItemDAO =
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


}