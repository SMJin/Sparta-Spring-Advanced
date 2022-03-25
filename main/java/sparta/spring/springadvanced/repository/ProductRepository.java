package sparta.spring.springadvanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.spring.springadvanced.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { }