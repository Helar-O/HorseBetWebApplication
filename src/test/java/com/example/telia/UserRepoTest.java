package com.example.telia;

import com.example.telia.dao.*;
import com.example.telia.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepoTest {
    @Mock
    @Autowired
    private UserDao userDao;
    @Mock
    private RaceDao raceDao;
    @Mock
    private ParticipantDao participantDao;
    @Mock
    private HorseDao horseDao;
    @Mock
    private BetDao betDao;

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

    public Optional<User> findUserById(int user_id) {
        List<User> users = userDao.getAllUsers();
        return users.stream().filter(user -> user.getUser_id() == user_id).findFirst();
    }

    @Test
    public void saveAndDeleteUser() throws Exception {
        User user = userDao.addNewUser(new User("newTestUser773", "pass"));
        assert findUserById(user.getUser_id()).isPresent();
        userDao.deleteUser(user);
        assert findUserById(user.getUser_id()).isEmpty();
    }
}
