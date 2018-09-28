package com.example.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View


class AnimationManager {
    companion object {
        val INSTANCE = AnimationManager()
    }

    fun getOnlineAnimation(target: View): AnimatorSet {
        val scaleAnimationX = ObjectAnimator.ofFloat(target, "scaleX", 0.50f)
        scaleAnimationX.duration = 500
        scaleAnimationX.repeatCount = ObjectAnimator.INFINITE
        scaleAnimationX.repeatMode = ObjectAnimator.REVERSE

        val scaleAnimationY = ObjectAnimator.ofFloat(target, "scaleY", 0.5f)
        scaleAnimationY.duration = 500
        scaleAnimationY.repeatCount = ObjectAnimator.INFINITE
        scaleAnimationY.repeatMode = ObjectAnimator.REVERSE

        val fadedAnimation = ObjectAnimator.ofFloat(target, "alpha", 0.0f)
        fadedAnimation.duration = 500
        fadedAnimation.repeatCount = ObjectAnimator.INFINITE
        fadedAnimation.repeatMode = ObjectAnimator.REVERSE

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleAnimationX, scaleAnimationY, fadedAnimation)
        return animatorSet
    }

    fun getLoadingDrawable(context: Context) = ContextCompat.getDrawable(context, R.drawable.img_loading)
}
