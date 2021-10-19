package com.rossio.exhibitions.service

import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.ExhibitionDetailsDTO
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.jvm.Throws


@Service
class ExhibitionService(
    val exhibitionsRepository: ExhibitionsRepository
) {

    fun getAllExhibitions(): List<ExhibitionDAO> =
        exhibitionsRepository.findAll()

    fun getAllPublicExhibitions(): List<ExhibitionDAO> =
        exhibitionsRepository.findAllByStatusIs(Status.PUBLIC)


    fun getOneExhibition(id : Long) : ExhibitionDAO =
        exhibitionsRepository.findById(id).orElseThrow { NotFoundException("No Exhibition with id: $id found") }

    fun createExhibition(exhibition: ExhibitionDAO) : ExhibitionDAO {

        if(exhibition.id == 0L) {
            return exhibitionsRepository.save(exhibition)
        }
        throw Exception("id is not 0")
    }


    fun editExhibitionDetails(details: ExhibitionDTO, exhibition: ExhibitionDAO) : ExhibitionDAO =
        if (exhibition.editDetails(details)) {
            print("exhibition edited...saving...")
            exhibitionsRepository.save(exhibition)
        } else
            exhibition

    fun editExhibitionCredits(credits : String, exhibition: ExhibitionDAO) : ExhibitionDAO {
        exhibition.editCredits(credits)
        return exhibitionsRepository.save(exhibition)
    }

    fun editExhibitionAuxiliaryMaterials(onlineResourcesNova : String, bibliography : String, audioVisualResources : String, webPlaces : String, exhibition: ExhibitionDAO) : ExhibitionDAO {
        exhibition.editAuxiliaryMaterials(onlineResourcesNova,bibliography,audioVisualResources,webPlaces)
        return exhibitionsRepository.save(exhibition)
    }

    fun addExhibitionItem(exhibition: ExhibitionDAO, item: ExhibitionItemDAO) : ExhibitionDAO {
        exhibition.addExhibitionItem(item)
        return exhibitionsRepository.save(exhibition)
    }
    fun deleteExhibition(exhibition: ExhibitionDAO) =
        exhibitionsRepository.delete(exhibition)

    fun addCollaborator(exhibition: ExhibitionDAO, collaborator: CollaboratorDAO) {
        exhibition.addCollaborator(collaborator)
        exhibitionsRepository.save(exhibition)
    }

    fun removeCollaborator(exhibition: ExhibitionDAO, collaborator: CollaboratorDAO) {
        if(exhibition.removeCollaborator(collaborator))
            exhibitionsRepository.save(exhibition)
    }

    fun addKeyword(exhibition: ExhibitionDAO, keyword: Keywords) {
        if(exhibition.addKeyword(keyword))
            exhibitionsRepository.save(exhibition)
    }

    fun removeKeyword(exhibition: ExhibitionDAO, keyword: Keywords) {
        if(exhibition.removeKeyword(keyword))
            exhibitionsRepository.save(exhibition)
    }


    fun changeStatus(exhibition: ExhibitionDAO, status: Status) {
        if(exhibition.changeStatus(status))
            exhibitionsRepository.save(exhibition)
    }


    fun recentExhibitions() =
        exhibitionsRepository.findAll(Sort.by("date").descending())

}
