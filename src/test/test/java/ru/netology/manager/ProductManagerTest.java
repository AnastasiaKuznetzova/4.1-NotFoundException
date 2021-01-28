package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product product1 = new Book(1, "First", 400,"AA");
    Product product2 = new Book(2,"Second", 350, "BB");
    Product product3 = new Smartphone(3,"Smart", 20000, "Sony");
    Product product4 = new Smartphone(4, "Phone", 50000, "Apple");


    @BeforeEach
    void setUp() {
        manager.addProduct(product1);
        manager.addProduct(product2);
        manager.addProduct(product3);
        manager.addProduct(product4);

    }


    @Test
    void shouldSearchByBookName() {
        Product[] expected = {new Book(1, "First", 400,"AA")};
        Product[] actual = manager.searchBy("First");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByBookAuthor() {
        Product[] expected = {new Book(2,"Second", 350, "BB")};
        Product[] actual = manager.searchBy ("BB");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemovieById() {
        repository.removeById(4);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{product1, product2, product3};
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldSearchBySmartphoneName() {
        Product[] expected = {new Smartphone(3,"Smart", 20000, "Sony")};
        Product[] actual = manager.searchBy("Smart");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmartphoneManufacturer() {

        Product[] expected = {new Smartphone(4, "Phone", 50000, "Apple")};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldSearchNothing(){
        Product[] expected = new Product[0];
        Product[] actual = new Product[0];
        assertArrayEquals(expected, actual);

    }

}