package shtain.it.studio.dev.countries.test.app.root.disposable_manager

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Alex Shtain on 22.02.2019.
 */
class DisposableManagerImpl : IDisposableManager {

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun add(_disposable: Disposable) {
        getCompositeDisposable().add(_disposable)
    }

    override fun dispose() {
        mCompositeDisposable?.let { if (!it.isDisposed) it.dispose() }
    }

    override fun clear() {
        mCompositeDisposable?.let { if (!it.isDisposed) it.clear() }
    }

    override fun isDisposed(): Boolean {
        return mCompositeDisposable == null || mCompositeDisposable?.isDisposed ?: true
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (isDisposed()) mCompositeDisposable = CompositeDisposable()
        return mCompositeDisposable as CompositeDisposable
    }
}