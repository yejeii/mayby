package com.mayby;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class MaybyApplicationTests {

    @Test
    @GetMapping("/index")
    void contextLoads() {

    }

}
