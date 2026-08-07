public class VehicleRentalSystem{
    static final InputValidator validator = new InputValidator();
    static final UserInterface userInterface = new UserInterface();

    public static void main(String[] args) {
        boolean running = true;
        String border = "-".repeat(40);
        while (running) {
            System.out.println();
            System.out.println(border);
            System.out.println("VEHICLE RENTAL MANAGEMENT SYSTEM");
            System.out.println(border);
            System.out.println("""
                    1. Add Vehicle
                    2. View All Vehicle
                    3. Rent a Vehicle
                    4. Return a Vehicle
                    5. Exit
                    """);
            int choice = validator.getValidatedChoice(1, 5);

            switch (choice) {
                case 1 -> userInterface.addVehicle();
                case 2 -> userInterface.viewAllVehicles();
                case 3 -> userInterface.rentVehicle();
                case 4 -> userInterface.returnVehicle();
                case 5 -> {
                    System.out.println("Exiting Vehicle Rental System. . .");
                    running = false;
                }
                default -> {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        }
    }
}