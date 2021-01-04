package net.shyshkin.study.dynamodbspringdata;

import net.shyshkin.study.dynamodbspringdata.extensions.DynamoDBServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(DynamoDBServer.class)
class DynamodbSpringDataApplicationTests {

    @Test
    void contextLoads() {
    }

}
