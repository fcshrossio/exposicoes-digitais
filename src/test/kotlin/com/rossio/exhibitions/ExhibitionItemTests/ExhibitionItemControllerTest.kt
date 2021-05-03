package com.rossio.exhibitions.ExhibitionItemTests

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.rossio.exhibitions.dto.*
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.*
import com.rossio.exhibitions.service.ExhibitionItemService
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
class ExhibitionItemControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var exhibitionItemService: ExhibitionItemService



    companion object {
        /**
        var url = "/item"

        val mapper = jacksonObjectMapper()

        var editorDTO = UserDTO(0,"Henrique Raposo")

        var digital = DigitalResourceDTO(0,"NOME")

        var dia = Date(0)

        val exhibitionDTO1 = ExhibitionDTO(1L, editorDTO, emptyList(),"titulo","subtitulo", digital, emptyList(),dia,Status.PRIVATE,Keywords.Teste1,
            emptyList())

        val exhibitionItemDTO1 = IntroductionItemDTO(2L,0,"texto")

        val exhibitionItemDTO2 = TextItemDTO(3L,1,"")

        val exhibitionItemDTO3 = AboutItemDTO(3L,1,"")


        val exhibitionItemList = listOf<ExhibitionItemDTO>(exhibitionItemDTO1, exhibitionItemDTO2, exhibitionItemDTO3)

        val exhibitionItemDAO1 = IntroductionItemDAO(exhibitionItemDTO1)

        val exhibitionItemDAO2 = TextItemDAO(exhibitionItemDTO2)

        val exhibitionItemDAO3 = AboutItemDAO(exhibitionItemDTO3)

        val exhibitionItemListDAO = listOf<ExhibitionItemDAO>(exhibitionItemDAO1, exhibitionItemDAO2, exhibitionItemDAO3)
        **/
    }

    /**
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
        MatcherAssert.assertThat(responseDTO, Matchers.equalTo(exhibitionItemList))


    }

    @Test
    fun `Test get one exhibition item`() {
        Mockito.`when`(exhibitionItemService.getOneExhibitionItem(1L)).thenReturn(exhibitionItemDAO1)

        val result = mvc.perform(MockMvcRequestBuilders.get("${url}/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<IntroductionItemDTO>(responseString)
        MatcherAssert.assertThat(responseDTO, Matchers.equalTo(exhibitionItemDTO1))
    }

    @Test
    fun `Test get one exhibition item (Not found)`() {
        Mockito.`when`(exhibitionItemService.getOneExhibitionItem(4)).thenThrow(NotFoundException("not found"))

        val result = mvc.perform(MockMvcRequestBuilders.get("${url}/4"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)

    }


    fun <T>nonNullAny(t:Class<T>):T = Mockito.any(t)

    @Test
    fun `Test add one exhibition item`() {

        val newIntroductionItemDTO = IntroductionItemDTO(
            0,
            0,
            "text"
        )
        val newExhibitionItemDAO = IntroductionItemDAO(newIntroductionItemDTO)

        val appJson = mapper.writeValueAsString(newIntroductionItemDTO)

        Mockito.`when`(exhibitionItemService.createOneExhibitionItem(nonNullAny(ExhibitionItemDAO::class.java))).thenReturn(newExhibitionItemDAO)

        val result = mvc.perform(
            MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
            .content(appJson))
            .andExpect(MockMvcResultMatchers.status().isOk)



    }

    **/
    @Test
    fun `Test add one invalid`() {

    }

    @Test
    fun `Test edit one exhibition item`() {

    }

    @Test
    fun `Test delete exhibition item`() {
        /**
        //Mockito.`when`(exhibitionItemService.getAllExhibitionItems()).thenReturn(emptyList())

        mvc.perform(MockMvcRequestBuilders.delete("${url}/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
        **/
    }

}