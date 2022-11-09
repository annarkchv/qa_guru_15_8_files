package guru.qa.lesson;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseCsvFile {
    ClassLoader classLoader = ParseCsvFile.class.getClassLoader();

    @Test
    void csvTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("testCsv.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) { // закрытие двух стримов

            List<String[]> csvContent = csvReader.readAll();
            String[] secondRow = csvContent.get(1); // получение второй строки по индексу
            assertThat(secondRow[1]).isEqualTo("Jane");
            assertThat(secondRow[2]).isEqualTo("Doe");
        }
    }
}
