package shtain.it.studio.dev.countries.test.app.root.disposable_manager

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Alex Shtain on 22.02.2019.
 */
class DisposableManagerImpl : IDisposableManager {

    private var mCompositeDisposable: CompositeDisposable? = null
    var isNeedDispose: Boolean = true

    override fun add(_disposable: Disposable) {
        getCompositeDisposable().add(_disposable)
    }

    override fun dispose() {
        if (isNeedDispose) mCompositeDisposable?.let { if (!it.isDisposed) it.dispose() }
        else isNeedDispose = true
    }

    override fun clear() {
        if (isNeedDispose) mCompositeDisposable?.let { if (!it.isDisposed) it.clear() }
        else isNeedDispose = true
    }

    override fun isDisposed(): Boolean {
        return mCompositeDisposable == null || mCompositeDisposable?.isDisposed ?: true
    }

    override fun isNeedDispose(isNeed: Boolean) {
        isNeedDispose = isNeed
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (isDisposed()) mCompositeDisposable = CompositeDisposable()
        return mCompositeDisposable as CompositeDisposable
    }
}