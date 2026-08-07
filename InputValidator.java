import java.util.Scanner;

public class InputValidator {
    private final Scanner sc;
    private final int MAX_SEATS = 8;
    private final int MIN_SEATS = 1;
    private final int MIN_ENGINE_DISPLACEMENT = 50;
    private final int MAX_ENGINE_DISPLACEMENT = 2500;
    private final int MIN_CARGO_CAPACITY = 500;
    private final int MAX_CARGO_CAPACITY = 2000;

    public InputValidator() {
        this.sc = new Scanner(System.in);
    }

    public boolean isValidPositiveInteger(String input){
        try{
            return Integer.parseInt(input) > 0;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public boolean isValidPositiveDecimal(String input){
        try{
            return Double.parseDouble(input) > 0;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public String getValidatedPlateNumber() {
        System.out.print("Enter plate number (6-7 letters and numbers): ");
        String input = sc.nextLine().trim();

        while (!input.matches("^[A-Za-z0-9]{6,7}$")) {
            System.out.println("Invalid plate number. Please enter a valid plate number (6-7 letters and numbers).");
            System.out.print("Enter plate number (6-7 letters and numbers): ");
            input = sc.nextLine().trim();
        }
        return input;
    }

    public String getValidatedModel() {
        System.out.print("Enter model: ");
        String input = sc.nextLine().trim();

        while (input.isEmpty()) {
            System.out.println("Invalid model. Please enter a valid model.");
            System.out.print("Enter model: ");
            input = sc.nextLine().trim();
        }
        return input;
    }

    public double getValidatedRate() {
        System.out.print("Enter base rate per day: ");
        String input = sc.nextLine().trim();

        while (!isValidPositiveDecimal(input)) {
            System.out.println("Invalid rate. Please enter a positive number.");
            System.out.print("Enter base rate per day: ");
            input = sc.nextLine().trim();
        }
        return Double.parseDouble(input);
    }

    public int getValidatedSeats() {
        System.out.print("Enter number of seats (" + MIN_SEATS + "-" + MAX_SEATS + "): ");
        String input = sc.nextLine().trim();

        while (!isValidPositiveInteger(input) || Integer.parseInt(input) < MIN_SEATS || Integer.parseInt(input) > MAX_SEATS) {
            System.out.println("Invalid number of seats. Please enter a valid number of seats (" + MIN_SEATS + "-" + MAX_SEATS + ").");
            System.out.print("Enter number of seats (" + MIN_SEATS + "-" + MAX_SEATS + "): ");
            input = sc.nextLine().trim();
        }
        return Integer.parseInt(input);
    }

    public int getValidatedEngineDisplacement() {
        System.out.print("Enter engine displacement (" + MIN_ENGINE_DISPLACEMENT + "-" + MAX_ENGINE_DISPLACEMENT + " cc): ");
        String input = sc.nextLine().trim();

        while (!isValidPositiveInteger(input) || Integer.parseInt(input) < MIN_ENGINE_DISPLACEMENT || Integer.parseInt(input) > MAX_ENGINE_DISPLACEMENT) {
            System.out.println("Invalid engine displacement. Please enter a valid engine displacement (" + MIN_ENGINE_DISPLACEMENT + "-" + MAX_ENGINE_DISPLACEMENT + ").");
            System.out.print("Enter engine displacement (" + MIN_ENGINE_DISPLACEMENT + "-" + MAX_ENGINE_DISPLACEMENT + " cc): ");
            input = sc.nextLine().trim();
        }
        return Integer.parseInt(input);
    }

    public int getValidatedCargoCapacity() {
        System.out.print("Enter cargo capacity (" + MIN_CARGO_CAPACITY + "-" + MAX_CARGO_CAPACITY + " kg): ");
        String input = sc.nextLine().trim();

        while (!isValidPositiveInteger(input) || Integer.parseInt(input) < MIN_CARGO_CAPACITY || Integer.parseInt(input) > MAX_CARGO_CAPACITY) {
            System.out.println("Invalid cargo capacity. Please enter a valid cargo capacity (" + MIN_CARGO_CAPACITY + "-" + MAX_CARGO_CAPACITY + ").");
            System.out.print("Enter cargo capacity (" + MIN_CARGO_CAPACITY + "-" + MAX_CARGO_CAPACITY + " kg): ");
            input = sc.nextLine().trim();
        }
        return Integer.parseInt(input);
    }

    public int getValidatedRentalDays() {
        System.out.print("Enter number of rental days: ");
        String input = sc.nextLine().trim();

        while (!isValidPositiveInteger(input)) {
            System.out.println("Invalid number of days. Please enter a positive whole number.");
            System.out.print("Enter number of rental days: ");
            input = sc.nextLine().trim();
        }
        return Integer.parseInt(input);
    }

    public int getValidatedChoice(int low, int high){
        String prompt = "Enter your choice: ";
        String errorMsg = "Invalid choice. Enter a whole number from "+low+"-"+high+".";
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        while(!isValidPositiveInteger(String.valueOf(input)) || Integer.parseInt(input) < low || Integer.parseInt(input) > high){
            System.out.println(errorMsg);
            System.out.print(prompt);
            input = sc.nextLine().trim();
        }
        return Integer.parseInt(input);
    }
}