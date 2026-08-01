public class Car extends Vehicle{
    private final int numberSeats;

    public Car(String plateNumber, String model, double rate, int numberSeats){
        super(plateNumber, model, rate);
        this.numberSeats = numberSeats;
    }

    public int getNumberSeats(){return numberSeats;}
    
    @Override
    public String getVehicleType(){return "Car";}

    @Override
    public double calculateRentalCost(int days){return (getRate() * days);}

    @Override
    public String getAdditionalDetail(){return numberSeats + " seats";}
}