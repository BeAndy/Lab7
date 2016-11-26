import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 11/26/2016.
 */
public class Runner {

    public static void main(String[] args) {
        List<String> listStrings = getStringList("input.txt");
        try {
            getNames(listStrings);
            getNumbers(listStrings);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    public static List<String> getStringList(String way) {
        List<String> list = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(way)));
            String currentDirectory = reader.readLine() + " ";
            while (currentDirectory != null) {
                list.add(currentDirectory);
                currentDirectory = reader.readLine();
            }
            reader.close();
        } catch (IOException exception) {
            exception.getLocalizedMessage();
        }
        return list;
    }

    private static void writeData(String way, Iterator<String> iterator) {
        try {
            FileWriter writer = new FileWriter(new File(way));
            while (iterator.hasNext()) {
                writer.write(iterator.next());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException exception) {
            exception.getLocalizedMessage();
        }
    }

    private static List<String> getNames(List<String> listStrings) throws Exception {
        List<String> names = new LinkedList<>();
        try {
            Pattern patternSecondTask = Pattern.compile("[^\\d\\s.,]+");
            Iterator<String> listIterator = listStrings.iterator();
            while (listIterator.hasNext()) {
                Matcher matcherSecondTask = patternSecondTask.matcher(listIterator.next());
                while (matcherSecondTask.find()) {
                    String word = matcherSecondTask.group();
                    names.add(word);
                }
            }
            Collections.sort(names, (first, second) -> (first.compareTo(second)));
            System.out.println("Number of elements in the first task: " + names.size());
            for (String s : names) {
                System.out.println(s);
            }
            writeData("output2.txt", names.iterator());
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return names;
    }

    private static List<String> getNumbers(List<String> setOfStrings) throws Exception {
        List<String> numbers = new LinkedList<>();
        try {
            Pattern patternFirstTask = Pattern.compile("[\\d]+[\\s.,]");
            Iterator<String> listIterator = setOfStrings.iterator();
            while (listIterator.hasNext()) {
                Matcher matcherFirstTask = patternFirstTask.matcher(listIterator.next());
                while (matcherFirstTask.find()) {
                    String word = matcherFirstTask.group();
                    numbers.add(word);
                }
            }
            Collections.sort(numbers, (first, second) -> (Double.parseDouble(first) > Double.parseDouble(second) ? 1 : 0));
            System.out.println("Number of elements in the second task: " + numbers.size());
            writeData("output1.txt", numbers.iterator());
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return numbers;
    }


}
