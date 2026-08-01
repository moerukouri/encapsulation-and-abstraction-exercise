import java.util.*;

public class RentalFunctions{
    private final List<Vehicle> vehicles = new ArrayList<>();

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

        String headerFormat = "%-" + vehicleTypeWidth + "s | %-" + modelWidth + "s | %-" + plateNumberWidth + "s | %-" + additionalDetailWidth + "s | %-" + rateWidth + "s | %-" + statusWidth + "s";
        String header = String.format(headerFormat, "Type", "Model", "Plate Number", "Additional Detail", "Base Rate", "Status");
        System.out.println(header);
        System.out.println("-".repeat(header.length()));
        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle.toTableRow(vehicleTypeWidth, modelWidth, plateNumberWidth, additionalDetailWidth, rateWidth, statusWidth));
        }
    }

    public enum RentResult{
        SUCCESS, NOT_FOUND, NOT_AVAILABLE
    }

    public RentResult rentVehicle(String plateNumber, int days){
        Vehicle vehicle = findVehicle(plateNumber);
        if (vehicle == null) {
            return RentResult.NOT_FOUND;
        }
        if (!vehicle.isAvailable()){
            return RentResult.NOT_AVAILABLE;
        }
        vehicle.setAvailability(false);
        return RentResult.SUCCESS;
    }

    public enum ReturnResult{
        SUCCESS, NOT_FOUND, NOT_RENTED
    }

    public ReturnResult returnVehicle(String plateNumber){
        Vehicle vehicle = findVehicle(plateNumber);
        if (vehicle == null) {
            return ReturnResult.NOT_FOUND;
        }
        if (vehicle.isAvailable()){
            return ReturnResult.NOT_RENTED;
        }
        vehicle.setAvailability(true);
        return ReturnResult.SUCCESS;
    }

    public static String formatPesos(double amount){
        return String.format("₱%,.2f", amount);
    }
}