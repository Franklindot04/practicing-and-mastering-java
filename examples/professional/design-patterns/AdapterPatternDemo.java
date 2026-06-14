public class AdapterPatternDemo {
    public static void main(String[] args) {
        TemperatureReader reader = new CelsiusSensorAdapter(new LegacyCelsiusSensor());
        System.out.println("Temperature: " + reader.temperatureFahrenheit());
    }
}

interface TemperatureReader {
    double temperatureFahrenheit();
}

class LegacyCelsiusSensor {
    double readCelsius() {
        return 20.0;
    }
}

class CelsiusSensorAdapter implements TemperatureReader {
    private final LegacyCelsiusSensor sensor;

    CelsiusSensorAdapter(LegacyCelsiusSensor sensor) {
        this.sensor = sensor;
    }

    public double temperatureFahrenheit() {
        return sensor.readCelsius() * 9 / 5 + 32;
    }
}
