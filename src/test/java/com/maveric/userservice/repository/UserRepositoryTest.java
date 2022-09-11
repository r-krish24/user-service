package com.maveric.userservice.repository;
import com.maveric.userservice.model.User;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static com.maveric.userservice.UserServiceApplicationTests.getUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testFindById() {
        User user = getUser();
        repository.save(user);
        User result = repository.findById(user.get_id()).get();
        assertEquals(user.get_id(), result.get_id());
    }
    @Test
    public void testFindAll() {
        User user = getUser();
        repository.save(user);
        List<User> result = new ArrayList<>();
        repository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 1);
    }
    @Test
    public void testSave() {
        User user = getUser();
        repository.save(user);
        User found = repository.findById(user.get_id()).get();
        assertEquals(user.get_id(), found.get_id());
    }
    @Test
    public void testDeleteById() {
        User user = getUser();
        repository.save(user);
        repository.deleteById(user.get_id());
        List<User> result = new ArrayList<>();
        repository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }
}