import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.jpa.UserRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;

    @Test
    public void findAllTest() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(users.size(), 2);
    }

}
