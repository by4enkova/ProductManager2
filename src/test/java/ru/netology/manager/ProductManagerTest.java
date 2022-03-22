package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "TwentyYearsAfter", 100, "Dumas");
    Product second = new Book(2, "OliverTwist", 150, "Dickens");
    Product third = new Book(3, "TheSunAlsoRises", 200, "Hemingway");
    Product fourth = new Book(4, "It", 250, "King");
    Product fifth = new Smartphone(5, "AFifth", 300, "MobileInt1");
    Product sixth = new Smartphone(6, "ASixth", 400, "MobileInt2");
    Product seventh = new Smartphone(7, "ASeventh", 500, "MobileInt3");
    Product eighth = new Smartphone(8, "AEighth", 600, "MobileInt3");

    @BeforeEach
    void setUp(){
        manager.addProduct(first);
        manager.addProduct(second);
        manager.addProduct(third);
        manager.addProduct(fourth);
        manager.addProduct(fifth);
        manager.addProduct(sixth);
        manager.addProduct(seventh);
        manager.addProduct(eighth);
    }

    @Test
    void shouldFindByNameOfTheBook(){
        Product[] actual = manager.searchBy("It");
        Product[] expected = {new Book(4, "It",250,"King")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByAuthorOfTheBook(){
        Product[] actual = manager.searchBy("Dumas");
        Product[] expected = {new Book(1, "TwentyYearsAfter",100,"Dumas")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByNameOfTheSmartphone(){
        Product[] actual = manager.searchBy("AFifth");
        Product[] expected = {new Smartphone(5, "AFifth",300,"MobileInt1")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByCreatorOfTheSmartphone(){
        Product[] actual = manager.searchBy("MobileInt2");
        Product[] expected = {new Smartphone(6, "ASixth",400,"MobileInt2")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById(){
        repository.removeById(7);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth, fifth, sixth, eighth};
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldNotFindInvalidAuthor(){
        Product[] actual = manager.searchBy("Kristie");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindByInvalidNameOfTheBook(){
        Product[] actual = manager.searchBy("Theatre");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindByInvalidCreatorOfTheSmartphone(){
        Product[] actual = manager.searchBy("MobileInt9");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindTheSmartphonesByTheirCreator(){
        String search = "MobileInt3";
        Product[] actual = manager.searchBy(search);
        Product[] expected = new Product[]{seventh, eighth};
        assertArrayEquals(expected, actual);
    }

}

