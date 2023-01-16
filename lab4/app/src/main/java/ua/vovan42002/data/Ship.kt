package ua.vovan42002.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Ship(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name")var name: String?,
    @ColumnInfo(name = "home_port")var home_port: String?,
    @ColumnInfo(name = "image")var image: String?,
    @ColumnInfo(name = "type")var type: String?
) : Serializable
