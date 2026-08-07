import java.util.*;

public class RentalFunctions{
    private final List<Vehicle> vehicles = new ArrayList<>();
    public static final int SUCCESS = 0;
    public static final int NOT_FOUND = 1;
    public static final int NOT_AVAILABLE = 2;
    public static final int NOT_RENTED = 3;

    public boolean isDuplicatePlate(String plateNumber){return findVehicle(plateNumber) != null;}

    public void addVehicle(Vehicle vehicle){vehicles.add(vehicle);}

    public Vehicle findVehicle(String plateNumber){
        for(Vehicle vehicle : vehicles){
            if(vehicle.getPlateNumber().equalsIgnoreCase(plateNumber)){return vehicle;}
        }
        return null;
    }

    public List<Vehicle> getAllVehicles(){return vehicles;}

    public void printAllVehicles(){
        if (vehicles.isEmpty()){
            System.out.println("No vehicles registered yet.");
            return;
        }

        int vehicleTypeWidth = "Type".length();
        int modelWidth = "Model".length();
        int plateNumberWidth = "Plate Number".length();
        int additionalDetailWidth = "Additional Detail".length();
        int rateWidth = "Base Rate".length();
        int statusWidth = "Status".length();

        for(Vehicle vehicle : vehicles){
            vehicleTypeWidth = Math.max(vehicleTypeWidth, vehicle.getVehicleType().length());
            modelWidth = Math.max(modelWidth, vehicle.getModel().length());
            plateNumberWidth = Math.max(plateNumberWidth, vehicle.getPlateNumber().length());
            additionalDetailWidth = Math.max(additionalDetailWidth, vehicle.getAdditionalDetail().length());
            rateWidth = Math.max(rateWidth, String.format("%.2f", vehicle.getRate()).length());
            statusWidth = Math.max(statusWidth, vehicle.getStatus().length());
        }

        String headerFormat = "%-" + vehicleTypeWidth + "s | %-" + plateNumberWidth + "s | %-" + modelWidth + "s | %-" + additionalDetailWidth + "s | %-" + rateWidth + "s | %-" + statusWidth + "s";
        String header = String.format(headerFormat, "Type", "Plate Number", "Model", "Additional Detail", "Base Rate", "Status");
        System.out.println(header);
        System.out.println("-".repeat(header.length()));
        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle.toTableRow(vehicleTypeWidth, plateNumberWidth, modelWidth, additionalDetailWidth, rateWidth, statusWidth));
        }
    }

    public int rentVehicle(String plateNumber){
        Vehicle vehicle = findVehicle(plateNumber);
        if (vehicle == null) {
            return NOT_FOUND;
        }
        if (!vehicle.isAvailable()){
            return NOT_AVAILABLE;
        }
        vehicle.setAvailability(false);
        return SUCCESS;
    }

    public int returnVehicle(String plateNumber){
        Vehicle vehicle = findVehicle(plateNumber);
        if (vehicle == null) {
            return NOT_FOUND;
        }
        if (vehicle.isAvailable()){
            return NOT_RENTED;
        }
        vehicle.setAvailability(true);
        return SUCCESS;
    }

    public static String formatPesos(double amount){
        return String.format("P%,.2f", amount);
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public List<Vehicle> getRentedVehicles() {
        List<Vehicle> rentedVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isAvailable()) {
                rentedVehicles.add(vehicle);
            }
        }
        return rentedVehicles;
    }
}