package gri.riverjach.bookstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var author: String,
    var summary: String,
    @ColumnInfo(name = "picture_url")
    var pictureUrl: String

)