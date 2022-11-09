package guru.qa.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseZipArchive {
    ClassLoader classLoader = ParseZipArchive.class.getClassLoader();

    @Test
    void csvTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("testFiles.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".csv")) {
                    try (InputStream inputStreamCsv = classLoader.getResourceAsStream(zipEntry.getName());
                         CSVReader csvReader = new CSVReader(new InputStreamReader(inputStreamCsv))) {

                        List<String[]> csvContent = csvReader.readAll();
                        String[] firstUserInTable = csvContent.get(1);
                        assertThat(firstUserInTable[0]).isEqualTo("1");
                        assertThat(firstUserInTable[1]).isEqualTo("Jane");
                    }
                }
            }
        }
    }

    @Test
    void pdfTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("testFiles.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".pdf")) {
                    try (InputStream inputStreamPdf = classLoader.getResourceAsStream(zipEntry.getName())) {

                        PDF pdfContent = new PDF(inputStreamPdf);
                        assertThat(pdfContent.author).isEqualTo("Anna Rakchaeva");
                        assertThat(pdfContent.text).startsWith("Lorem ipsum dolor sit amet");
                    }
                }
            }
        }
    }

    @Test
    void xlsTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("testFiles.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".xlsx")) {
                    try (InputStream inputStreamXlsx = classLoader.getResourceAsStream("testXlsx.xlsx")) {

                        XLS xlsxContent = new XLS(inputStreamXlsx);
                        assertThat(
                                xlsxContent.excel.getSheetAt(0)
                                        .getRow(1)
                                        .getCell(1)
                                        .getStringCellValue()
                        ).isEqualTo("Jane");
                        assertThat(
                                xlsxContent.excel.getSheetAt(0)
                                        .getRow(1)
                                        .getCell(2)
                                        .getStringCellValue()
                        ).isEqualTo("Doe");
                    }
                }
            }
        }
    }
}
