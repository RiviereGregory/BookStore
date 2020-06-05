package gri.riverjach.bookstore.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import gri.riverjach.bookstore.App
import timber.log.Timber

class SyncRepositoryWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Timber.i("Synchronizing books...")

        val bookApi = FakeBookApi()
        val bookDao = App.db.bookDao()

        bookDao.insertBook(bookApi.loadBooks())

        return Result.success()
    }

}