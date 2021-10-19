package com.rossio.exhibitions.ExhibitionItemTests

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.rossio.exhibitions.ExhibitionTests.ExhibitionControllerTest
import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.enums.Status
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.ExhibitionItemService
import com.rossio.exhibitions.service.ExhibitionService
import junit.framework.Assert
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
class ExhibitionItemControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var exhibitionItemService: ExhibitionItemService

    @MockBean
    lateinit var exhibitionService: ExhibitionService

/*

    companion object {

        var url = "/item"

        var uuid: Long = 0L;

        val mapper = jacksonObjectMapper()//.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        var userDTO = UserDTO(uuid++,"Henrique Raposo","password")

        var editorDAO = EditorDAO(userDTO)

        var editorDTO = UserDTO(editorDAO)

        var digitalDAO = DigitalResourceDAO(uuid++,"NOME")

        val digitalDTO = DigitalResourceDTO(digitalDAO)

        var dia = Date(0);

        val exhibitionDTO1 = ExhibitionDTO(
            uuid++,
            editorDTO, emptyList(),"titulo","subtitulo",
            digitalDTO, emptyList(),dia,
            Status.PRIVATE, mutableListOf(),
            emptyList())

        val exhibitionDTO2 = ExhibitionDTO(
            uuid++,
            editorDTO, emptyList(),"titulo2","suntitulo2", DigitalResourceDTO(), emptyList(),dia,Status.PRIVATE,
            mutableListOf(),
            emptyList())

        val exhibitionDTOList = listOf(exhibitionDTO1, exhibitionDTO2)

        val exhibitionDAO1 = ExhibitionDAO(exhibitionDTO1)

        val exhibitionDAO2 = ExhibitionDAO(exhibitionDTO2)

        val exhibitionDAOList = listOf(exhibitionDAO1, exhibitionDAO2)

        val exhibitionItemDTO1 = IntroductionItemDTO(uuid++,0, exhibitionDAO1.id,"texto")

        val exhibitionItemDTO2 = TextItemDTO(uuid++,1,exhibitionDAO1.id,"", emptyList())

        val exhibitionItemDTO3 = AboutItemDTO(uuid++,2,exhibitionDAO1.id,"", emptyList())


        val exhibitionItemList = listOf<ExhibitionItemDTO>(exhibitionItemDTO1, exhibitionItemDTO2, exhibitionItemDTO3)

        val exhibitionItemDAO1 = IntroductionItemDAO(exhibitionItemDTO1, exhibitionDAO1)



        val exhibitionItemDAO3 = AboutItemDAO(exhibitionItemDTO3, exhibitionDAO1)

        val exhibitionItemListDAO = listOf<ExhibitionItemDAO>(exhibitionItemDAO1, exhibitionItemDAO2, exhibitionItemDAO3)

    }

    @Test
    fun `Test get all exhibitions items`() {
        Mockito.`when`(exhibitionItemService.getAllExhibitionItems()).thenReturn(exhibitionItemListDAO)

        val result = mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$",
                    Matchers.hasSize<Any>(exhibitionItemList.size)
                ))
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<List<ExhibitionItemDTO>>(responseString)
        assertThat(responseDTO, Matchers.equalTo(exhibitionItemList))


    }

    @Test
    fun `Test get one exhibition item`() {
        Mockito.`when`(exhibitionItemService.getOneExhibitionItem(exhibitionDAO1.id)).thenReturn(exhibitionItemDAO1)

        val result = mvc.perform(MockMvcRequestBuilders.get("${url}/"+ exhibitionDAO1.id))
            .andExpect(status().isOk())
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionItemDTO>(responseString)
        assertThat(responseDTO, equalTo(exhibitionItemDTO1))
    }

    @Test
    fun `Test get one exhibition item (Not found)`() {
        Mockito.`when`(exhibitionItemService.getOneExhibitionItem(4)).thenThrow(NotFoundException("not found"))

        val result = mvc.perform(MockMvcRequestBuilders.get("${url}/4"))
            .andExpect(status().is4xxClientError)

    }


    fun <T>nonNullAny(t:Class<T>):T = Mockito.any(t)

    @Test
    fun `Test create exhibition item Introduction`() {

        val newExhibitionItemDTO = IntroductionItemDTO(uuid++,0, exhibitionDAO1.id,"text")

        val newExhibitionItemDAO = mapItemDTOtoDAO(newExhibitionItemDTO, exhibitionDAO1)


        Mockito.`when`(exhibitionItemService.createOneExhibitionItem(newExhibitionItemDAO)).thenReturn(newExhibitionItemDAO)

        val itemJson = ExhibitionControllerTest.mapper.writeValueAsString(newExhibitionItemDTO)

        Mockito.`when`(exhibitionService.getOneExhibition(exhibitionDAO1.id)).thenReturn(
            exhibitionDAO1
        )

        val result = mvc.perform(
            post("${url}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJson)
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionItemDTO>(responseString)
        Assert.assertEquals(responseDTO, newExhibitionItemDTO)

    }



    @Test
    fun `Test edit one exhibition item`() {
        var edited = IntroductionItemDAO(exhibitionItemDAO1.id, exhibitionItemDAO1.position++, exhibitionDAO1, "new text")

        var editedDTO = IntroductionItemDTO(edited)

        Mockito.`when`(exhibitionItemService.getOneExhibitionItem(exhibitionItemDAO1.id)).thenReturn(exhibitionItemDAO1)

        Mockito.`when`(exhibitionService.getOneExhibition(exhibitionItemDAO1.exhibition.id)).thenReturn(exhibitionDAO1)

        Mockito.`when`(exhibitionItemService.editOneExhibitionItem(exhibitionItemDAO1, edited)).thenReturn(exhibitionItemDAO1)

        val itemJson = mapper.writeValueAsString(editedDTO)

        val result = mvc.perform(
            put("${url}/" + exhibitionItemDAO1.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJson)
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<ExhibitionItemDTO>(responseString)
        //Assert.assertEquals(responseDTO, editedDTO)
        //TODO ASSERT

    }

    @Test
    fun `Test delete exhibition item`() {
        /**
        //Mockito.`when`(exhibitionItemService.getAllExhibitionItems()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.delete("${url}/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
        **/
    }

    @Test
    fun `Test get all markers`(){

    }

    @Test
    fun `Test create marker`(){

    }

    @Test
    fun `Test delete marker`(){

    }

    @Test
    fun `Test get all sub abouts`(){

    }

    @Test
    fun `Test create sub about`(){

    }

    @Test
    fun `Test delete sub about`(){

    }

    @Test
    fun `Test get all sub texts`(){

    }

    @Test
    fun `Test create sub text`(){

    }

    @Test
    fun `Test delete sub text`(){

    }


 */
}