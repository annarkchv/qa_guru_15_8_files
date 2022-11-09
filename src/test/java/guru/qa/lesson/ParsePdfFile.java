package guru.qa.lesson;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class ParsePdfFile {

    @Test
    void pdfTest() throws Exception {

        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadedPdf = $("a[href*='junit-user-guide-5.9.1.pdf']").download();
        PDF pdfContent = new PDF(downloadedPdf);
        // У библиотеки pdf есть свои ассерты, описание в pdf test github
        assertThat(pdfContent.author).contains("Sam Brannen");
    }
}
