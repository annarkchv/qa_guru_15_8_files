package guru.qa.lesson;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class DownloadFileWithoutHref {

    // Позволяет скачивать файлы после нажатия на кнопку, даже если нет href
    static {
        Configuration.fileDownload = FileDownloadMode.PROXY;
    }

    @Test
    void selenideFileDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();

        try (InputStream inputStream = new FileInputStream(downloadedFile)) {
            byte[] fileByte = inputStream.readAllBytes();
            String fileContent = new String(fileByte, StandardCharsets.UTF_8);
            assertThat(fileContent).contains("This repository is the home of the next generation of JUnit");
        }
    }
}
