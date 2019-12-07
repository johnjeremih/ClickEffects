package com.johnjeremih.clickeffects

import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.ImageView
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

object ClickEffects {


    class AxisPoint {
        internal var x = 0
        internal var y = 0

        companion object {
            internal fun getCenter(v: View): AxisPoint {
                val point = AxisPoint()
                point.x = v.x.toInt() + v.width / 2
                point.y = v.y.toInt() + v.height / 2
                return point
            }
        }
    }


    private fun getRandomColor(): Int {
        val rnd = Random()
        val transparent = 200
        return Color.argb(
            transparent,
            56 + rnd.nextInt(170),
            56 + rnd.nextInt(170),
            56 + rnd.nextInt(170)
        )
    }

    private fun setRandomColor(d: Drawable) {
        val rnd = Random()
        val transparent = 200
        val color = Color.argb(
            transparent,
            56 + rnd.nextInt(170),
            56 + rnd.nextInt(170),
            56 + rnd.nextInt(170)
        )
        d.colorFilter = PorterDuffColorFilter(
            color,
            PorterDuff.Mode.LIGHTEN
        )
    }

    private val handle = Handler()



    fun animJumping(v: View) {
        val duration = 800
        val start = System.currentTimeMillis()
        val origin = v.y
        Thread(object : Runnable {
            override fun run() {
                val t = (System.currentTimeMillis() - start).toInt()
                val progress = t * 1.0 / duration
                val y = Math.abs(
                    v.height
                            * Math.sin(3 * Math.PI * progress)
                            * (1 - progress)
                ).toFloat()
                v.y = origin - y

                Log.d(ContentValues.TAG, "$y/$progress")
                if (progress < 1) handle.postDelayed(
                    this,
                    20
                ) else v.y = origin
            }
        }).start()
    }

    fun animShaking(v: View) {
        val duration = 1000
        val start = System.currentTimeMillis()
        val origin = v.y
        val rotateAngle = Math.PI.toFloat() * 8
        val waveTimes = 5f
        Thread(object : Runnable {
            override fun run() {
                val t = (System.currentTimeMillis() - start).toInt()
                val progress = t * 1.0 / duration
                val y = abs(
                    v.height
                            * sin(Math.PI * progress)
                            * (1 - progress)
                ).toFloat()
                v.y = origin - y
                v.rotation = ((rotateAngle * waveTimes * progress) % rotateAngle - 0.5 * rotateAngle).toFloat();


                v.rotation = (45 * Math.sin(progress * Math.PI * 6)).toFloat()

                Log.d(ContentValues.TAG, "$y/$progress")
                if (progress < 1) handle.postDelayed(
                    this,
                    10
                ) else {
                    v.y = origin
                    v.rotation = 0f
                }
            }
        }).start()
    }


    fun animRing(
        context: Context?,
        v: View,
        duration: Int
    ) {
        val effectSize = v.width * 2 / 3
        val color: Int = getRandomColor() // get a random color

        val p: AxisPoint = AxisPoint.getCenter(v)
        val iv = ImageView(context)
        (v.parent as ViewGroup).addView(iv) // add View to parent

        val gd =
            GradientDrawable()
        gd.shape = GradientDrawable.OVAL
        gd.setStroke(6, color)
        iv.background = gd
        iv.layoutParams.height = effectSize
        iv.layoutParams.width = effectSize
        iv.x = p.x - effectSize / 2.toFloat()
        iv.y = p.y - effectSize / 2.toFloat()
        v.bringToFront() // bring original view to front

        val ip: Interpolator =
            DecelerateInterpolator()
        iv.animate().scaleX(4f).scaleY(4f).setDuration(duration.toLong()).alpha(0f).interpolator =
            ip
    }


    fun animExplosion(context: Context?, v: View) {
        animParticalNova(context, v, 18)
        val particles = 9
        val radious = v.width * 8 / 5
        val ballSize = v.width / 6
        val color: Int =getRandomColor() // get a random color

        val p: AxisPoint = AxisPoint.getCenter(v)
        val points: ArrayList<AxisPoint> =
            ArrayList()
        for (i in 0 until particles) {
            val point =
                AxisPoint()
            point.x =
                (Math.cos(Math.PI * 2 * i / particles) * radious).toInt()
            point.y =
                (Math.sin(Math.PI * 2 * i / particles) * radious).toInt()
            points.add(point)
            val iv = ImageView(context)
            (v.parent as ViewGroup).addView(iv) // add View to parent

            val gd =
                GradientDrawable()
            gd.setColor(color)
            gd.shape = GradientDrawable.OVAL
            iv.background = gd
            iv.layoutParams.height = ballSize
            iv.layoutParams.width = ballSize
            iv.x = p.x - ballSize / 2.toFloat()
            iv.y = p.y - ballSize / 2.toFloat()
            v.bringToFront() // bring original view to front

            val ip: Interpolator =
                DecelerateInterpolator()
            iv.animate().translationXBy(point.x.toFloat()).translationYBy(point.y.toFloat())
                .scaleX(2f).scaleY(2f).setDuration(400).alpha(0f).interpolator = ip
        }
    }

    fun animParticalNova(
        context: Context?,
        v: View,
        particles: Int
    ) {
        val radios = v.width * 6 / 5
        val ballSize = v.width / 8
        val color: Int =getRandomColor() // get a random color

        val p: AxisPoint =AxisPoint.getCenter(v)
        val points: ArrayList<AxisPoint> =
            ArrayList()
        for (i in 0 until particles) {
            val point =AxisPoint()
            point.x =
                (cos(Math.PI * 2 * i / particles) * radios).toInt()
            point.y =
                (sin(Math.PI * 2 * i / particles) * radios).toInt()
            points.add(point)
            val iv = ImageView(context)
            (v.parent as ViewGroup).addView(iv) // add View to parent

            val gd =
                GradientDrawable()
            gd.setColor(color)
            gd.shape = GradientDrawable.OVAL
            iv.background = gd
            iv.layoutParams.height = ballSize
            iv.layoutParams.width = ballSize
            iv.x = p.x - ballSize / 2.toFloat()
            iv.y = p.y - ballSize / 2.toFloat()
            v.bringToFront() // bring original view to front

            val ip: Interpolator =
                LinearOutSlowInInterpolator()
            iv.animate().translationXBy(point.x.toFloat()).translationYBy(point.y.toFloat())
                .scaleX(2f).scaleY(2f).setDuration(400).alpha(0f).interpolator = ip
        }
    }




}