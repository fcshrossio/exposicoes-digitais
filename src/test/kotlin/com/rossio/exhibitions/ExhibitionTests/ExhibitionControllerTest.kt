package com.rossio.exhibitions.ExhibitionTests

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
import com.rossio.exhibitions.ExhibitionItemTests.ExhibitionItemControllerTest
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.dia
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.digitalDAO
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.digitalDTO
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.editorDAO
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.editorDTO
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.exhibitionDAO1
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.exhibitionDAOList
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.exhibitionDTO1
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.exhibitionDTOList
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.mapper
import com.rossio.exhibitions.ExhibitionsApplicationTests.Companion.uuid
import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.DigitalResourceService
import com.rossio.exhibitions.service.EditorService
import com.rossio.exhibitions.service.ExhibitionItemService
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
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
    lateinit var exhibitionItemService: ExhibitionItemService

    @MockBean
    lateinit var editorService: EditorService

    @MockBean
    lateinit var digitalResourceService: DigitalResourceService


    companion object {

        var url = "/exhibition"



    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test get all exhibitions(ROLE ADMIN)`() {
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
    fun `Test get all exhibitions(NO ROLE)`() {
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(exhibitionDAOList)

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(status().isForbidden())



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


    fun <T> nonNullAny(t: Class<T>): T = Mockito.any(t)

    @Transactional
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test add one exhibition`() {


        val newExhibitionDTO = ExhibitionDTO(
            uuid++, editorDTO, emptyList(), "titulo", "subtitulo", digitalDTO, emptyList(), dia,
            Status.PRIVATE, mutableListOf(),
            emptyList(),
            "",
            "",
            "",
            "",
            ""
        )


        val newExhibitionDAO = ExhibitionDAO(newExhibitionDTO, editorDAO, digitalDAO)

        val exhibitionJson = mapper.writeValueAsString(newExhibitionDTO)

        Mockito.`when`(exhibitionService.createExhibition(nonNullAny(ExhibitionDAO::class.java)))
            .thenReturn(newExhibitionDAO)

        Mockito.`when`(editorService.getOneEditor(exhibitionDTO1.editor.id)).thenReturn(editorDAO)

        Mockito.`when`(digitalResourceService.getOneDigitalResource(exhibitionDTO1.cover.id)).thenReturn(digitalDAO)

        Mockito.`when`(exhibitionService.getOneExhibition(exhibitionDTO1.id)).thenReturn(exhibitionDAO1)
        val result = mvc.perform(
            MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(exhibitionJson)
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionDTO>(responseString)
        assertEquals(responseDTO, newExhibitionDTO)


    }


    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test add one invalid`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(status().isOk())
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test edit one exhibition`() {
        //TODO Complete test
        /**
        var detailsDTO = ExhibitionDetailsDTO(exhibitionDAO1.id, "novo titulo", "novo subtitulo")

        val detailsJson = mapper.writeValueAsString(detailsDTO)


       exhibitionDAO1.editDetails(detailsDTO)

        Mockito.`when`(exhibitionService.getOneExhibition(exhibitionDAO1.id)).thenReturn(exhibitionDAO1)

        Mockito.`when`(exhibitionService.editExhibitionDetails(nonNullAny(ExhibitionDetailsDTO::class.java),nonNullAny(ExhibitionDAO::class.java)))
            .thenReturn(exhibitionDAO1)

        val result = mvc.perform(put("${url}/" + exhibitionDAO1.id)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .content(detailsJson)
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionDTO>(responseString)
        assertEquals(responseDTO.title, detailsDTO.title)
        assertEquals(responseDTO.subtitle, detailsDTO.subtitle)
        **/
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test delete exhibition`() {
        //TODO complete
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test add collaborator`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test remove one collaborator`() {
        //TODO
        Mockito.`when`(exhibitionService.getAllExhibitions()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun `Test add one exhibition item`() {
    /**
        val newIntroductionItemDTO = IntroductionItemDTO(
            0,
            0,
            exhibitionDAO1.id,
            "título",
            "text"
        )

        val newExhibitionItemDAO = IntroductionItemDAO(
            newIntroductionItemDTO,
            exhibitionDAO1
        )

        val itemJson = mapper.writeValueAsString(newIntroductionItemDTO)

        Mockito.`when`(exhibitionItemService.createOneExhibitionItem(nonNullAny(ExhibitionItemDAO::class.java)))
            .thenReturn(newExhibitionItemDAO)

        Mockito.`when`(exhibitionService.getOneExhibition(exhibitionDTO1.id)).thenReturn(
            exhibitionDAO1
        )

        val result = mvc.perform(
            MockMvcRequestBuilders.post("${url}/" + exhibitionDAO1.id + "/additem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJson)
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionItemDTO>(responseString)
        assertEquals(responseDTO, newIntroductionItemDTO)
        **/



    @Test
    fun `Test add one invalid`() {

    }
}

}