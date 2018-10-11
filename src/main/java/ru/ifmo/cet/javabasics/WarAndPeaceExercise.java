package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
        List<String> lines12 = Files.readAllLines(tome12Path, charset);
        List<String> lines34 = Files.readAllLines(tome34Path, charset);
        List<String> lines = new LinkedList<String>(lines12);
        lines.addAll(lines34);
        Map<String, Integer> wmap = new TreeMap<>();
        String stringlist = lines.toString();
        stringlist = stringlist.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase();
       /* lines = lines.stream()
                .map(str -> str.toLowerCase())
                .map(str -> str.replaceAll("[^а-яА-Яa-zA-Z]", " "))
                .collect(Collectors.toList());
                */
        Stream<String> stringStream = Stream.of(stringlist.split(" "));
        stringStream
                .filter(word -> word.length() >= 4)
                .forEach(word -> {
                    wmap.put(word, wmap.getOrDefault(word, 0) + 1);
                });
        Comparator<Map.Entry<String, Integer>> comp = ((pair1, pair2) ->
                (pair1.getValue().equals(pair2.getValue())) ?
                        (pair1.getKey().compareTo(pair2.getKey())) : (pair2.getValue().compareTo(pair1.getValue())));
        String result = wmap.entrySet().stream()
                .filter(pair -> pair.getValue() >= 10)
                .sorted(comp)
                .map(pair -> pair.getKey() + " - " + pair.getValue())
                .collect(Collectors.joining("\n"));
        return result;
    }
}