package com.example;

import com.example.repository.PersonRepository;
import com.example.domain.Person;
import io.micronaut.cache.CacheManager;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RedisTestContainerExtension.class)
@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CacheTest {
    @Inject
    private PersonRepository repository;

    @Inject
    private CacheManager cacheManager;

    public static Person createEntity() {
        Person person = new Person();
        person.setName("jhon");
        person.setCustomerNo("123456");
        person.setConfigId("1");
        person.setAuthenticated(true);
        return person;
    }

    @BeforeAll
    public void initTest() {
        Person person = createEntity();
        repository.save(person);
    }

    @AfterAll
    public void cleanupTest() {
        repository.deleteAll();
    }

    @BeforeEach
    public void setup() {
        cacheManager.getCache(Person.class.getName()).invalidateAll();
    }

    @Test
    public void findById() {
        long id = 1L;
        repository.findById(id);
        repository.findById(id);
    }
}
