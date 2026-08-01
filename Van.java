public class Van extends Vehicle {
    private static final double DRIVER_FEE = 500.0;
    private final int cargoCapacity;
 
    public Van(String plateNumber, String model, double rate, int cargoCapacity){
        super(plateNumber, model, rate);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity(){return cargoCapacity;}

    @Override
    public String getVehicleType(){return "Van";}

    @Override
    public double calculateRentalCost(int days){return (getRate() * days) + DRIVER_FEE;}

    @Override
    public String getAdditionalDetail(){return cargoCapacity + "kg";}
}