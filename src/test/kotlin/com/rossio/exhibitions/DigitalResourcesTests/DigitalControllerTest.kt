package com.rossio.exhibitions.DigitalResourcesTests

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.rossio.exhibitions.ExhibitionTests.ExhibitionControllerTest
import com.rossio.exhibitions.dto.DigitalResourceDTO
import com.rossio.exhibitions.exception.NotFoundException
import com.rossio.exhibitions.model.DigitalResourceDAO
import com.rossio.exhibitions.service.DigitalResourceService
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
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

@SpringBootTest
@AutoConfigureMockMvc
class DigitalControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var digitalResourceService: DigitalResourceService

    companion object {
        var url = "/resource"

        val mapper = jacksonObjectMapper()

        val resourceDTO1 = DigitalResourceDTO(1L,"Nome","", "", "", "", emptyList(), "", emptyList(),"","")

        val resourceDTO2 = DigitalResourceDTO(2L,"Nome","", "","", "", emptyList(), "", emptyList(),"","")

        val resourcesList = listOf<DigitalResourceDTO>(resourceDTO1, resourceDTO2)

        val resourceDAO1 = DigitalResourceDAO(resourceDTO1)

        val resourceDAO2 = DigitalResourceDAO(resourceDTO2)

        val resourceListDAO = listOf<DigitalResourceDAO>(resourceDAO1, resourceDAO2)



    }

    @Test
    fun `Test get All Resources`(){
        Mockito.`when`(digitalResourceService.getAllDigitalResources()).thenReturn(resourceListDAO)

        val result = mvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.jsonPath("$",
                    Matchers.hasSize<Any>(resourcesList.size)
                ))
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<List<DigitalResourceDTO>>(responseString)
        MatcherAssert.assertThat(responseDTO, Matchers.equalTo(resourcesList))


    }

    @Test
    fun `Test get Resource By Id`(){
        Mockito.`when`(digitalResourceService.getOneDigitalResource(1)).thenReturn(resourceDAO1)

        val result = mvc.perform(MockMvcRequestBuilders.get("${url}/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()

        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<DigitalResourceDTO>(responseString)
        MatcherAssert.assertThat(responseDTO, Matchers.equalTo(resourceDTO1))

    }

    @Test
    fun `Test get Resource By Id (Not Found Exception)`(){
        Mockito.`when`(digitalResourceService.getOneDigitalResource(3)).thenThrow(NotFoundException("not found"))

        val result = mvc.perform(MockMvcRequestBuilders.get("${url}/3"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andReturn()
        /**
        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<DigitalResourceDTO>(responseString)
        MatcherAssert.assertThat(responseDTO, Matchers.equalTo(resourceDTO1))
        **/
    }


    @Test
    fun `Test Edit Resource`(){

    }

    @Test
    fun `Test Delete Resource`(){
        //Mockito.`when`(digitalResourceService.removeDigitalResource(1))

        val result = mvc.perform(MockMvcRequestBuilders.delete("${url}/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()

    }

    @Test
    fun `Test Delete Resource (Not Found Exception)`(){
        Mockito.`when`(digitalResourceService.removeDigitalResource(3)).thenThrow(NotFoundException("not found"))

        val result = mvc.perform(MockMvcRequestBuilders.delete("${url}/3"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
        /**
        val responseString = result.response.contentAsString
        val responseDTO = mapper.readValue<DigitalResourceDTO>(responseString)
        MatcherAssert.assertThat(responseDTO, Matchers.equalTo(resourceDTO1))
         **/
    }
}