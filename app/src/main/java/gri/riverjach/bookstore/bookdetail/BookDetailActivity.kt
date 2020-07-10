package gri.riverjach.bookstore.bookdetail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import gri.riverjach.bookstore.Book
import gri.riverjach.bookstore.R
import gri.riverjach.bookstore.bookslist.BooksListActivity
import kotlinx.android.synthetic.main.activity_book_detail.*
import timber.log.Timber

class BookDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRAT_BOOK_ID = "bookId"
    }

    private lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookId = intent.getIntExtra(EXTRAT_BOOK_ID, 1)
        Timber.d("Book id=$bookId")

        //Permet d'ajouter des parametres au viewModel
        val factory = BookDetailViewModelFactory(bookId)
        viewModel = ViewModelProvider(this, factory).get(BookDetailViewModel::class.java)
        viewModel.book.observe(this, Observer { book ->
            if (book != null) {
                updateBook(book)
            }
        })

        findViewById<Button>(R.id.deleteBook).setOnClickListener {
            viewModel.deleteBook(bookId)
            Timber.i("Book deleted Book id=$bookId")
            val intent = Intent(this, BooksListActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }
    }

    private fun updateBook(book: Book) {
        Picasso.get()
            .load(book.pictureUrl)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(bookCover)
        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookSummary.text = book.summary

    }
}