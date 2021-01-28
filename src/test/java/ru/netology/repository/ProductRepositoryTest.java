package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product product1 = new Book(1, "First", 400, "AA");
    Product product2 = new Book(2, "Second", 350, "BB");
    Product product3 = new Smartphone(3, "Smart", 20000, "Sony");
    Product product4 = new Smartphone(4, "Phone", 50000, "Apple");


    @BeforeEach
    void setUp() {
        manager.addProduct(product1);
        manager.addProduct(product2);
        manager.addProduct(product3);
        manager.addProduct(product4);

    }


    @Test
    void shouldRemoveById() {
        repository.removeById(4);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{product1, product2, product3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByNotExceptionId() {

        try {
            repository.removeById(5);
        }
        catch (NotFoundException e){
           System.err.println(e);
       }
        assertThrows(NotFoundException.class, () -> repository.removeById(1234));
    }
}


