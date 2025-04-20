// Write a Java program to calculate the sum of a list of integers using autoboxing and unboxing. 
// Include methods to parse strings into their respective wrapper classes (e.g., Integer.parseInt())

import java.util.ArrayList;
import java.util.List;

public class experiment5_1__22BCS13785 {
    public static List<Integer> parseStringList(String[] str_nums) {
        List<Integer> int_list = new ArrayList<>();
        for (String num : str_nums) {
            int_list.add(Integer.parseInt(num)); 
        }
        return int_list;
    }
    public static int calculateSum(List<Integer> numbs) {
        int sum = 0;
        for (Integer num : numbs) {
            sum += num; 
        }
        return sum;
    }
    public static void main(String[] args) {
        String[] inputNumbers = {"10", "20", "30", "40", "50"};
        List<Integer> numbe = parseStringList(inputNumbers);
        int sum = calculateSum(numbe);
        System.out.println("Sum of numbers: " + sum);
    }
}
