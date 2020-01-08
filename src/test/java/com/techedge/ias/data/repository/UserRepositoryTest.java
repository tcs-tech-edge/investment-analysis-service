package com.techedge.ias.data.repository;

import com.techedge.ias.data.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserPersistance() {
        User user = new User("Murugesh", "Kumar");
        testEntityManager.persist(user);
        testEntityManager.flush();

        User userFromDB = userRepository.findById(1).orElse(null);

        assertNotNull(userFromDB);
        assertEquals("Murugesh", userFromDB.getFirstName());
        assertEquals("Kumar", userFromDB.getLastName());
        assertEquals(1, userFromDB.getId());

    }


}
