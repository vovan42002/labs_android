package ua.vovan42002.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface ShipDao {
    @Query("SELECT * FROM ship")
    fun getAll(): LiveData<List<Ship>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ships: ArrayList<Ship>)

    @Query("DELETE FROM ship")
    fun deleteAll()
}