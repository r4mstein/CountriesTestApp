package shtain.it.studio.dev.countries.test.app.splash

import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import io.reactivex.Observable
import shtain.it.studio.dev.countries.test.app.root.anim_manager.IAnimManager
import shtain.it.studio.dev.countries.test.app.root.base.BasePresenter
import shtain.it.studio.dev.countries.test.app.root.disposable_manager.IDisposableManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.02.2019.
 */
private const val DELAY_TIME = 3000L

class SplashPresenter @Inject constructor(
    private val animManager: IAnimManager,
    private val disposableManager: IDisposableManager
) : BasePresenter<IContract.View>(), IContract.Presenter {

    override fun onStop() {
        disposableManager.dispose()
    }

    @SuppressLint("CheckResult")
    override fun startTimer() {
        disposableManager.add(Observable.timer(DELAY_TIME, TimeUnit.MILLISECONDS)
            .doOnSubscribe { mView?.startAnimation() }
            .subscribe {
                disposableManager.isNeedDispose(false)
                mView?.openMainScreen()
            })
    }

    override fun createLogoAnim(view: View): AnimatorSet {
        return AnimatorSet().apply {
            interpolator = AccelerateDecelerateInterpolator()
            play(animManager.createScaleXAnim(view, 1f, 1.2f, 1.1f, 1f))
                .with(animManager.createScaleYAnim(view, 1f, 1.2f, 1.1f, 1f))
                .after(animManager.createAlphaAnim(view, 0f, 0.4f, 0.8f, 1f))
        }
    }

    override fun createTextAnim(view: View): AnimatorSet {
        return AnimatorSet().apply {
            interpolator = AccelerateDecelerateInterpolator()
            play(animManager.createScaleXAnim(view, 1f, 1.2f, 1.1f, 1f))
                .with(animManager.createScaleYAnim(view, 1f, 1.2f, 1.1f, 1f))
                .after(animManager.createAlphaAnim(view, 0f, 0.4f, 0.8f, 1f))
        }
    }
}