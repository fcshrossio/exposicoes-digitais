package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*

import com.rossio.exhibitions.model.ExhibitionItemsRepository
import org.springframework.stereotype.Service

@Service
class ExhibitionItemService(
    val exhibitionItemsRepository : ExhibitionItemsRepository,
    val markersRepository: MarkersRepository,
    val subTextRepository: SubTextRepository
) {
    fun getAllExhibitionItems(): List<ExhibitionItemDAO> =
        exhibitionItemsRepository.findAll()

    fun getOneExhibitionItem(id : Long) : ExhibitionItemDAO =
        exhibitionItemsRepository.findById(id).orElseThrow { NotFoundException("No Exhibition Item with id: $id found") }

    fun createOneExhibitionItem(item : ExhibitionItemDAO) : ExhibitionItemDAO {
        if(item.id == 0L){
            return exhibitionItemsRepository.save(item)
        }
        throw Exception("id is not 0")
    }


    fun editOneExhibitionItem(item : ExhibitionItemDAO, newItem : ExhibitionItemDAO): ExhibitionItemDAO {
        if (item.editItem(newItem)) {
            return exhibitionItemsRepository.save(item)
        } else{
            return item
        }
    }

    fun deleteOneExhibitionItem(id: Long)  =
        exhibitionItemsRepository.delete(getOneExhibitionItem(id))





    fun removeSubItem(itemId: Long,subItemDAO: SubItemDAO) : ExhibitionItemDAO{
        var item : ExhibitionItemDAO = getOneExhibitionItem(itemId)
        if(item.subItems.contains(subItemDAO))
        {
            item.removeSubItem(subItemDAO)
            return exhibitionItemsRepository.save(item)
        }
        else
        {
            throw NotFoundException("The $itemId item does not contain this sub item")
        }
    }
}