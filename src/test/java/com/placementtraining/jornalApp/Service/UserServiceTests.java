package com.placementtraining.jornalApp.Service;
import com.placementtraining.jornalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserRepository userRepository;
    @Test
    public void testAdd(){
        assertEquals(4,2+1);
    }
//    @Test if we use parameterixed then this is not used
    @ParameterizedTest
    @CsvSource({
            "amit",
            "vivek",
            "dev"
    })
    public void testFindbyUsername(String name){

        assertNotNull(userRepository.findByUsername(name));
    }



}
