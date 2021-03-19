package pl.lukaszkutylowski;

import lombok.val;

public class CsvEntryConverter {
    public static Entry parse(String text) {
        val split = text.split(";");
        return new Entry(split[0], split[1]);
    }
}
