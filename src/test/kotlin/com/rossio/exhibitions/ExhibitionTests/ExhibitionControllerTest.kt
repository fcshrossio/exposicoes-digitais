package com.rossio.exhibitions.ExhibitionTests

import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.service.ExhibitionService
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.DigitalResourceService
import com.rossio.exhibitions.service.EditorService
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@AutoConfigureMockMvc
class ExhibitionControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var exhibitionService: ExhibitionService

    @MockBean
    lateinit var editorService: EditorService

    @MockBean
    lateinit var digitalResourceService: DigitalResourceService



    companion object {

        var url = "/exhibition"

        var uuid: Long = 0L;

        val mapper = jacksonObjectMapper()

        var editorDAO = EditorDAO(uuid++,"Henrique Raposo")


        var editorDTO = UserDTO(editorDAO)

        var digitalDAO = DigitalResourceDAO(uuid++,"NOME")

        val digitalDTO = DigitalResourceDTO(digitalDAO)

        var dia = Date(0);

        val exhibitionDTO1 = ExhibitionDTO(uuid++, editorDTO, emptyList(),"titulo","subtitulo", digitalDTO, emptyList(),dia,
            Status.PRIVATE, mutableListOf(),
            emptyList())

        val exhibitionDTO2 = ExhibitionDTO(uuid++, editorDTO, emptyList(),"titulo2","suntitulo2", DigitalResourceDTO(), emptyList(),dia,Status.PRIVATE,
            mutableListOf(),
            emptyList())

        val exhibitionDTOList = listOf(exhibitionDTO1, exhibitionDTO2)

        val exhibitionDAO1 = ExhibitionDAO(exhibitionDTO1)

        val exhibitionDAO2 = ExhibitionDAO(exhibitionDTO2)

        val exhibitionDAOList = listOf(exhibitionDAO1, exhibitionDAO2)


    }

    @Test
    fun `Test get all exhibitions`() {
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(exhibitionDAOList)

        val result = mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<Any>(exhibitionDTOList.size)))
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<List<ExhibitionDTO>>(responseString)
        assertThat(responseDTO, equalTo(exhibitionDTOList))


    }

    @Test
    fun `Test get one exhibition`() {
        Mockito.`when`(exhibitionService.getOneExhibition(exhibitionDTO1.id)).thenReturn(exhibitionDAO1)

        val result = mvc.perform(MockMvcRequestBuilders.get("$url/" + exhibitionDTO1.id))
            .andExpect(status().isOk())
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionDTO>(responseString)
        assertThat(responseDTO, equalTo(exhibitionDTO1))
    }

    @Test
    fun `Test get one exhibition (Not found)`() {
        Mockito.`when`(exhibitionService.getOneExhibition(4)).thenThrow(NotFoundException("not found"))

        val result = mvc.perform(MockMvcRequestBuilders.get("${url}/4"))
            .andExpect(status().is4xxClientError)

    }


    fun <T>nonNullAny(t:Class<T>):T = Mockito.any(t)

    @Transactional
    @Test
    fun `Test add one exhibition`() {



        val newExhibitionDTO = ExhibitionDTO(uuid++, editorDTO, emptyList(),"titulo","subtitulo", digitalDTO, emptyList(),dia,
            Status.PRIVATE, mutableListOf(),
            emptyList())


        val newExhibitionDAO = ExhibitionDAO(newExhibitionDTO)

        val exhibitionJson = mapper.writeValueAsString(newExhibitionDTO)

        Mockito.`when`(exhibitionService.createExhibition(nonNullAny(ExhibitionDAO::class.java))).thenReturn( newExhibitionDAO )

        Mockito.`when`(editorService.getOneEditor(exhibitionDTO1.editor.id)).thenReturn(editorDAO)

        Mockito.`when`(digitalResourceService.getOneDigitalResource(exhibitionDTO1.cover.id)).thenReturn(digitalDAO)

        Mockito.`when`(exhibitionService.getOneExhibition(exhibitionDTO1.id)).thenReturn(exhibitionDAO1)
        val result = mvc.perform(MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .content(exhibitionJson))
            .andExpect(status().isOk)
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionDTO>(responseString)
        assertEquals(responseDTO, newExhibitionDTO)


    }


    @Test
    fun `Test add one invalid`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(status().isOk())
    }

    @Test
    fun `Test edit one exhibition`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(status().isOk())
    }

    @Test
    fun `Test delete exhibition`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Test add collaborator`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Test remove one collaborator`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

}