package android.template.core.network.di

import android.template.core.network.MyModelDataSource
import android.template.core.network.remote.MyModelNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface NetworkBinds {
        @Singleton
        @Binds
        fun bindsMyModelDataSource(
            myModelNetwork: MyModelNetwork,
        ): MyModelDataSource
    }

    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}