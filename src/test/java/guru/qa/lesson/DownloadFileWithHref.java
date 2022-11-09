package guru.qa.lesson;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class DownloadFileWithHref {

    @Test
    void selenideFileDownloadTest() throws Exception {

        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download(); // абстракция, опеределяющая путь до файла

        // Конструкция try with resources - автоматически закрывает ресурсы, указанные в круглых скобках после try
        try (InputStream inputStream = new FileInputStream(downloadedFile)) {
            byte[] fileByte = inputStream.readAllBytes();
            String fileContent = new String(fileByte, StandardCharsets.UTF_8);
            assertThat(fileContent).contains("This repository is the home of the next generation of JUnit");
        }

//        Конструкция try - finally - для закрытия ресурсов нужен блок finally
//        InputStream inputStream = new FileInputStream(downloadedFile); // класс Java, позволяет читать сами файлы
//        try {
//            byte[] fileByte = inputStream.readAllBytes(); // содержимое файла в массиве байт
//            String fileContent = new String(fileByte, StandardCharsets.UTF_8); // преобразование байт в строку
//            assertThat(fileContent)
//                    .contains("This repository is the home of the next generation of JUnit, JUnit 5."); // проверка содержимого файла
//        } finally {
//            inputStream.close(); // закрытие стрима
//        }

//        Зависимость commons-io - одной строчкой можно заменить конструкцию выше
//        String fileContent = FileUtils.readFileToString(downloadedFile, StandardCharsets.UTF_8);
    }
}
