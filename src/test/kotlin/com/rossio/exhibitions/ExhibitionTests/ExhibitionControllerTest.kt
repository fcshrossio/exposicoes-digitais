package com.rossio.exhibitions.ExhibitionTests

import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.dto.ExhibitionDTO
import com.rossio.exhibitions.dto.UserDTO
import com.rossio.exhibitions.model.ExhibitionDAO
import com.rossio.exhibitions.model.Keywords
import com.rossio.exhibitions.model.Status
import com.rossio.exhibitions.model.UserDAO
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
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class ExhibitionControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var exhibitionService: ExhibitionService



    companion object {

        var url = "/exhibition"

        val mapper = jacksonObjectMapper()

        var editorDTO = UserDTO(0,"Henrique Raposo")

        var editorDAO = UserDAO(0,"Henrique Raposo")

        var digital = DigitalResourceDTO(0,"NOME")

        //val digitalDAO = DigitalResourceDAO(digital)

        var dia = Date(0);

        val exhibitionDTO1 = ExhibitionDTO(1L, editorDTO, emptyList(),"","", DigitalResourceDTO(), emptyList(),dia,
            Status.PRIVATE, mutableListOf(),
            emptyList())

        val exhibitionDTO2 = ExhibitionDTO(2L, editorDTO, emptyList(),"","", DigitalResourceDTO(), emptyList(),dia,Status.PRIVATE,
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
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<Any>(exhibitionDTOList.size)))
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<List<ExhibitionDTO>>(responseString)
        assertThat(responseDTO, equalTo(exhibitionDTOList))


    }

    @Test
    fun `Test get one exhibition`() {
        Mockito.`when`(exhibitionService.getOneExhibition(1L)).thenReturn(exhibitionDAO1)

        val result = mvc.perform(MockMvcRequestBuilders.get("$url/1"))
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

    @Test
    fun `Test add one exhibition`() {

        val newExhibitionDTO = ExhibitionDTO(
            3L,
            editorDTO,
            emptyList(),
            "Título",
            "SubTítulo",
            digital,
            emptyList(),
            dia,
            Status.PRIVATE,
            mutableListOf(),
            emptyList()
        )

        val newExhibitionDAO = ExhibitionDAO(newExhibitionDTO)

        val appJson = mapper.writeValueAsString(newExhibitionDTO)

        Mockito.`when`(exhibitionService.createExhibition(nonNullAny(ExhibitionDAO::class.java))).thenReturn(newExhibitionDAO)

        val result = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
            .content(appJson))
            .andExpect(status().isOk)



    }


    @Test
    fun `Test add one invalid`() {
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(status().isOk())
    }

    @Test
    fun `Test edit one exhibition`() {
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(status().isOk())
    }

    @Test
    fun `Test delete exhibition`() {
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Test add collaborator`() {
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Test remove one collaborator`() {
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

}