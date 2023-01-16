package ua.vovan42002.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Ship::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shipDao(): ShipDao
}