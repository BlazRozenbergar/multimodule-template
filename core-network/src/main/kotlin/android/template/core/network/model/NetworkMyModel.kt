package android.template.core.network.model

import kotlinx.serialization.Serializable

@Serializable
//TODO: Add to proguard if not initialized anywhere
data class NetworkMyModel(
    val name: String,
)