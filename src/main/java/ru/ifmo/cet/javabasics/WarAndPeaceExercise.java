package ru.ifmo.cet.javabasics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
        List<String> lines12 = Files.readAllLines(tome12Path, charset);
        List<String> lines34 = Files.readAllLines(tome34Path, charset);
        List<String> lines = new LinkedList<String>(lines12);
        Map<String, Integer> wmap = new HashMap<>();
        lines.addAll(lines34);
        for(String line : lines){
            line = line.replaceAll("[^a-zA-Zа-яА-Я]", " ");
            for(String word : line.split(" ")){
                if(word.length() >= 4){
                    if(wmap.containsKey(word.toLowerCase())){
                        wmap.put(word.toLowerCase(), wmap.get(word.toLowerCase()) + 1);
                    }
                    else{
                        wmap.put(word.toLowerCase(), 1);
                    }
                }
            }
        }
        Set<Map.Entry<String, Integer>> set = wmap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare( Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2 )
            {
                if(entry1.getValue().equals(entry2.getValue())){
                    return(entry1.getKey()).compareTo(entry2.getKey());
                }
                else{
                    return (entry2.getValue()).compareTo(entry1.getValue());
                }
            }
        } );
        String result = "";
        for(Map.Entry<String, Integer> pair : list){
            if(pair.getValue() < 10) continue;
            result += (pair.getKey() + " - " + pair.getValue() + "\n");
        }
        return result.substring(0, result.length() - 1);


    }
}