package shtain.it.studio.dev.countries.test.app.splash

import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash.*
import shtain.it.studio.dev.countries.test.app.R
import shtain.it.studio.dev.countries.test.app.main.navigator.MainActivity
import shtain.it.studio.dev.countries.test.app.root.base.BaseActivity

/**
 * Created by Alex Shtain on 22.02.2019.
 */
private const val ANIMATION_DURATION = 600L

class SplashActivity : BaseActivity<ISplashNavigator, IContract.View, IContract.Presenter>(),
    ISplashNavigator, IContract.View {

    private lateinit var mAnimatorSet: AnimatorSet

    override fun setup() {
        mNavigator = this
        mObjectGraph?.mSplashComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mAnimatorSet.isRunning) mAnimatorSet.cancel()
    }

    override fun startAnimation() {
        mAnimatorSet = AnimatorSet().apply {
            duration = ANIMATION_DURATION
            play(mPresenter.createLogoAnim(ivSplashLogo))
                .with(mPresenter.createTextAnim(tvSplashText))
            start()
        }
    }

    override fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}