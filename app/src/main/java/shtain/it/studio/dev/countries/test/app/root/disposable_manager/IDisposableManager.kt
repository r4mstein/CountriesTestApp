package shtain.it.studio.dev.countries.test.app.root.disposable_manager

import io.reactivex.disposables.Disposable

/**
 * Created by Alex Shtain on 22.02.2019.
 */
interface IDisposableManager {
    fun add(_disposable: Disposable)
    fun dispose()
    fun clear()
    fun isDisposed(): Boolean
    fun isNeedDispose(isNeed: Boolean)
}