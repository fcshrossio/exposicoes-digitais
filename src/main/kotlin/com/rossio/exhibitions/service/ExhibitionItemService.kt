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

    fun createMarker(markerDAO: MarkerDAO) : MarkerDAO =
        markersRepository.save(markerDAO)

    fun addSubItem(item: ExhibitionItemDAO, subItem: SubItemDAO) : ExhibitionItemDAO {
        if(subItem.id !== 0L && item.id !== 0L){
            item.addSubItem(subItem)
            return exhibitionItemsRepository.save(item)
        }
        throw Exception("id is 0")
    }

    fun addMarker(itemId: Long, markerDAO: MarkerDAO) {
     var item = getOneExhibitionItem(itemId)

            exhibitionItemsRepository.save(item)


    }

    fun removeMarker(markerDAO: MarkerDAO) {


    }

    fun addSubText(itemId: Long, subItemDAO: SubItemDAO) {


    }

    fun removeSubText(subItemDAO: SubItemDAO) {

    }
}