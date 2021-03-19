package pl.lukaszkutylowski;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class EntryRepository {
    private List<Entry> entries;

    @Autowired
    public EntryRepository() {
        val fileService = new FileService();
        try {
            this.entries = fileService.readAllFile();
        } catch (IOException e) {
            entries = new ArrayList<>();
        }
    }

    public List<Entry> getAll() {
        return entries;
    }

    public Set<Entry> getRandomEntries(int number) {
        val random = new Random();
        val randomEntries = new HashSet<Entry>();
        while (randomEntries.size() < number && randomEntries.size() < entries.size()) {
            while (randomEntries.size() < number && randomEntries.size() < entries.size()) {
                randomEntries.add(entries.get(random.nextInt(entries.size())));
            }
        }
        return randomEntries;
    }

    public void add(Entry entry) {
        entries.add(entry);
    }

    public int size() {
        return entries.size();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }
}
