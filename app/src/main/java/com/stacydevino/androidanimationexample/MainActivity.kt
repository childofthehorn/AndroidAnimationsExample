package com.stacydevino.androidanimationexample

/*
    This Example shows a mixture of what to do and what not to do in a worst-case scenario.
    Yes, we are puposely showing BAD EXAMPLES that are generally code-sound.
 */

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var isFabOpen = false
    var fab_open: Animation? = null
    var fab_close: Animation? = null
    private var isAnimate = false
    private var isObjectAnimator = false
    private var isXML = true //default state
    private var isAnimation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        fab!!.setOnClickListener(this)
        fab1!!.setOnClickListener(this)
        fab2!!.setOnClickListener(this)
        button.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)

        button.setOnClickListener({
            isXML = true
            isAnimate = false
            isAnimation = false
            isObjectAnimator = false
            button.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
        button2.setOnClickListener({
            isXML = false
            isAnimate = true
            isAnimation = false
            isObjectAnimator = false
            button.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
        button3.setOnClickListener({
            isXML = false
            isAnimate = false
            isAnimation = true
            isObjectAnimator = false
            button.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
        button4.setOnClickListener({
            isXML = false
            isAnimate = false
            isAnimation = false
            isObjectAnimator = true
            button4.isSelected = true
            button.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
    }

    fun resetFab(){
        isFabOpen = false
        fab1.visibility = View.INVISIBLE
        fab2.visibility = View.INVISIBLE
    }

    override fun onClick(v: View) {
        val id = v.getId()
        if (id == R.id.fab) {
            if (isXML) { animateFABXML() }
            if (isAnimate) { animateFABAnim() }
            if (isAnimation) { animateFABAnimation() }
            if (isObjectAnimator) { animateFABObjectAninator() }
        } else if (id == R.id.fab2) {
        } else if (id == R.id.fab1) {
        }
    }


    fun animateFABXML() {
        fab1.scaleX = 1f
        fab1.scaleY = 1f
        fab2.scaleX = 1f
        fab2.scaleY = 1f
        fab1.visibility = View.VISIBLE
        fab2.visibility = View.VISIBLE
        if (isFabOpen) {
            fab1.startAnimation(fab_close)
            fab2.startAnimation(fab_close)
            fab1.setClickable(false)
            fab2.setClickable(false)
            isFabOpen = false
        } else {
            fab1.startAnimation(fab_open)
            fab2.startAnimation(fab_open)
            fab1.setClickable(true)
            fab2.setClickable(true)
            isFabOpen = true
        }
    }

    fun animateFABAnim(){
        if (isFabOpen){
            fab1.animate().scaleX(0f).scaleY(0f).setDuration(300).setInterpolator(LinearInterpolator()).withEndAction({fab1.visibility = View.GONE}).start()
            fab1.animate().alpha(0f).setDuration(300).setInterpolator(AccelerateInterpolator()).start()
            fab2.animate().scaleX(0f).scaleY(0f).setDuration(300).setInterpolator(LinearInterpolator()).withEndAction({fab2.visibility = View.GONE}).start()
            fab2.animate().alpha(0f).setDuration(300).setInterpolator(AccelerateInterpolator()).start()
            fab1.setClickable(false)
            fab2.setClickable(false)
            isFabOpen = false
        } else {
            fab1.visibility = View.VISIBLE
            fab2.visibility = View.VISIBLE
            fab1.animate().scaleX(0.8f).scaleY(0.8f).setDuration(300).setInterpolator(LinearInterpolator()).start()
            fab1.animate().alpha(1f).setDuration(300).setInterpolator(AccelerateInterpolator()).start()
            fab2.animate().scaleX(0.8f).scaleY(0.8f).setDuration(300).setInterpolator(LinearInterpolator()).start()
            fab2.animate().alpha(1f).setDuration(300).setInterpolator(AccelerateInterpolator()).start()
            fab1.setClickable(true)
            fab2.setClickable(true)
            isFabOpen = true
        }
    }

    fun animateFABAnimation(){
        if (isFabOpen){
            val scaleAnim = ScaleAnimation(.8f, 0f, // Start and end values for the X axis scaling
                    .8f, 0f,                        // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, .5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, .5f) // Pivot point of Y scaling
            scaleAnim.interpolator = LinearInterpolator()
            scaleAnim.duration = 300
            val alphaAnim = AlphaAnimation(1f, 0f)
            alphaAnim.interpolator = AccelerateInterpolator()
            alphaAnim.duration = 300
            alphaAnim.setAnimationListener(object :Animation.AnimationListener{
                override fun onAnimationEnd(animation: Animation?) {
                    fab1.visibility = View.GONE
                    fab2.visibility = View.GONE
                }
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationStart(animation: Animation?) {
                    fab1.visibility = View.VISIBLE
                    fab2.visibility = View.VISIBLE}
            })
            val animSet = AnimationSet(false)
            animSet.addAnimation(scaleAnim)
            animSet.addAnimation(alphaAnim)
            fab1.startAnimation(animSet)
            fab2.startAnimation(animSet)
            fab1.setClickable(false)
            fab2.setClickable(false)
            isFabOpen = false
        } else {
            val scaleAnim = ScaleAnimation(0f, .8f, // Start and end values for the X axis scaling
                    0f, .8f,                        // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, .5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, .5f) // Pivot point of Y scaling
            scaleAnim.interpolator=LinearInterpolator()
            scaleAnim.duration = 300
            val alphaAnim = AlphaAnimation(0f, 1f)
            alphaAnim.interpolator = AccelerateInterpolator()
            alphaAnim.duration = 300
            alphaAnim.setAnimationListener(object :Animation.AnimationListener{
                override fun onAnimationEnd(animation: Animation?) {
                    fab1.scaleX = 0.8f
                    fab1.scaleY = 0.8f
                    fab2.scaleX = 0.8f
                    fab2.scaleY = 0.8f
                    fab1.visibility = View.VISIBLE
                    fab2.visibility = View.VISIBLE
                }
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationStart(animation: Animation?) {}
            })
            val animSet = AnimationSet(false)
            animSet.addAnimation(scaleAnim)
            animSet.addAnimation(alphaAnim)
            fab1.startAnimation(animSet)
            fab2.startAnimation(animSet)
            fab1.setClickable(true)
            fab2.setClickable(true)
            isFabOpen = true
        }
    }

    fun animateFABObjectAninator(){
        if (isFabOpen){
            val animatorOutSet = AnimatorSet()

            //Fab1
            val scaleDownX = ObjectAnimator.ofFloat(fab1, "scaleX", 0.0f)
            val scaleDownY = ObjectAnimator.ofFloat(fab1, "scaleY", 0.0f)
            scaleDownX.interpolator = LinearInterpolator()
            scaleDownY.interpolator = LinearInterpolator()
            val fadeOut = ObjectAnimator.ofFloat(fab1, "alpha", 1f, 0.0f)
            fadeOut.interpolator = AccelerateInterpolator()
            fadeOut.duration = 300
            scaleDownX.duration = 300
            scaleDownY.duration = 300
            scaleDownX.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    fab1.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })

            //Fab2
            val scaleDownX2 = ObjectAnimator.ofFloat(fab2, "scaleX", 0.0f)
            val scaleDownY2 = ObjectAnimator.ofFloat(fab2, "scaleY", 0.0f)
            scaleDownX2.interpolator = LinearInterpolator()
            scaleDownY2.interpolator = LinearInterpolator()
            val fadeOut2 = ObjectAnimator.ofFloat(fab2, "alpha", 1f, 0.0f)
            fadeOut2.interpolator = AccelerateInterpolator()
            fadeOut2.duration = 300
            scaleDownX2.duration = 300
            scaleDownY2.duration = 300
            scaleDownX2.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    fab2.visibility = View.GONE
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animatorOutSet.playTogether(scaleDownX, scaleDownY, fadeOut, scaleDownX2, scaleDownY2, fadeOut2)
            animatorOutSet.start()
            fab1.setClickable(false)
            fab2.setClickable(false)
            isFabOpen = false
        } else {
            val animatorInSet = AnimatorSet()

            //Fab1
            val scaleUpX = ObjectAnimator.ofFloat(fab1, "scaleX", 0.8f)
            val scaleUpY = ObjectAnimator.ofFloat(fab1, "scaleY", 0.8f)
            scaleUpX.interpolator = LinearInterpolator()
            scaleUpY.interpolator = LinearInterpolator()
            val fadeIn = ObjectAnimator.ofFloat(fab1, "alpha", 0f, 1f)
            fadeIn.interpolator = AccelerateInterpolator()
            fadeIn.duration = 300
            scaleUpX.duration = 300
            scaleUpY.duration = 300
            scaleUpX.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    fab1.visibility = View.VISIBLE
                }
                override fun onAnimationEnd(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })

            //Fab2
            val scaleUpX2 = ObjectAnimator.ofFloat(fab2, "scaleX", 0.8f)
            val scaleUpY2 = ObjectAnimator.ofFloat(fab2, "scaleY", 0.8f)
            scaleUpX2.interpolator = LinearInterpolator()
            scaleUpY2.interpolator = LinearInterpolator()
            val fadeIn2 = ObjectAnimator.ofFloat(fab2, "alpha", 0f, 1f)
            fadeIn2.interpolator = AccelerateInterpolator()
            fadeIn2.duration = 300
            scaleUpX2.duration = 300
            scaleUpY2.duration = 300
            scaleUpX2.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    fab2.visibility = View.VISIBLE
                }
                override fun onAnimationEnd(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animatorInSet.playTogether(scaleUpX, scaleUpY, fadeIn, scaleUpX2, scaleUpY2, fadeIn2)
            animatorInSet.start()
            fab1.setClickable(true)
            fab2.setClickable(true)
            isFabOpen = true
        }
    }


}
