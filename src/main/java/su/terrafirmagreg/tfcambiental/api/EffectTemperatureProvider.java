package su.terrafirmagreg.tfcambiental.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.world.entity.player.Player;

import su.terrafirmagreg.tfcambiental.modifier.TempAttractor;

@FunctionalInterface
public interface EffectTemperatureProvider {
    Optional<TempAttractor> getAttractor(Player player);

    static final List<EffectTemperatureProvider> PROVIDERS = new ArrayList<>();

    public static void register(EffectTemperatureProvider effect) {
        PROVIDERS.add(effect);
    }

    public static float evaluateAll(Player player, float exterior_target_temperature, float current_temperature) {
        float homeostasis_modifier = PROVIDERS.stream()
                .map(effect -> effect.getAttractor(player))
                .flatMap(Optional::stream)
                .filter(attr -> attr.canAttract(current_temperature))
                .map(attr -> {
                    float active_delta = attr.target() - current_temperature;
                    float reactive_delta = attr.target() - exterior_target_temperature;
                    return reactive_delta * attr.rigidity();
                })
                .reduce(0.0f, Float::sum);
        return homeostasis_modifier;
    }
}
