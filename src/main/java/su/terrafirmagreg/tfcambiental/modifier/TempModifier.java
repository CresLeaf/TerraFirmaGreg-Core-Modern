package su.terrafirmagreg.tfcambiental.modifier;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TempModifier implements Comparable<TempModifier> {
    private float change;
    private float potency;
    private float wetness;

    public TempModifier(float change, float potency) {
        this(change, potency, 0f);
    }

    public static Optional<TempModifier> defined(float change, float potency) {
        return Optional.of(new TempModifier(change, potency, 0));
    }

    public static Optional<TempModifier> defined(float change, float potency, float wetness) {
        return Optional.of(new TempModifier(change, potency, wetness));
    }

    public static Optional<TempModifier> none() {
        return Optional.empty();
    }

    @Override
    public int compareTo(@NotNull TempModifier o) {
        return Float.compare(this.change, o.change);
    }
}
