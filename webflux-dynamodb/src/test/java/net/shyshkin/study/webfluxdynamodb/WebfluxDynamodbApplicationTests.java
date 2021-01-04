package net.shyshkin.study.webfluxdynamodb;

import net.shyshkin.study.webfluxdynamodb.extensions.DynamoDBLocalServerExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(DynamoDBLocalServerExtension.class)
class WebfluxDynamodbApplicationTests {

    @Test
    void contextLoads() {
    }

}
