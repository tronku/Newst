package project.tronku.newst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NavBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}
