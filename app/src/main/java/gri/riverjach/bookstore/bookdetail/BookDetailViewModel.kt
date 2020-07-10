package gri.riverjach.bookstore.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import gri.riverjach.bookstore.App
import gri.riverjach.bookstore.Book

class BookDetailViewModel(bookId: Int) : ViewModel() {
    private val bookIdLiveData = MutableLiveData<Int>()

    val book: LiveData<Book> = Transformations.switchMap(bookIdLiveData) { id ->
        App.db.bookDao().getBookById(id)
    }

    init {
        bookIdLiveData.value = bookId
    }

    fun deleteBook(id: Int) {
            App.repository.deleteBookNow(id)
    }
}