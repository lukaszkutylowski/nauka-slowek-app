package pl.lukaszkutylowski.console;

import org.springframework.stereotype.Service;

@Service
public class ConsoleOutputWriter {

    private void print(String s) {
        System.out.println(s);
    }

    public void printHello() {
        print("Witaj w aplikacji LinguApp");
    }

    public void printOptionUndefined() {
        print("Opcja niezdefiniowana");
    }

    public void printMenu() {
        print("Wybierz opcję:");
        print("0 - dodaj frazę");
        print("1 - Test");
        print("2 - Koniec programu");
    }

    public void printBye() {
        print("Bye bye");
    }

    public void printSavedState() {
        print("Zapisano stan aplikacji");
    }

    public void printNotSavedState() {
        print("Nie udało sie zapisać zmian");
    }

    public void printEnterOriginal() {
        print("Podaj oryginalną frazę");
    }

    public void printEnterTranslation() {
        print("Podaj tłumaczenie");
    }

    public void printAddSomePhrase() {
        print("Dodaj przynajmniej jedną frazę do bazy");
    }

    public void printEntryTranslation(String original) {
        print("Podaj tłumaczenie dla: " + original);
    }

    public void printRight() {
        print("Odpowiedź poprawna");
    }

    public void printNotRight(String translation) {
        print("Odpowiedź niepoprawna - " + translation);
    }

    public void printScore(int score, int testSize) {
        print("Twój wynik to: " + score + "/" + testSize);
    }
}
