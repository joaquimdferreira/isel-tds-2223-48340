package pt.isel

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.replaceOneById

class MongoStorageAsync<T>(
    private val collectionName: String,
    private val factory: (String) -> T,
    override val serializer: StringSerializer<T>,
    database: CoroutineDatabase
) : StorageAsync<String,T>
{
    val collection = database.getCollection<Doc>(collectionName)

    /**
     * Throws Exception if already exists a document with the same ID.
     */
    override suspend fun new(id: String): T {
        /**
         * Validate that id is unique.
         */
        require(load(id) == null) { "There is already a document with given id $id" }
        /**
         * Create a new entity T and save in on file system.
         */
        val obj = factory(id)
        val objStr = serializer.write(obj)
        collection.insertOne(Doc(id, objStr))
        return obj
    }

    override suspend fun load(id: String): T? {
        val doc = collection.findOneById(id) ?: return null
        val objStr = doc.obj
        return serializer.parse(objStr)
    }

    override suspend fun save(id: String, obj: T) {
        require(load(id) != null) { "There is no document with given id $id" }
        val objStr = serializer.write(obj)
        collection.replaceOneById(id, Doc(id, objStr))
    }

    suspend fun delete(id: String) {
        require(load(id) != null) { "There is no document with given id $id" }
        collection.deleteOneById(id)
    }
}