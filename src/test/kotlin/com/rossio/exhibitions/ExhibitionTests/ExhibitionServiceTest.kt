package com.rossio.exhibitions.ExhibitionTests

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.enums.Keywords
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.ExhibitionService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class ExhibitionServiceTest {

    @Autowired
    lateinit var exhibitionService: ExhibitionService

    @MockBean
    lateinit var exhibitionsRepository: ExhibitionsRepository

    @MockBean
    lateinit var collaboratorRepository: CollaboratorRepository

    /*
    companion object {
        val mapper = ObjectMapper().registerModule(KotlinModule())

        var uid:Long = 0L;

        val userDTO = UserDTO(uid++, "Henrique Raposo","password")

        var editorDTO = userDTO


        var digitalDAO = DigitalResourceDAO(uid++,"NOME")

        var dia = Date(0);

        val exhibitionDTO1 = ExhibitionDTO(
            uid++, ExhibitionControllerTest.editorDTO, emptyList(),"titulo","subtitulo", ExhibitionControllerTest.digitalDTO, emptyList(),dia,
            Status.PRIVATE, mutableListOf(),
            emptyList())

        val exhibitionDTO2 = ExhibitionDTO(
            uid++, ExhibitionControllerTest.editorDTO, emptyList(),"titulo2","suntitulo2", DigitalResourceDTO(), emptyList(),dia,
            Status.PRIVATE,
            mutableListOf(),
            emptyList())

        val exhibitionDTOList = listOf(exhibitionDTO1, exhibitionDTO2)

        val exhibitionDAO1 = ExhibitionDAO(exhibitionDTO1)

        val exhibitionDAO2 = ExhibitionDAO(exhibitionDTO2)

        val exhibitionDAOList = listOf(exhibitionDAO1, exhibitionDAO2)

        val userDTO4 =  UserDTO(uid++, "Colaborador Raposo","password")

        var collaboratorDAO = CollaboratorDAO(userDTO4)
    }

    @Test
    fun `get All Exhitions`() {
        Mockito.`when`(exhibitionsRepository.findAll()).thenReturn(exhibitionDAOList)

        assertThat(exhibitionService.getAllExhibitions(), equalTo(exhibitionDAOList))

    }

    @Test
    fun `get One Exhition`() {
        Mockito.`when`(exhibitionsRepository.findById(exhibitionDAO1.id)).thenReturn(Optional.of(exhibitionDAO1))
        assertThat(exhibitionService.getOneExhibition(exhibitionDAO1.id), equalTo(exhibitionDAO1))
    }

    @Test
    fun `create Exhition`() {
        Mockito.`when`(exhibitionsRepository.save(Mockito.any(ExhibitionDAO::class.java)))
            .then {
                val exhibitionDAO:ExhibitionDAO = it.getArgument(0)
                    assertEquals(exhibitionDAO.title, exhibitionDAO1.title)
                    assertEquals(exhibitionDAO.subtitle, exhibitionDAO1.subtitle)
                    assertEquals(exhibitionDAO.status, exhibitionDAO1.status)
                    //TODO COMPARE MORE ITEMS
                exhibitionDAO
            }

        exhibitionService.createExhibition(exhibitionDAO1)
    }

    @Test
    fun `delete Exhition`() {
        Mockito.`when`(exhibitionsRepository.delete(Mockito.any(ExhibitionDAO::class.java)))
            .then {
                val exhibitionDAO:ExhibitionDAO = it.getArgument(0)
                assertEquals(exhibitionDAO.title, exhibitionDAO2.title)
                assertEquals(exhibitionDAO.subtitle, exhibitionDAO2.subtitle)
                assertEquals(exhibitionDAO.status, exhibitionDAO2.status)
                //TODO COMPARE MORE ITEMS
                exhibitionDAO
            }

        exhibitionService.deleteExhibition(exhibitionDAO2)
    }

    @Test
    fun `add collaborator to exhibition`() {


        Mockito.`when`(exhibitionsRepository.save(Mockito.any(ExhibitionDAO::class.java)))
            .then {
                val exhibitionDAO:ExhibitionDAO = it.getArgument(0)
                assertEquals(exhibitionDAO.title, exhibitionDAO1.title)
                assertEquals(exhibitionDAO.subtitle, exhibitionDAO1.subtitle)
                assertEquals(exhibitionDAO.status, exhibitionDAO1.status)
                assertEquals(exhibitionDAO.collaborators, mutableListOf(collaboratorDAO))
                //TODO COMPARE MORE ITEMS
                exhibitionDAO
            }


       exhibitionService.addCollaborator(exhibitionDAO1,collaboratorDAO)
    }

    @Test
    fun `remove collaborator from exhibition`() {
        //var collaboratorDAO = CollaboratorDAO(uid++, "Colaborador Raposo", mutableListOf(exhibitionDAO1))

        var list: MutableList<CollaboratorDAO> = mutableListOf()

        Mockito.`when`(exhibitionsRepository.save(Mockito.any(ExhibitionDAO::class.java)))
            .then {
                val exhibitionDAO:ExhibitionDAO = it.getArgument(0)
                assertEquals(exhibitionDAO.title, exhibitionDAO1.title)
                assertEquals(exhibitionDAO.subtitle, exhibitionDAO1.subtitle)
                assertEquals(exhibitionDAO.status, exhibitionDAO1.status)
                assertEquals(exhibitionDAO.collaborators, list)
                //TODO COMPARE MORE ITEMS
                exhibitionDAO
            }

        exhibitionService.removeCollaborator(exhibitionDAO1,collaboratorDAO)
    }

    @Test
    fun `add keyword to exhibition`() {
        var keyword = Keywords.Teste1

        Mockito.`when`(exhibitionsRepository.save(Mockito.any(ExhibitionDAO::class.java)))
            .then {
                val exhibitionDAO:ExhibitionDAO = it.getArgument(0)
                assertEquals(exhibitionDAO.title, exhibitionDAO1.title)
                assertEquals(exhibitionDAO.subtitle, exhibitionDAO1.subtitle)
                assertEquals(exhibitionDAO.status, exhibitionDAO1.status)
                //TODO COMPARE MORE ITEMS
                assertEquals(exhibitionDAO.keywords, mutableListOf(keyword))
                exhibitionDAO
            }

        exhibitionService.addKeyword(exhibitionDAO1,keyword)
    }

    @Test
    fun `remove keyword from Exhibition`() {
        var keyword = Keywords.Teste1

        var keywordList:MutableList<Keywords> = mutableListOf()

        Mockito.`when`(exhibitionsRepository.save(Mockito.any(ExhibitionDAO::class.java)))
            .then {
                val exhibitionDAO:ExhibitionDAO = it.getArgument(0)
                assertEquals(exhibitionDAO.title, exhibitionDAO1.title)
                assertEquals(exhibitionDAO.subtitle, exhibitionDAO1.subtitle)
                assertEquals(exhibitionDAO.status, exhibitionDAO1.status)
                //TODO COMPARE MORE ITEMS
                assertEquals(exhibitionDAO.keywords, keywordList)
                exhibitionDAO
            }

        exhibitionService.addKeyword(exhibitionDAO1,keyword)
    }

    @Test
    fun `change Exhibition Status`() {
        var status = Status.PUBLIC

        Mockito.`when`(exhibitionsRepository.save(Mockito.any(ExhibitionDAO::class.java)))
            .then {
                val exhibitionDAO:ExhibitionDAO = it.getArgument(0)
                assertEquals(exhibitionDAO.title, exhibitionDAO1.title)
                assertEquals(exhibitionDAO.subtitle, exhibitionDAO1.subtitle)
                assertEquals(exhibitionDAO.status, exhibitionDAO1.status)
                assertEquals(exhibitionDAO.keywords, status)
                exhibitionDAO
            }

        exhibitionService.changeStatus(exhibitionDAO1,status)

    }

    @Test
    fun `get recent Exhibitions`() {
        Mockito.`when`(exhibitionsRepository.findAll()).thenReturn(exhibitionDAOList)

        assertThat(exhibitionService.getAllExhibitions(), equalTo(exhibitionDAOList))
        //TODO CHECK DATE
    }






*/

}