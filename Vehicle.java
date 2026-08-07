public abstract class Vehicle{
    private final String plateNumber;
    private final String model;
    private final double rate;
    private boolean availability;

    public Vehicle(String plateNumber, String model, double rate){
        this.plateNumber = plateNumber;
        this.model = model;
        this.rate = rate;
        this.availability = true;
    }

    public String getPlateNumber(){return plateNumber;}
    public String getModel(){return model;}
    public double getRate(){return rate;}
    public boolean isAvailable(){return availability;}

    public String getStatus(){return (availability ? "Available" : "Rented");}

    public void setAvailability(boolean availability){this.availability = availability;}

    public abstract String getVehicleType();
    public abstract double calculateRentalCost(int days);
    public abstract String getAdditionalDetail();

    public String toTableRow(int vehicleTypeWidth, int plateNumberWidth, int modelWidth, int additionalDetailWidth, int rateWidth, int statusWidth){
        String status = getStatus();
        String format = "%-" + vehicleTypeWidth + "s | %-" + plateNumberWidth + "s | %-" + modelWidth + "s | %-" + additionalDetailWidth + "s | P%-," + rateWidth + ".2f | %-" + statusWidth + "s";
        return String.format(format, getVehicleType(), plateNumber, model, getAdditionalDetail(), rate, status);
    }
}