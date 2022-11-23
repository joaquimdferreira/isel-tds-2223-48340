package pt.isel

import com.mongodb.ConnectionString
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.test.*
import org.litote.kmongo.reactivestreams.*  //NEEDED! import KMongo reactivestreams extensions
import org.litote.kmongo.coroutine.* //NEEDED! import KMongo coroutine extensions
import pt.isel.ttt.*

val ENV_MONGO_CONNECTION: String = System.getenv("MONGO_CONNECTION")

class MongoStorageAsyncTest {
    class Student(val _id: String, val name: String, val address: String)

    val connectionString = ConnectionString(ENV_MONGO_CONNECTION)
    val client = KMongo.createClient(connectionString).coroutine
    val database: CoroutineDatabase = client.getDatabase("checkers-remote")

    @BeforeTest
    fun setup() {
        runBlocking {
            database.getCollection<Any>("test").deleteOneById("super")
        }
    }

    @Test
    fun `Check Cloud Mongo DB connection`() {
        runBlocking {
            val collection = database.getCollection<Student>("test")
            collection.insertOne(Student(Random.nextInt().toString(), "Ze Manuel", "Rua Rosa"))
        }
    }
    @Test fun `Save a complex entity and load it`() {
        val serializer = object : StringSerializer<Board> {
            override fun write(obj: Board) = obj.serialize()
            override fun parse(input: String) =  Board.deserialize(input)
        }
        val fs = MongoStorageAsync<Board>("test", { BoardRun() }, serializer, database)
        runBlocking {
            val board = fs
                .new("super")
                .play(Position(1, 1), Player.CROSS)
                .play(Position(2, 0), Player.CIRCLE)
                .play(Position(1, 0), Player.CROSS)
                .play(Position(2, 2), Player.CIRCLE)
                .play(Position(1, 2), Player.CROSS)
            fs.save("super", board)
            val actual = fs.load("super")
            assertNotNull(actual)
            assertNotSame(board, actual)
            assertIs<BoardWinner>(actual)
            val iter = actual.moves.iterator()
            board.moves.forEach { assertEquals(it, iter.next()) }
        }
    }
}
