package com.rossio.exhibitions.ExhibitionTests

import com.rossio.exhibitions.model.ExhibitionsRepository
import com.rossio.exhibitions.service.ExhibitionService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class ExhibitionServiceTest {

    @Autowired
    lateinit var exhibitionService: ExhibitionService

    @MockBean
    lateinit var exhibitionsRepository: ExhibitionsRepository

    companion object {


    }

    @Test
    fun `get All Exhitions`() {
        Mockito.`when`(exhibitionsRepository.findAll()).thenReturn(emptyList())

        assertThat(exhibitionService.getAllExhibitions(), equalTo(emptyList()))

    }

    @Test
    fun `get One Exhition`() {

    }


}