package com.target.myretail.controller

import com.target.myretail.model.PriceRow
import com.target.myretail.model.Product
import com.target.myretail.model.StorePriceRequestBody
import com.target.myretail.service.ProductDetailsService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class RetailControllerTest {

    @MockBean lateinit var mockDetailsService: ProductDetailsService

    @Autowired lateinit var controller: RetailController

    val product: Product = Product(PRODUCT_ID, PRODUCT_NAME, Pair(PRICE, CURRENCY_CODE))
    val row: PriceRow = PriceRow(PRODUCT_ID, PRICE, CURRENCY_CODE)
    val requestBody: StorePriceRequestBody = StorePriceRequestBody(PRICE, CURRENCY_CODE)

    // Returns Mockito.any() as nullable type
    fun <T> any(): T = Mockito.any<T>()

    @Before fun setup() = initMocks(this)

    @Test
    fun `should return response with 200ok and body for getProductDetailsById`() {
        `when`(mockDetailsService.hydrateProductDetails(any())).thenReturn(product)
        val response = controller.getProductDetailsById(PRODUCT_ID)
        assert(response.statusCode.is2xxSuccessful)
        assert(response.hasBody())
    }

    @Test
    fun `should return response with 200ok and body for `() {
        `when`(mockDetailsService.storeProductPrice(any(), any())).thenReturn(row)
        val response = controller.saveProductPrice(PRODUCT_ID, requestBody)
        assert(response.statusCode.is2xxSuccessful)
        assert(response.hasBody())
    }

    private companion object { // Test values, stored here for consistency
        const val PRODUCT_ID = "00000000"
        const val PRODUCT_NAME = "myProduct"
        const val PRICE = 20.0
        const val CURRENCY_CODE = "USD"
    }
}
