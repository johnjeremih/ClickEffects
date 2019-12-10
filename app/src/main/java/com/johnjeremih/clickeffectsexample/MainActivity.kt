package com.johnjeremih.clickeffectsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.johnjeremih.clickeffects.ClickEffects
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageButton.setOnClickListener {

            ClickEffects.animRing(baseContext, it, 400)
            ClickEffects.animJumping(it)

        }

        imageButton2.setOnClickListener {
            ClickEffects.animShaking(it)
            ClickEffects.animRing(baseContext,it,200)


        }


        heartImageView.setOnClickListener {

            if (isClicked){
                ClickEffects.animExplosion(baseContext,it)
                ClickEffects.animRing(baseContext, it, 400)


                heartImageView.setImageResource(R.drawable.vector_empty_heart)
                isClicked = false
            }else {

                ClickEffects.animJumping(it)
                ClickEffects.animParticleNova(baseContext,it,18)

                isClicked = true
                heartImageView.setImageResource(R.drawable.vector_full_heart)


            }
        }

    }
}
