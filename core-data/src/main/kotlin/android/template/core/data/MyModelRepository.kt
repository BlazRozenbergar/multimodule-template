package android.template.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import android.template.core.database.MyModel
import android.template.core.database.MyModelDao
import android.template.core.network.MyModelDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface MyModelRepository {
    val myModels: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultMyModelRepository @Inject constructor(
    private val myModelDao: MyModelDao,
    private val myModelDataSource: MyModelDataSource,
) : MyModelRepository {

    override val myModels: Flow<List<String>> =
        myModelDao.getMyModels().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        val modelName = myModelDataSource.getMyModel(name)
        myModelDao.insertMyModel(MyModel(name = modelName))
    }
}
