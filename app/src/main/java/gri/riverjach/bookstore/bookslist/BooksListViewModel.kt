package gri.riverjach.bookstore.bookslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import gri.riverjach.bookstore.App
import gri.riverjach.bookstore.Book

class BooksListViewModel : ViewModel() {
    val books: LiveData<List<Book>> = App.db.bookDao().getAllBooks()

    fun refreshBooks() {
        App.repository.SyncBooksNow()
    }
}