public class VehicleRentalSystem{
    static final InputValidator validator = new InputValidator();

    public static void main(String[] args) {
        boolean running = true;
        String border = "-".repeat(40);
        while (running) {
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
                case 1 -> UserInterface.addVehicle();
                case 2 -> UserInterface.viewAllVehicles();
                case 3 -> UserInterface.rentVehicle();
                case 4 -> UserInterface.returnVehicle();
                case 5 -> running = false;
                default -> {
                }
            }
        }
    }
}