import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class ParkingLot {

    int capacity;
    ArrayList<Vehicle> vehicles;

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        vehicles = new ArrayList<>();
    }

    public String addVehicle(String plate, String type) {

        plate = plate.trim();

        if (plate.isEmpty()) {
            return "Plate number cannot be empty";
        }

        if (vehicles.size() >= capacity) {
            return "Parking is full";
        }

        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equalsIgnoreCase(plate)) {
                return "Vehicle already exists";
            }
        }

        vehicles.add(new Vehicle(plate, type));

        return "Vehicle added successfully";
    }

    public String removeVehicle(String plate) {

        plate = plate.trim();

        for (int i = 0; i < vehicles.size(); i++) {

            Vehicle v = vehicles.get(i);

            if (v.getPlateNumber().equalsIgnoreCase(plate)) {

                long hours = v.getDurationHours();
                double fee = calculateFee(v.getType(), hours);

                String entryTime = v.getEntryTime().format(formatter);

                vehicles.remove(i);

                return "Removed Successfully\n"
                        + "Entry Time: " + entryTime + "\n"
                        + "Duration: " + hours + " hour(s)\n"
                        + "Fee: " + fee;
            }
        }

        return "Vehicle not found";
    }

    public double calculateFee(String type, long hours) {

        if (type.equals("Sedan")) return hours * 10;
        if (type.equals("SUV")) return hours * 15;
        return hours * 20;
    }

    public String viewVehicles() {

        if (vehicles.isEmpty()) {
            return "No vehicles parked";
        }

        String result = "";

        for (Vehicle v : vehicles) {

            result += "Plate: " + v.getPlateNumber()
                    + " | Type: " + v.getType()
                    + " | Entry Time: " + v.getEntryTime().format(formatter)
                    + " | Duration: " + v.getDurationHours()
                    + " hour(s)\n";
        }

        return result;
    }

    public String searchVehicle(String plate) {

        plate = plate.trim();

        for (Vehicle v : vehicles) {

            if (v.getPlateNumber().equalsIgnoreCase(plate)) {

                return "Vehicle Found\n"
                        + "Plate: " + v.getPlateNumber()
                        + "\nType: " + v.getType()
                        + "\nEntry Time: " + v.getEntryTime().format(formatter)
                        + "\nDuration: " + v.getDurationHours()
                        + " hour(s)";
            }
        }

        return "Vehicle not found";
    }

    public static void main(String[] args) {
        new ParkingGUI();
    }
}