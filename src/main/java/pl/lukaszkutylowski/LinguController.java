package pl.lukaszkutylowski;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszkutylowski.console.ConsoleOutputWriter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class LinguController {
    private static final int UNDEFINED = -1;
    private static final int ADD_ENTRY = 0;
    private static final int TEST = 1;
    private static final int CLOSE_APP = 2;

    private final EntryRepository entryRepository;
    private final FileService fileService;
    private final ConsoleOutputWriter console;
    private final Scanner scanner;

    void mainLoop() {
        console.print("Witaj w aplikacji LinguApp");
        int option = UNDEFINED;
        while (option != CLOSE_APP) {
            printMenu();
            option = chooseOption();
            executeOption(option);
        }
    }

    private void executeOption(int option) {
        switch (option) {
            case ADD_ENTRY:
                addEntry();
                break;
            case TEST:
                test();
                break;
            case CLOSE_APP:
                close();
                break;
            default:
                console.print("Opcja niezdefiniowana");
        }
    }

    private void addEntry() {
        console.print("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        console.print("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void test() {
        if (entryRepository.isEmpty()) {
            console.print("Dodaj przynajmniej jedną frazę do bazy");
            return;
        }
        final int testSize = entryRepository.size() > 10 ? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            console.print("Podaj tłumaczenie dla: " + entry.getOriginal());
            String translation = scanner.nextLine();
            if (entry.getTranslation().equalsIgnoreCase(translation)) {
                console.print("Odpowiedź poprawna");
                score++;
            } else {
                console.print("Odpowiedź niepoprawna - " + entry.getTranslation());
            }
        }
        console.print("Twój wynik to: " + score + "/" + testSize);
    }

    private void close() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            console.print("Zapisano stan aplikacji");
        } catch (IOException e) {
            console.print("Nie udało sie zapisać zmian");
        }
        console.print("Bye bye");
    }

    private void printMenu() {
        console.printMenu();
    }

    private int chooseOption() {
        int option;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            option = UNDEFINED;
        } finally {
            scanner.nextLine();
        }
        if (option > UNDEFINED && option <= CLOSE_APP)
            return option;
        else
            return UNDEFINED;
    }
}
