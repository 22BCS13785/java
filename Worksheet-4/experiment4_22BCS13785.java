import java.util.*;

public class experiment4_22BCS13785 {
    public static void main(String[] args) {
        ArrayList<String> sym = new ArrayList<>();
        ArrayList<Integer> val = new ArrayList<>();

        Scanner ss = new Scanner(System.in);
        System.out.print("Enter the number of cards: ");
        int car = ss.nextInt();

        for (int i = 0; i < car; i++) {
            System.out.println("Card number " + (i + 1) + " :");
            String symbo = ss.next();
            int num = ss.nextInt();

            sym.add(symbo);
            val.add(num);
        }

        Map<String, List<Integer>> con = new HashMap<>();

        for (int i = 0; i < car; i++) {
            con.putIfAbsent(sym.get(i), new ArrayList<>());
            con.get(sym.get(i)).add(val.get(i));
        }

     
        List<String> sorte_s = new ArrayList<>(con.keySet());
        Collections.sort(sorte_s);

        System.out.println("\nDistinct Symbols are:");
        for (String s : sorte_s) {
            System.out.print(s + " ");
        }
        System.out.println();

    
        for (String s : sorte_s) {
            System.out.println("\nCards in " + s + " Symbol");
            List<Integer> numbers = con.get(s);
            int sum = 0;
            
            for (int num : numbers) {
                System.out.println(s + " " + num);
                sum += num;
            }

            System.out.println("Number of cards: " + numbers.size());
            System.out.println("Sum of Numbers: " + sum);
        }

       
    }
}
