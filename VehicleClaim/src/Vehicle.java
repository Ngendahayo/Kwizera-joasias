public class Vehicle {
    private String vehicleId;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private String vehicleType;

    public Vehicle(String vehicleId, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
    }

    public boolean validateDetails() {
        return vehicleYear > 1990 && !vehicleMake.isEmpty() && !vehicleModel.isEmpty();
    }

    // Getters and Setters
    public String getVehicleId() { return vehicleId; }
    public String getVehicleMake() { return vehicleMake; }
    public String getVehicleModel() { return vehicleModel; }
    public int getVehicleYear() { return vehicleYear; }
    public String getVehicleType() { return vehicleType; }

    public void updateDetails(String make, String model, int year, String type) {
        this.vehicleMake = make;
        this.vehicleModel = model;
        this.vehicleYear = year;
        this.vehicleType = type;
    }
}
