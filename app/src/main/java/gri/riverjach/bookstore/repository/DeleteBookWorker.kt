package gri.riverjach.bookstore.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import gri.riverjach.bookstore.App
import timber.log.Timber

class DeleteBookWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Timber.i("Deleting book ...")
        val bookDao = App.db.bookDao()
        val idBookToDelete: Int = inputData.getInt("bookId", 0)
        bookDao.deleteBookById(idBookToDelete)

        return Result.success()
    }
}