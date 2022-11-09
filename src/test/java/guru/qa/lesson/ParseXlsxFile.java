package guru.qa.lesson;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseXlsxFile {
    // Если в зависимостях есть две библиотеки - pdf и xls, то при запуске теста может возникнуть ошибка из-за
    // зависимости org.apache.poi. Чтобы исправить, нужно поменять версию xls

    ClassLoader classLoader = ParseXlsxFile.class.getClassLoader(); // позволяет брать файлы из resources

    @Test
    void xlsxTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("testXlsx.xlsx")) {

            XLS xlsxContent = new XLS(inputStream);
            assertThat(
                    xlsxContent.excel.getSheetAt(0)
                            .getRow(1)
                            .getCell(1)
                            .getStringCellValue()
            ).isEqualTo("Jane");
        }
    }
}
