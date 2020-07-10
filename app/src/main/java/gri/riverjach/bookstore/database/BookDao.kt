package gri.riverjach.bookstore.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import gri.riverjach.bookstore.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book ORDER BY title")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBookById(id: Int): LiveData<Book>

    @Insert
    fun insertBook(books: List<Book>)

    @Query("DELETE FROM book WHERE id = :id")
    fun deleteBookById(id: Int)

}