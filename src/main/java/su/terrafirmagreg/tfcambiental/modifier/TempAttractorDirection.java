package su.terrafirmagreg.tfcambiental.modifier;

public enum TempAttractorDirection {
    HEATING,
    COOLING,
    BIDIRECTIONAL;

    public boolean canAttract(float currentTemperature, float targetTemperature) {
        return switch (this) {
            case HEATING -> targetTemperature > currentTemperature;
            case COOLING -> targetTemperature < currentTemperature;
            case BIDIRECTIONAL -> true;
        };
    }
}
