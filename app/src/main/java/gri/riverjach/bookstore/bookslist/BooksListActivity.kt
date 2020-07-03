package gri.riverjach.bookstore.bookslist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import gri.riverjach.bookstore.Book
import gri.riverjach.bookstore.R
import gri.riverjach.bookstore.bookdetail.BookDetailActivity
import kotlinx.android.synthetic.main.activity_books.*
import timber.log.Timber

class BooksListActivity : AppCompatActivity(), BooksListAdapter.BooksListAdapterListener {
    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksListAdapter
    private lateinit var books: MutableList<Book>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        books = mutableListOf()
        booksAdapter = BooksListAdapter(books, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BooksListActivity)
            adapter = booksAdapter
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.refreshBooks()
        }

        viewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)
        viewModel.books.observe(this, Observer { newBooks -> updateBooks(newBooks!!) })
    }

    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("List of newBooks $newBooks")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    override fun onBookSelected(book: Book) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(BookDetailActivity.EXTRAT_BOOK_ID, book.id)
        startActivity(intent)
    }


}