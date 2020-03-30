package com.target.myretail.controller

import com.target.myretail.dao.PriceDao
import com.target.myretail.dao.RedSkyDao
import com.target.myretail.model.PriceRow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class RetailControllerIntTest {

    @MockBean lateinit var mockPriceDao: PriceDao
    @MockBean lateinit var mockRedSkyDao: RedSkyDao

    @Autowired lateinit var mockMvc: MockMvc

    val row: PriceRow = PriceRow(PRODUCT_ID, PRICE, CURRENCY_CODE)

    @Before fun setup() = initMocks(this)

    @Test
    fun `should return results for getProductDetailsById`() {
        `when`(mockPriceDao.getProductPriceAndCurrencyById(PRODUCT_ID)).thenReturn(row)
        `when`(mockRedSkyDao.getProductNameById(PRODUCT_ID)).thenReturn(PRODUCT_NAME)
        mockMvc.perform(get("/products/$PRODUCT_ID"))
            .andExpect(status().is2xxSuccessful)
            .andExpect(jsonPath("id").value(PRODUCT_ID))
            .andExpect(jsonPath("name").value(PRODUCT_NAME))
    }

    @Test
    fun `should return results for saveProductPrice`() {
        `when`(mockPriceDao.saveProductPriceAndCurrencyForId(PRODUCT_ID, PRICE, CURRENCY_CODE)).thenReturn(row)
        mockMvc.perform(put("/products/$PRODUCT_ID")
            .content(STORE_PRICE_REQ_BODY)
            .contentType(APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful)
            .andExpect(jsonPath("productId").value(PRODUCT_ID))
            .andExpect(jsonPath("value").value(PRICE))
            .andExpect(jsonPath("currencyCode").value(CURRENCY_CODE))
    }

    private companion object { // Test values, stored here for consistency
        const val PRODUCT_ID = "00000000"
        const val PRODUCT_NAME = "myProduct"
        const val PRICE = 20.0
        const val CURRENCY_CODE = "USD"
        const val STORE_PRICE_REQ_BODY = "{\"value\":$PRICE,\"currency_code\":\"$CURRENCY_CODE\"}"
    }
}
