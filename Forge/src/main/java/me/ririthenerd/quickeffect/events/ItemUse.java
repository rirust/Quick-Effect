package me.ririthenerd.quickeffect.events;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = "quickeffect", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ItemUse {
    @SubscribeEvent
    public static void useItem(PlayerInteractEvent.RightClickItem e) {
        Player p = e.getEntity();
        ItemStack hand = e.getItemStack();

        if(!p.pick(4.5, 10, true).getType().equals(HitResult.Type.BLOCK)){
            if(e.getItemStack().getItem().equals(Items.PHANTOM_MEMBRANE)){
                p.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.TURTLE_SCUTE)){
                p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200));
                p.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.GHAST_TEAR)){
                p.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.BLAZE_POWDER)){
                p.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.MAGMA_CREAM)){
                p.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.GLISTERING_MELON_SLICE)){
                p.addEffect(new MobEffectInstance(MobEffects.HEAL, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.RABBIT_FOOT)){
                p.addEffect(new MobEffectInstance(MobEffects.JUMP, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.SUGAR)){
                p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200));
                subOne(hand);
            }

            if(e.getItemStack().getItem().equals(Items.STONE)){
                p.addEffect(new MobEffectInstance(MobEffects.INFESTED, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.COBWEB)){
                p.addEffect(new MobEffectInstance(MobEffects.WEAVING, 200));
                subOne(hand);
            }
            if(e.getItemStack().getItem().equals(Items.SLIME_BLOCK)){
                p.addEffect(new MobEffectInstance(MobEffects.OOZING, 200));
                subOne(hand);
            }

            if(e.getItemStack().getItem().equals(Items.FERMENTED_SPIDER_EYE)){
                int no = 3;
                if(p.hasEffect(MobEffects.MOVEMENT_SPEED) || p.hasEffect(MobEffects.JUMP)){
                    p.removeEffect(MobEffects.MOVEMENT_SPEED);
                    p.removeEffect(MobEffects.JUMP);
                    p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200));
                    subOne(hand);
                    no--;
                }
                if(p.hasEffect(MobEffects.HEAL) || p.hasEffect(MobEffects.POISON)){
                    p.removeEffect(MobEffects.HEAL);
                    p.removeEffect(MobEffects.POISON);
                    p.addEffect(new MobEffectInstance(MobEffects.HARM, 200));
                    subOne(hand);
                    no--;
                }
                if(p.hasEffect(MobEffects.NIGHT_VISION)){
                    p.removeEffect(MobEffects.NIGHT_VISION);
                    p.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200));
                    subOne(hand);
                    no--;
                }
                if(no == 3){
                    p.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200));
                    subOne(hand);
                }
            }
            if(e.getItemStack().getItem().equals(Items.REDSTONE)){
                Map<Holder<MobEffect>, MobEffectInstance> se = p.getActiveEffectsMap();
                for (Holder<MobEffect> eff : se.keySet()) {
                    if (e.getItemStack().getItem().equals(Items.REDSTONE)) {
                        if (!(se.get(eff).getDuration() + 20 >= 9600)) {
                            p.addEffect(new MobEffectInstance(eff, se.get(eff).getDuration() + 20, se.get(eff).getAmplifier()));
                            subOne(hand);
                        }
                    }
                }
            }
            if(e.getItemStack().getItem().equals(Items.GLOWSTONE_DUST)){
                Map<Holder<MobEffect>, MobEffectInstance> se = p.getActiveEffectsMap();
                for (Holder<MobEffect> eff : se.keySet()){
                    if(!(se.get(eff).getDuration() + 20 >= 9600)) {
                        if (e.getItemStack().getItem().equals(Items.GLOWSTONE_DUST)) {
                            p.addEffect(new MobEffectInstance(eff, se.get(eff).getDuration(), se.get(eff).getAmplifier() + 1));
                            subOne(hand);
                        }
                    }
                }
            }
        }
    }

    private static void subOne(ItemStack mainHand){
        mainHand.setCount(mainHand.getCount() - 1);
    }

}