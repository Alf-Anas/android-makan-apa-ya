package dev.geoit.makanapaya.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [HistoryEntity::class, RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun restaurantDao(): RestaurantDao

    companion object {
        @Volatile
        private var Instance: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MainDatabase::class.java, "main_database")
                    .addCallback(RoomDatabaseCallback(context))
                    .build().also { Instance = it }
            }
        }
    }

    private class RoomDatabaseCallback(private val context: Context) : Callback() {
        @OptIn(DelicateCoroutinesApi::class)
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Add initial data when the database is created
            GlobalScope.launch(Dispatchers.IO) {
                val converters = Converters()
                val restaurantDao = getDatabase(context).restaurantDao()
                restaurantDao.insert(
                    RestaurantEntity(
                        0,
                        "Nasi Padang Halim",
                        9,
                        21,
                        converters.saveList(
                            listOf(
                                "Ayam Bakar",
                                "Ayam Goreng",
                                "Ayam Sambal",
                                "Ayam Gulai",
                                "Ikan Bakar",
                                "Ikan Goreng",
                                "Telur Dadar"
                            )
                        ),
                        "2023-10-14 08:00:00"
                    )
                )
                restaurantDao.insert(
                    RestaurantEntity(
                        1,
                        "Warteg Bahari Jengki",
                        0,
                        24,
                        converters.saveList(
                            listOf(
                                "Ayam Bakar",
                                "Ayam Kecap",
                                "Ayam Goreng",
                                "Ayam Sambal",
                                "Ayam Gulai",
                                "Ikan Bakar",
                                "Ikan Goreng",
                                "Ikan Teri",
                                "Ikan Asin",
                                "Ikan Sarden",
                                "Telur Dadar",
                                "Telur Bulat Sambal",
                                "Telur Bulat Gulai",
                                "Telur Asin",
                            )
                        ),
                        "2023-10-14 09:00:00"
                    )
                )
                restaurantDao.insert(
                    RestaurantEntity(
                        2,
                        "Sate Pak Ijo",
                        18,
                        24,
                        converters.saveList(
                            listOf(
                                "Sate Ayam",
                                "Sate Daging",
                            )
                        ),
                        "2023-10-14 09:00:00"
                    )
                )
                restaurantDao.insert(
                    RestaurantEntity(
                        3,
                        "Ayam Geprek Pak Muh",
                        10,
                        24,
                        converters.saveList(
                            listOf(
                                "Ayam Geprek",
                            )
                        ),
                        "2023-10-14 09:00:00"
                    )
                )
                restaurantDao.insert(
                    RestaurantEntity(
                        4,
                        "Nasi Goreng Malam",
                        18,
                        24,
                        converters.saveList(
                            listOf(
                                "Nasi Goreng",
                                "Nasi Gila",
                                "Mie Goreng",
                                "Mie Rebus",
                                "Kwetiau Goreng",
                                "Kwetiau Rebus",
                            )
                        ),
                        "2023-10-14 09:00:00"
                    )
                )
                restaurantDao.insert(
                    RestaurantEntity(
                        5,
                        "Bakso Bang Amir",
                        18,
                        24,
                        converters.saveList(
                            listOf(
                                "Bakso Biasa",
                                "Bakso Urat",
                                "Mie Ayam",
                                "Mie Ayam Bakso",
                                "Soto Ayam",
                                "Soto Daging",
                            )
                        ),
                        "2023-10-14 09:00:00"
                    )
                )
                restaurantDao.insert(
                    RestaurantEntity(
                        6,
                        "Pecel Ayam",
                        18,
                        24,
                        converters.saveList(
                            listOf(
                                "Ayam Goreng",
                                "Ayam Bakar",
                                "Bebek Goreng",
                                "Bebek Bakar",
                                "Ati Ampela",
                                "Pecel Lele",
                            )
                        ),
                        "2023-10-14 09:00:00"
                    )
                )
            }
        }
    }

}