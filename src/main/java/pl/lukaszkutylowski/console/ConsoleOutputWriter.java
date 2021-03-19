package pl.lukaszkutylowski.console;

import org.springframework.stereotype.Service;

@Service
public class ConsoleOutputWriter {

    public void print(String s) {
        System.out.println(s);
    }

    public void printMenu() {
        print("Wybierz opcję:");
        print("0 - dodaj frazę");
        print("1 - Test");
        print("2 - Koniec programu");
    }
}
