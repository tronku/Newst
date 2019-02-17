package project.tronku.newst.Activities

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import project.tronku.newst.R
import java.lang.Thread.sleep

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val appIcon : ImageView = findViewById(R.id.app_icon)
        val appText : ImageView = findViewById(R.id.app_text)

        val intent = Intent(this, MainActivity::class.java)

        setAnimator(appIcon)
        setAnimator(appText)

        val thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (exception : InterruptedException) {
                    exception.printStackTrace()
                } finally {
                    startActivity(intent)
                }
            }
        }

        thread.start()
    }

    fun setAnimator(view: ImageView) {
        val iconAnimator : ObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        iconAnimator.duration = 1000
        iconAnimator.interpolator = AccelerateDecelerateInterpolator()
        iconAnimator.start()
    }

}
