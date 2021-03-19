package pl.lukaszkutylowski.console;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.lukaszkutylowski.formatter.TextFormatter;

@Service
@RequiredArgsConstructor
public class ConsoleOutputWriter {

    private final TextFormatter textFormatter;

    public void print(String s) {
        val formattedText = textFormatter.format(s);
        System.out.println(formattedText);
    }

    public void printMenu() {
        print("Wybierz opcję:");
        print("0 - dodaj frazę");
        print("1 - Test");
        print("2 - Koniec programu");
    }
}
