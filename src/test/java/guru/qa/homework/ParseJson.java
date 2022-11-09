package guru.qa.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.homework.jsonmodel.User;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseJson {

    ClassLoader classLoader = ParseJson.class.getClassLoader();

    @Test
    void jsonTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("userTest.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(inputStreamReader, User.class);

            assertThat(user.firstName).isEqualTo("Jane");
            assertThat(user.isAdult).isTrue();
            assertThat(user.socialNetworks.get(0)).isEqualTo("Instagram");
            assertThat(user.contacts.email).isEqualTo("janedoe@test.com");
        }
    }
}
