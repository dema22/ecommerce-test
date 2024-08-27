package com.example.ecommerce;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CartRepository cartRepository;

	@BeforeEach
	void setup() {
		// Clear the repository before each test
		cartRepository.deleteById("1");
	}

	@Test
	void testCreateCart() throws Exception {
		mockMvc.perform(post("/api/carts")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	void testAddProducts() throws Exception {
		Cart cart = new Cart();
		cartRepository.save(cart);

		String productJson = "[{\"id\": 1, \"description\": \"Sample product\", \"amount\": 10.0}]";

		// Perform the mock request using the Cart ID
		mockMvc.perform(post("/api/carts/" + cart.getId() + "/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productJson))
				.andExpect(status().isOk());
	}

	@Test
	void testGetCart() throws Exception {
		Cart cart = new Cart();
		cartRepository.save(cart);

		mockMvc.perform(get("/api/carts/" + cart.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteCart() throws Exception {
		Cart cart = new Cart();
		cartRepository.save(cart);

		mockMvc.perform(delete("/api/carts/" + cart.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testAddProductsToNonExistentCart() throws Exception {
		String productJson = "[{\"id\": 1, \"description\": \"Sample product\", \"amount\": 10.0}]";

		mockMvc.perform(post("/api/carts/999/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productJson))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetCartByNonExistentId() throws Exception {
		mockMvc.perform(get("/api/carts/999")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testDeleteCartWithInvalidId() throws Exception {
		mockMvc.perform(delete("/api/carts/999")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
