public class UserInterface{
    private static final RentalFunctions rentalFunctions = new RentalFunctions();
    private static final InputValidator validator = new InputValidator();

    public void addVehicle(){
        System.out.println("""
                Select vehicle type:
                1. Car
                2. Motorcycle
                3. Van
                4. Return
                """);
        int typeChoice = validator.getValidatedChoice(1, 4);
        if (typeChoice == 4) {
            return;
        }

        String plateNumber = validator.getValidatedString(
            "Enter Plate Number: ", 
            "^[A-Za-z0-9]+", 
            "Invalid plate number. Enter letters and numbers only, no spaces.");

        while(rentalFunctions.isDuplicatePlate(plateNumber)){
            System.out.println("Vehicle with that plate number already exists. Try again.");
            plateNumber = validator.getValidatedString(
                "Enter Plate Number: ", 
                "^[A-Za-z0-9]+", 
                "Invalid plate number. Use letters and numbers only, no spaces.");
        }

        String model = validator.getValidatedString(
            "Enter Model: ", 
            "\\S.+", 
            "Model cannot be empty.");

        double rate = Double.parseDouble(validator.getValidatedPositiveDecimal(
            "Enter base rate per day: ", 
            "Invalid rate. Enter a positive number."));

        Vehicle vehicle;
        switch (typeChoice){
            case 1 -> { 
                int seats = Integer.parseInt(validator.getValidatedPositiveInteger(
                        "Enter Number of Seats: ",
                        "Invalid seats. Enter a positive whole number."));
                vehicle = new Car(plateNumber, model, rate, seats);
            }
            case 2 -> {
                int cc = Integer.parseInt(validator.getValidatedPositiveInteger(
                        "Enter Engine Displacement (cc): ",
                        "Invalid engine displacement. Enter a positive whole number."));
                vehicle = new Motorcycle(plateNumber, model, rate, cc);
            }
            case 3 -> {
                int capacity = Integer.parseInt(validator.getValidatedPositiveInteger(
                        "Enter Cargo Capacity (kg): ",
                        "Invalid cargo capacity. Enter a positive whole number."));
                vehicle = new Van(plateNumber, model, rate, capacity);
            }
            default -> {
                System.out.println("Invalid Choice. Enter a whole number from 1-4");
                return;
            }

        }

        rentalFunctions.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
    }

    public void viewAllVehicles(){
        System.out.println();
        rentalFunctions.printAllVehicles();
    }

    public void rentVehicle(){
        if(rentalFunctions.getAllVehicles().isEmpty()){
            System.out.println("No vehicles registered yet.");
            return;
        }
        if(rentalFunctions.getAvailableVehicles(false).isEmpty()){
            System.out.println("No vehicles available for rent.");
            return;
        }
        String plateNumber = validator.getValidatedString(
            "Enter Plate Number: ", 
            "^[A-Za-z0-9]+",
            "Invalid plate number. Enter letters or numbers only, no spaces.");
        int days = Integer.parseInt(validator.getValidatedPositiveInteger(
            "Enter Number of Rental Days: ",
            "Invalid number of days. Enter a positive whole number."));

        int result = rentalFunctions.rentVehicle(plateNumber);

        switch(result){
            case RentalFunctions.SUCCESS -> {
                Vehicle vehicle = rentalFunctions.findVehicle(plateNumber);
                double cost = vehicle.calculateRentalCost(days);
                System.out.println("Total rental cost: " + RentalFunctions.formatPesos(cost));
            }
            case RentalFunctions.NOT_FOUND -> System.out.println("Vehicle not found!");
            case RentalFunctions.NOT_AVAILABLE -> System.out.println("Vehicle not available!");
        }
    }

    public void returnVehicle(){
        if(rentalFunctions.getAvailableVehicles(true).isEmpty()){
            System.out.println("No vehicles are currently rented.");
            return;
        }
        String plateNumber = validator.getValidatedString(
            "Enter Plate Number: ", 
            "^[A-Za-z0-9]+",
            "Invalid plate number. Enter letters or numbers only, no spaces.");

        int result = rentalFunctions.returnVehicle(plateNumber);

        switch(result){
            case RentalFunctions.SUCCESS -> System.out.println("Vehicle returned successfully!");
            case RentalFunctions.NOT_FOUND -> System.out.println("Vehicle not found!");
            case RentalFunctions.NOT_RENTED -> System.out.println("Vehicle was not rented.");
        }
    }
}