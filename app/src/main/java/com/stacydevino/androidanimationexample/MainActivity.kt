package com.stacydevino.androidanimationexample

/*
    This Example shows a mixture of what to do and what not to do in a worst-case scenario.
    Yes, we are puposely showing BAD EXAMPLES that are generally code-sound.
 */

import android.animation.*
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
    private var isPropValHolder = false

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
            isPropValHolder = false
            button.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button5.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
        button2.setOnClickListener({
            isXML = false
            isAnimate = true
            isAnimation = false
            isObjectAnimator = false
            isPropValHolder = false
            button.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button5.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
        button3.setOnClickListener({
            isXML = false
            isAnimate = false
            isAnimation = true
            isObjectAnimator = false
            isPropValHolder = false
            button.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button5.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
        button4.setOnClickListener({
            isXML = false
            isAnimate = false
            isAnimation = false
            isObjectAnimator = true
            isPropValHolder = false
            button.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            button5.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })
        button5.setOnClickListener({
            isXML = false
            isAnimate = false
            isAnimation = false
            isObjectAnimator = false
            isPropValHolder = true
            button.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button2.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button3.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button4.background.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY)
            button5.background.setColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
            resetFab()
        })

        //Example of Text Color Animator
        button6.setOnClickListener({
            var textColor = button5.currentTextColor
            var textColorOld = textColor
            if(textColor == Color.MAGENTA){
                textColor = Color.BLACK
            } else {
                textColor = Color.MAGENTA
            }
            val txtColorAnim = ObjectAnimator.ofInt(button6, "textColor", textColorOld , textColor)
            txtColorAnim.duration = 500
            txtColorAnim.setEvaluator(ArgbEvaluator())
            txtColorAnim.start()
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
            if (isPropValHolder) { animateFABPropValues() }
        } else if (id == R.id.fab2) {
            //Fab1 and Fab 2 "look" like they should give the same result, but that is confusing!
            fab2.animate().rotation(360f).setDuration(500).setInterpolator(DecelerateInterpolator()).start()

            //This is even more interesting because of what it does to the object
            //fab2.animate().rotationBy(360f).setDuration(500).setInterpolator(DecelerateInterpolator()).start()
        } else if (id == R.id.fab1) {
            val rotateAnim = RotateAnimation(0f, 360f)
            rotateAnim.interpolator = DecelerateInterpolator()
            rotateAnim.duration = 500
            fab1.startAnimation(rotateAnim)
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

    fun animateFABPropValues(){
        if (isFabOpen){
            val animatorOutSet = AnimatorSet()

            //Define our properties here and once!
            val scaleDownX = PropertyValuesHolder.ofFloat("scaleX", 0.0f)
            val scaleDownY = PropertyValuesHolder.ofFloat("scaleY", 0.0f)
            val fadeOut = PropertyValuesHolder.ofFloat( "alpha", 1f, 0.0f)

            //Fab1
            val animatorScaleOut = ObjectAnimator.ofPropertyValuesHolder( fab1, scaleDownX, scaleDownY)
            animatorScaleOut.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    fab1.visibility = View.GONE
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animatorScaleOut.interpolator = LinearInterpolator()
            animatorScaleOut.duration = 300
            val animatorFadeOut = ObjectAnimator.ofPropertyValuesHolder( fab1, fadeOut)
            animatorFadeOut.interpolator = AccelerateInterpolator()
            animatorFadeOut.duration = 300

            //Fab2
            val animatorScaleOut2 = ObjectAnimator.ofPropertyValuesHolder( fab2, scaleDownX, scaleDownY)
            animatorScaleOut2.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    fab2.visibility = View.GONE
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animatorScaleOut2.interpolator = LinearInterpolator()
            animatorScaleOut2.duration = 300
            val animatorFadeOut2 = ObjectAnimator.ofPropertyValuesHolder( fab2, fadeOut)
            animatorFadeOut2.interpolator = AccelerateInterpolator()
            animatorFadeOut2.duration = 300

            animatorOutSet.playTogether(animatorScaleOut, animatorFadeOut, animatorScaleOut2, animatorFadeOut2)
            animatorOutSet.start()
            fab1.setClickable(false)
            fab2.setClickable(false)
            isFabOpen = false
        } else {
            val animatorInSet = AnimatorSet()

            val scaleUpX = PropertyValuesHolder.ofFloat( "scaleX", 0.8f)
            val scaleUpY = PropertyValuesHolder.ofFloat( "scaleY", 0.8f)
            val fadeIn = PropertyValuesHolder.ofFloat( "alpha", 0f, 1f)

            //Fab1
            val animatorScaleIn = ObjectAnimator.ofPropertyValuesHolder(fab1, scaleUpX, scaleUpY)
            animatorScaleIn.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    fab1.visibility = View.VISIBLE
                }
                override fun onAnimationEnd(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animatorScaleIn.interpolator = AccelerateInterpolator()
            animatorScaleIn.duration = 300
            val animatorFadeIn = ObjectAnimator.ofPropertyValuesHolder(fab1, fadeIn)
            animatorFadeIn.interpolator = AccelerateInterpolator()
            animatorFadeIn.duration = 300

            //Fab2
            val animatorScaleIn2 = ObjectAnimator.ofPropertyValuesHolder(fab2, scaleUpX, scaleUpY)
            animatorScaleIn2.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    fab2.visibility = View.VISIBLE
                }
                override fun onAnimationEnd(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animatorScaleIn2.interpolator = AccelerateInterpolator()
            animatorScaleIn2.duration = 300
            val animatorFadeIn2 = ObjectAnimator.ofPropertyValuesHolder(fab2, fadeIn)
            animatorFadeIn2.interpolator = AccelerateInterpolator()
            animatorFadeIn2.duration = 300

            animatorInSet.playTogether(animatorScaleIn, animatorFadeIn, animatorScaleIn2, animatorFadeIn2)
            animatorInSet.start()
            fab1.setClickable(true)
            fab2.setClickable(true)
            isFabOpen = true
        }
    }


}
