public class Motorcycle extends Vehicle {
    private final int engineDisplacement;

    public Motorcycle(String plateNumber, String model, double rate, int engineDisplacement){
        super(plateNumber, model, rate);
        this.engineDisplacement = engineDisplacement;
    }

    public int getEngineDisplacement(){return engineDisplacement;}

    @Override
    public String getVehicleType(){return "Motorcycle";}

    @Override
    public double calculateRentalCost(int days){return getRate() * days;}

    @Override
    public String getAdditionalDetail(){return engineDisplacement + " cc";}
}