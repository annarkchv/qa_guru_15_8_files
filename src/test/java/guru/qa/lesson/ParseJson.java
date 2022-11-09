package guru.qa.lesson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import guru.qa.lesson.jsonmodel.User;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseJson {

    ClassLoader classLoader = ParseJson.class.getClassLoader();

    // Gson
    @Test
    void jsonTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("userTest.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(inputStreamReader, JsonObject.class);
            // Так обычно не делают. Нужно использовать модели
            assertThat(jsonObject.get("firstName").getAsString()).isEqualTo("Jane");
            assertThat(jsonObject.get("isAdult").getAsBoolean()).isTrue();
            assertThat(jsonObject.get("contacts").getAsJsonObject()
                    .get("phoneNumber").getAsString()).isEqualTo("89998887766");
        }
    }

    @Test
    void jsonTestWithModel() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("userTest.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            User user = gson.fromJson(inputStreamReader, User.class);
            // Модель. Описано в guru.qa.lesson.jsonmodel
            assertThat(user.firstName).isEqualTo("Jane");
            assertThat(user.isAdult).isTrue();
            assertThat(user.contacts.phoneNumber).isEqualTo("89998887766");
        }
    }
}
