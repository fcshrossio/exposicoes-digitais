package com.rossio.exhibitions.service

import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*

import com.rossio.exhibitions.model.ExhibitionItemsRepository
import org.springframework.stereotype.Service

@Service
class ExhibitionItemService(
    val exhibitionItemsRepository : ExhibitionItemsRepository,
    val markersRepository: MarkersRepository
) {
    fun getAllExhibitionItems(): List<ExhibitionItemDAO> =
        exhibitionItemsRepository.findAll()

    fun getOneExhibitionItem(id : Long) : ExhibitionItemDAO =
        exhibitionItemsRepository.findById(id).orElseThrow { NotFoundException("No Exhibition with id: $id found") }

    fun createOneExhibitionItem(item : ExhibitionItemDAO) : ExhibitionItemDAO =
        exhibitionItemsRepository.save(item)

    fun deleteOneExhibitionItem(id: Long)  =
        exhibitionItemsRepository.delete(getOneExhibitionItem(id))

    fun createMarker(markerDAO: MarkerDAO) : MarkerDAO =
        markersRepository.save(markerDAO)

    fun addMarker(itemId: Long, markerDAO: MarkerDAO) {
     var item = getOneExhibitionItem(itemId)
        if (item is MapItemDAO){
            item.addMarker(createMarker(markerDAO))
            exhibitionItemsRepository.save(item)
        }
        else
        {

        }

    }
}