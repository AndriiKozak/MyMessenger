package com.mycompany.mymessanger;

import javax.sql.DataSource;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMessangerApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() {
        assertNotNull(dataSource);
    }

}
