package guru.qa.lesson;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ParseZipArchive {

    ClassLoader classLoader = ParseZipArchive.class.getClassLoader();

    @Test
    void zipTest() throws Exception {

        try (InputStream inputStream = classLoader.getResourceAsStream("testFiles.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

            ZipEntry zipEntry;
            // В переменную zipEntry присваивается файл из архива, цикл завершается, когда файлов в архиве больше нет
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String fileName = zipEntry.getName();
            }
        }
    }
}
