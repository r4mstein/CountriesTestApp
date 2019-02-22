package shtain.it.studio.dev.countries.test.app.root.anim_manager

import android.animation.ObjectAnimator
import android.view.View

/**
 * Created by Alex Shtain on 22.02.2019.
 */
class AnimManagerImpl : IAnimManager {

    override fun createAlphaAnim(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, "Alpha", *values).apply {
            addUpdateListener { if (view.visibility == View.INVISIBLE) view.visibility = View.VISIBLE }
        }
    }

    override fun createScaleXAnim(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, "scaleX", *values)
    }

    override fun createScaleYAnim(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, "scaleY", *values)
    }
}