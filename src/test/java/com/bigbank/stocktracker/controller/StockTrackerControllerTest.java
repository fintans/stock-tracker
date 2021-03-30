//package com.bigbank.stocktracker.controller;
//
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.mockito.Matchers.anyString;
//
//import com.bigbank.stocktracker.model.Stock;
//import com.bigbank.stocktracker.model.User;
//import com.bigbank.stocktracker.model.VantageOutPut;
//import com.bigbank.stocktracker.service.CustomUserDetailsService;
//import com.bigbank.stocktracker.service.StockTrackerService;
//
//import reactor.core.publisher.Mono;
//
//public class StockTrackerControllerTest {
//	
//	Logger logger = LogManager.getLogger(StockTrackerControllerTest.class);
//
//	@Autowired
//	private MockMvc mvc;
//
//	@MockBean
//	CustomUserDetailsService userDetailsService;
//
//	@MockBean
//	StockTrackerService stockTrackerService;
//
//	@Test
//    public void whenSearchForStockAssertStockJsonIsReturned() throws Exception {
//    	User user = new User("admin", "password");
//    	
//    	Mono<VantageOutPut> vantageOutPut = new VantageOutPut(new Stock("nio", 40));
//    	when(stockTrackerService.getStock(anyString())).thenReturn(vantageOutPut);
//    	
//        this.mvc.perform(post("/api/users/register")
//        	      .contentType(MediaType.APPLICATION_JSON)
//        	      .content("{\"username\": \"admin\",\"password\": \"pass\"}"))
//        	      .andExpect(status().isOk())
//        	      .andExpect(jsonPath("$.username", is("admin")));
//    }
//
//}
