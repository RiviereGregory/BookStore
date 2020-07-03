package gri.riverjach.bookstore.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookDetailViewModelFactory(private val bookId: Int) : ViewModelProvider.NewInstanceFactory() {
    //Permet d'ajouter des parametres au viewModel
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookDetailViewModel(bookId) as T
    }
}