import java.util.Scanner;

public class InputValidator {
    private final Scanner sc;

    public InputValidator() {
        this.sc = new Scanner(System.in);
    }

    public String getValidatedString(String prompt, String regex, String errorMsg) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();

        while (!input.matches(regex)) {
            System.out.println(errorMsg);
            System.out.print(prompt);
            input = sc.nextLine().trim();
            }
        return input;
    }

    public String getValidatedPositiveInteger(String prompt, String errorMsg){
        System.out.println(prompt);
        String input = sc.nextLine().trim();
        while(!isValidPositiveInteger(input)){
            System.out.println(errorMsg);
            System.out.println(prompt);
            input = sc.nextLine().trim();
        }
        return input;
    }

    public String getValidatedPositiveDecimal(String prompt, String errorMsg){
        System.out.println(prompt);
        String input = sc.nextLine().trim();
        while(!isValidPositiveDecimal(input)){
            System.out.println(errorMsg);
            System.out.println(prompt);
            input = sc.nextLine().trim();
        }
        return input;
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

    public int getValidatedChoice(int low, int high){
        String prompt = "Enter your choice: ";
        String errorMsg = "Invalid choice. Enter a whole number from "+low+"-"+high+".";
        int input = Integer.parseInt(getValidatedPositiveInteger(prompt, errorMsg));
        while(input < low || input > high){
            System.out.println(errorMsg);
            input = Integer.parseInt(getValidatedPositiveInteger(prompt, errorMsg));
        }
        return input;
    }
}