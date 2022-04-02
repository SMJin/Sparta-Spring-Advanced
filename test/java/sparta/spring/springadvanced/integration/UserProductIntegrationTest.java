package sparta.spring.springadvanced.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sparta.spring.springadvanced.dto.ProductMypriceRequestDto;
import sparta.spring.springadvanced.dto.ProductRequestDto;
import sparta.spring.springadvanced.dto.SignupRequestDto;
import sparta.spring.springadvanced.model.Product;
import sparta.spring.springadvanced.model.User;
import sparta.spring.springadvanced.service.ProductService;
import sparta.spring.springadvanced.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserProductIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    Long userId = 100L;
    Product createdProduct = null;

    @Test
    @Order(1)
    @DisplayName("회원 가입 전 관심상품 등록(실패)")
    void test1() {
        // given
        Long userId = null;

        ProductRequestDto testProductDto = new ProductRequestDto(
                "오리온 꼬북칩 초코츄러스맛 160g",
                "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg",
                "https://search.shopping.naver.com/gate.nhn?id=24161228524",
                2350
        );


        // when - then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(testProductDto, userId);
        });

        // then
        assertEquals("회원 Id 가 유효하지 않습니다.", exception.getMessage());
    }

    @Test
    @Order(2)
    @DisplayName("회원 가입")
    void test2() {
        // given
        userId = 100L;
        String name = "Test";
        String password = "test";
        String email = "test@naver.com";
        SignupRequestDto testUserDto = new SignupRequestDto();
        testUserDto.setUsername(name);
        testUserDto.setPassword(password);
        testUserDto.setEmail(email);

        // when
        User testUser = userService.registerUser(testUserDto);

        // then
        assertNotNull(testUser.getId());
        assertEquals(name, testUser.getUsername());
        assertNotNull(testUser.getPassword());
        assertEquals(email, testUser.getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("가입된 회원으로 관심상품 등록")
    void test3() {
        // given
        String title = "오리온 꼬북칩 초코츄러스맛 160g";
        String imgUrl = "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg";
        String link = "https://search.shopping.naver.com/gate.nhn?id=24161228524";
        int lprice = 2350;
        ProductRequestDto testProductDto = new ProductRequestDto(title, imgUrl, link, lprice);

        // when
        Product testProduct = productService.createProduct(testProductDto, userId);

        // then
        assertNotNull(testProduct.getId());
        assertEquals(title, testProduct.getTitle());
        assertEquals(imgUrl, testProduct.getImage());
        assertEquals(link, testProduct.getLink());
        assertEquals(lprice, testProduct.getLprice());

        createdProduct = testProduct;
    }

    @Test
    @Order(4)
    @DisplayName("관심상품 업데이트")
    void test4() {
        // given
        int myPrice = 4500;
        ProductMypriceRequestDto testMypriceDto = new ProductMypriceRequestDto(myPrice);

        // when
        Product testProduct = productService.updateProduct(createdProduct.getId(), testMypriceDto);

        // then
        assertNotNull(testProduct.getId());
        assertEquals(myPrice, testProduct.getMyprice());

        createdProduct.setMyprice(myPrice);
    }

    @Test
    @Order(5)
    @DisplayName("관심상품 조회")
    void test5() {
        // given - when
        List<Product> testList = productService.getProducts(userId);
        Product testProduct = null;
        for (int i=0; i<testList.size(); i++) {
            if (testList.get(i).getId().equals(createdProduct.getId())) {
                testProduct = testList.get(i);
            }
        }

        // then
        assertNotNull(testProduct);
    }
}
