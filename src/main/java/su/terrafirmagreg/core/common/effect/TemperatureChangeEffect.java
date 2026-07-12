package su.terrafirmagreg.core.common.effect;

import java.util.Optional;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import su.terrafirmagreg.tfcambiental.api.EffectTemperatureProvider;
import su.terrafirmagreg.tfcambiental.modifier.TempAttractor;
import su.terrafirmagreg.tfcambiental.modifier.TempAttractorDirection;

public class TemperatureChangeEffect extends MobEffect implements EffectTemperatureProvider {

    private static final float RIGIDITY_SCALE = 0.4f;

    private final float targetTemperature;
    private final TempAttractorDirection direction;

    /**
     * Constructor for TemperatureChangeEffect.
     * @param pCategory The category of the effect.
     * @param pColor The color of the effect.
     * @param targetTemperature The target temperature for the effect.
     * @param direction The direction in which the effect can change temperature.
     */
    public TemperatureChangeEffect(MobEffectCategory pCategory, int pColor, float targetTemperature, TempAttractorDirection direction) {
        super(pCategory, pColor);
        this.targetTemperature = targetTemperature;
        this.direction = direction;
        EffectTemperatureProvider.register(this);
    }

    @Override
    public Optional<TempAttractor> getAttractor(Player player) {
        MobEffectInstance effect = player.getEffect(this);
        if (effect == null) {
            return Optional.empty();
        }
        return Optional.of(new TempAttractor(targetTemperature, (float) Math.tanh(RIGIDITY_SCALE * (effect.getAmplifier() + 1)), direction));
    }
}
