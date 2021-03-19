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
        console.printHello();
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
                console.printOptionUndefined();
        }
    }

    private void addEntry() {
        console.printEnterOriginal();
        String original = scanner.nextLine();
        console.printEnterTranslation();
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void test() {
        if (entryRepository.isEmpty()) {
            console.printAddSomePhrase();
            return;
        }
        final int testSize = entryRepository.size() > 10 ? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            console.printEntryTranslation(entry.getOriginal());
            String translation = scanner.nextLine();
            if (entry.getTranslation().equalsIgnoreCase(translation)) {
                console.printRight();
                score++;
            } else {
                console.printNotRight(entry.getTranslation());
            }
        }
        console.printScore(score, testSize);
    }

    private void close() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            console.printSavedState();
        } catch (IOException e) {
            console.printNotSavedState();
        }
        console.printBye();
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
