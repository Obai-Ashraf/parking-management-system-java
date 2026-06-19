import java.time.Duration;
import java.time.LocalDateTime;

public class Vehicle {

    String plateNumber;
    String type;
    LocalDateTime entryTime;

    public Vehicle(String plateNumber, String type) {
        this.plateNumber = plateNumber;
        this.type = type;
        this.entryTime = LocalDateTime.now();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public long getDurationHours() {

        long hours = Duration
                .between(entryTime, LocalDateTime.now())
                .toHours();

        if (hours == 0) return 1;

        return hours;
    }
}