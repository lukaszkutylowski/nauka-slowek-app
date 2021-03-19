package pl.lukaszkutylowski;

import lombok.val;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    private String fileName = "data.csv";

    public List<Entry> readAllFile() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(CsvEntryConverter::parse)
                .collect(Collectors.toList());
    }

    void saveEntries(List<Entry> entries) throws IOException {
        val writer = new BufferedWriter(new FileWriter(fileName));
        for (Entry entry : entries) {
            writer.write(entry.getOriginal() + ";" + entry.getTranslation());
            writer.newLine();
        }
        writer.close();
    }
}
