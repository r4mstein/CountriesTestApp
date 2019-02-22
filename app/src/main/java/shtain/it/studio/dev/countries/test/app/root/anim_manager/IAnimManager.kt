package shtain.it.studio.dev.countries.test.app.root.anim_manager

import android.animation.ObjectAnimator
import android.view.View

/**
 * Created by Alex Shtain on 22.02.2019.
 */
interface IAnimManager {
    fun createAlphaAnim(view: View, vararg values: Float): ObjectAnimator
    fun createScaleXAnim(view: View, vararg values: Float): ObjectAnimator
    fun createScaleYAnim(view: View, vararg values: Float): ObjectAnimator
}