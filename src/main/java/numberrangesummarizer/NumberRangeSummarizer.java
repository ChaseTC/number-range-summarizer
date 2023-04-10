package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class NumberRangeSummarizer implements INumberRangeSummarizer {
    @Override
    public ArrayList<Integer> collect(String input) {
        String[] split = input.split(",");
        ArrayList<Integer> ints = new ArrayList<>();
        for (String s : split) {
            ints.add(Integer.parseInt(s));
        }
        return ints;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        Integer[] array = input.toArray(new Integer[0]);
        StringBuilder sb = new StringBuilder();

        boolean inRange = false;

        for (int i = 0; i < array.length - 1; i++) {
            Integer current = array[i];
            Integer next = array[i + 1];

            if (inRange) {
                if (!current.equals(next - 1)) {
                    sb.append(current).append(", ");
                    inRange = false;
                }
            } else {
                sb.append(current);
                if (current.equals(next - 1)) {
                    sb.append("-");
                    inRange = true;
                } else {
                    sb.append(", ");
                }
            }
        }
        sb.append(array[array.length - 1]);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a comma separated list of integers:");

        String input = scanner.nextLine();
        NumberRangeSummarizer summarizer = new NumberRangeSummarizer();
        try {
            Collection<Integer> integerList = summarizer.collect(input);
            String out = summarizer.summarizeCollection(integerList);
            System.out.println(out);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }
}
