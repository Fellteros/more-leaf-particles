package net.fellter.more_leaf_particles

//? if fabric {
//? if < 26.1 {
/*import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
*///?} else
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry

typealias ParticleRegistry = /*? if < 26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/
//?}