package com.bigbank.stocktracker.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import com.bigbank.stocktracker.model.Stock;
import com.bigbank.stocktracker.model.User;
import com.bigbank.stocktracker.model.VantageOutPut;
import com.bigbank.stocktracker.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@Mock
	StockTrackerService stockTrackerService;
	
	@Disabled
	@Test
	@WithMockUser(username = "test", password = "test", roles = "USER")
	public void shouldAddStockToFavorites() {
		VantageOutPut vantageOutPut = new VantageOutPut(new Stock("TSLA", 900));
		User user = new User("admin", "password");
		
		// null pointer when trying to access vantageOutput - haven't been able properly mock it
	//	when(stockTrackerService.getStock(anyString()).then(Mono.just(vantageOutPut)));
	//	when(stockTrackerService.getStock(anyString()).thenReturn(Mono.just(vantageOutPut).subscribe()));
		when(stockTrackerService.getStock(anyString()).block()).thenReturn(vantageOutPut);
		

		
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		
		List<Stock> userFavourites = userService.addStockToFavourites("TSLA");
		
	//	assertEquals("TSLA", userFavourites.get(0).getSymbol());
	}
	
	@Test
	@WithMockUser(username = "test", password = "test", roles = "USER")
	public void shouldRemoveStockFromFavourites() {
		User user = new User("admin", "password");
		List<Stock> stockFavourites = new ArrayList<>();
		stockFavourites.add(new Stock("TSLA", 900));
		user.setStockFavourites(stockFavourites);
		
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		
		List<Stock> userFavourites = userService.removeStockFromFavourites("TSLA");
		assertEquals(0, userFavourites.size());
	}
	


}
