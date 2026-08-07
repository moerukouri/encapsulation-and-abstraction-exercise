public class UserInterface{
    private static final RentalFunctions rentalFunctions = new RentalFunctions();
    private static final InputValidator validator = VehicleRentalSystem.validator;

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

        String plateNumber = validator.getValidatedPlateNumber();

        while(rentalFunctions.isDuplicatePlate(plateNumber)){
            System.out.println("Vehicle with that plate number already exists. Try again.");
            plateNumber = validator.getValidatedPlateNumber();
        }

        String model = validator.getValidatedModel();

        double rate = validator.getValidatedRate();

        Vehicle vehicle;
        switch (typeChoice){
            case 1 -> { 
                int seats = validator.getValidatedSeats();
                vehicle = new Car(plateNumber, model, rate, seats);
            }
            case 2 -> {
                int cc = validator.getValidatedEngineDisplacement();
                vehicle = new Motorcycle(plateNumber, model, rate, cc);
            }
            case 3 -> {
                int capacity = validator.getValidatedCargoCapacity();
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
        if(!rentalFunctions.hasVehicleWithStatus(true)){
            System.out.println("No vehicles available for rent.");
            return;
        }
        String plateNumber = validator.getValidatedPlateNumber();
        int days = validator.getValidatedRentalDays();

        int result = rentalFunctions.rentVehicle(plateNumber);

        switch(result){
            case RentalFunctions.SUCCESS -> {
                Vehicle vehicle = rentalFunctions.findVehicle(plateNumber);
                double cost = vehicle.calculateRentalCost(days);
                if (vehicle instanceof Van van) {
                    double baseCost = cost - van.getDriverFee();
                    System.out.println("Base rental cost: " + RentalFunctions.formatPesos(baseCost));
                    System.out.println("Driver fee: " + RentalFunctions.formatPesos(van.getDriverFee()));
                }
                System.out.println("Total rental cost: " + RentalFunctions.formatPesos(cost));
            }
            case RentalFunctions.NOT_FOUND -> System.out.println("Vehicle not found!");
            case RentalFunctions.NOT_AVAILABLE -> System.out.println("Vehicle not available!");
        }
    }

    public void returnVehicle(){
        if(rentalFunctions.getAllVehicles().isEmpty()){
            System.out.println("No vehicles registered yet.");
            return;
        }
        if(!rentalFunctions.hasVehicleWithStatus(false)){
            System.out.println("No vehicles are currently rented.");
            return;
        }
        String plateNumber = validator.getValidatedPlateNumber();
        int result = rentalFunctions.returnVehicle(plateNumber);

        switch(result){
            case RentalFunctions.SUCCESS -> System.out.println("Vehicle returned successfully!");
            case RentalFunctions.NOT_FOUND -> System.out.println("Vehicle not found!");
            case RentalFunctions.NOT_RENTED -> System.out.println("Vehicle was not rented.");
        }
    }
}