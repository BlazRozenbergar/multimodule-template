package android.template.core.network

interface MyModelDataSource {
    suspend fun getMyModel(name: String): String
}