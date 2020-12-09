package com.showmiso.architecturestudy.ui

import androidx.databinding.ObservableField
import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.data.repository.NaverRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MovieViewModel(
    private val naverRepository: NaverRepository
) {
    private val disposable = CompositeDisposable()

    val query = ObservableField<String>()
    val movieList = ObservableField<List<MovieModel.Movie>>()

    val showDataIsEmpty = ObservableField<Unit>()
    val showThrowError = ObservableField<Throwable>()

    val showProgress = ObservableField<Unit>()
    val hideProgress = ObservableField<Unit>()

    fun searchMovie() {
        query.get()?.let {
            showProgress.notifyChange()
            naverRepository.getMoviesList(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    // TODO : Hide Keyboard
                    hideProgress.notifyChange()
                    if (list.isEmpty()) {
                        showDataIsEmpty.notifyChange()
                    } else {
                        movieList.set(list)
                    }
                }, { error ->
                    hideProgress.notifyChange()
                    showThrowError.set(error)
                }).addTo(disposable)
        }
    }

    fun clearDisposable() {
        disposable.clear()
    }
}
