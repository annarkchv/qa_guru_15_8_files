package guru.qa.lesson;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UploadFile {

    @Test
    void uploadFile() throws Exception {
        open("https://fineuploader.com/demos.html");
        // $("input[type = 'file']") - запись валидна для любого загрузчика файлов
        $("input[type = 'file']").uploadFromClasspath("cat.jpg");
        $("div.qq-file-info").shouldHave(Condition.text("cat.jpg"));


    }
}
