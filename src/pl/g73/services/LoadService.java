package pl.g73.services;

import pl.g73.sortservice.PBDataBase;
import pl.g73.sortservice.PhoneBookRecord;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadService {

    public static String[] loadDirectoryDataArray() throws IOException {
        String directoryDataString =
                new String(Files.readAllBytes(Paths.get("directory.txt"))).replaceAll("(\\d+ )", "");
        return directoryDataString.split("\r\n");
    }

    public static String[] loadFindDataArray() throws IOException {
        String findDataString = new String(Files.readAllBytes(Paths.get("find.txt")));
        return findDataString.split("\r\n");
    }

    public static void loadDirectoryDataArrayHashMap(PBDataBase pbDataBase) throws IOException {
        String[] directoryDataString =
                new String(Files.readAllBytes(Paths.get("directory.txt"))).split("\r\n");

        Pattern pattern = Pattern.compile("(\\d*) (.*)", Pattern.MULTILINE);
        Matcher matcher;

        for (String s : directoryDataString) {
            matcher = pattern.matcher(s);
            while (matcher.find()) {
                pbDataBase.put(new PhoneBookRecord(matcher.group(2), matcher.group(1)));
            }
        }
    }

    public static void loadDirectoryDataArrayHashMap2(PBDataBase pbDataBase) throws IOException {
        String[] directoryDataString =
                new String(Files.readAllBytes(Paths.get("directory.txt"))).split("\r\n");

        for (String s : directoryDataString) {
            pbDataBase.put(new PhoneBookRecord(s.substring(s.indexOf(' ')+1), s.substring(0,s.indexOf(' '))));
        }
    }



}