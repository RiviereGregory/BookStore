package gri.riverjach.bookstore.repository

import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BookRepository {
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()


    fun deleteBookNow(id: Int) {
        Timber.i("Deleting book now")
        val work = OneTimeWorkRequestBuilder<DeleteBookWorker>()
            .setInputData(workDataOf("bookId" to id))
            .build()

        WorkManager.getInstance()
            .beginUniqueWork("deleteBookIdNow", ExistingWorkPolicy.REPLACE, work).enqueue()
    }

    fun syncBooksNow() {
        Timber.i("Synchronizing books now")
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .beginUniqueWork("syncBooksNow", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }

    fun scheduleBooksSync() {
        Timber.i("Synchronizing books every 12 hours")
        val work = PeriodicWorkRequestBuilder<SyncRepositoryWorker>(12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .enqueueUniquePeriodicWork(
                "syncBooksScheduled",
                ExistingPeriodicWorkPolicy.KEEP,
                work
            )
    }
}