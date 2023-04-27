import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Raffle {

    private static ArrayList<Toy> toys = new ArrayList<>();
    private static PriorityQueue<Toy> prizes = new PriorityQueue<>();

    private static int idCounter = 0;

    /**
     * Add a new toy in the prize fund.
     * It is necessary to specify a title of toy which be added to list and its frequency of dropping out.
     * The ID of new toy will be added automatically.
     * If a toy which user want to add in the prize fund is already exist
     * the message about invalid adding will be shown and such toy will not be added in the prize fund.
     * In another case a new toy will be added a prize fund and the message about this will be shown.
     */
    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Enter Title: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Incorrect input. Try again.");
                break;
            }
            System.out.print("Enter Frequency of dropping out: ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("Incorrect input. Try again.");
                } else {
                    Toy toy = new Toy(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("New toy was added");
                    } else {
                        System.out.println("This toy is already in the prize fund.");
                    }
                }
            } else {
                System.out.println("Incorrect input. Try again.");
            }
            break;
        }
    }

    /**
     * Change the toy dropping out of dropping out.
     * It is necessary to specify a title of toy which dropping out dropping out be changed.
     * If a toy frequency of victory is changed successfully the message about this will be shown.
     * In another case the message about incorrect input will be shown.
     */
    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Toy ID: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Toy " + toys.get(selectedId).getToyTitle() +
                        " has frequency of victory " + toys.get(selectedId).getToyVictoryFrequency());
                System.out.print("Enter new Frequency of dropping out: ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setToyVictoryFrequency(newFrequency);
                    System.out.println("Frequency was changed.");
                } else {
                    System.out.println("Incorrect input. Try again.");
                }
            } else {
                System.out.println("There is not toy with such ID in prize fund.");
            }
        } else {
            System.out.println("Incorrect input. Try again.");
        }
    }

    /**
     * Input validation method.
     * @param s - string text.
     * @return True if all characters in the string are digits.
     * False if at least one character is not digit.
     */
    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * The method of realization the raffle.
     * It creates a PriorityQueue of prizes.
     * @return prize (toy)
     */
    public Toy getPrize() {
        if (prizes.size() == 0) {
            Random rnd = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getToyVictoryFrequency(); i++) {
                    Toy temp = new Toy(toy.getToyId(), toy.getToyTitle(), rnd.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }

    /**
     * The method of initialization the raffle.
     * It is necessary to add at least two toys into prize fund.
     */
    public void raffle() {
        if (toys.size() >= 2) {
            Toy prize = getPrize();
            System.out.println("Prize: " + prize.getToyTitle());
            saveResult(prize.getInfo());
        } else {
            System.out.println("You should add at least two toys into prize fund.");
        }
    }

    /**
     * The method of saving the result of raffle.
     * It will create a new file with results of the raffle.
     */
    private void saveResult(String text) {
        File file = new File("Result.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("Result.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
