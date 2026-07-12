package su.terrafirmagreg.tfcambiental.modifier;

public record TempAttractor(float target, float rigidity, TempAttractorDirection direction) {

    public boolean canAttract(float temperature) {
        return direction.canAttract(temperature, target);
    }
}
