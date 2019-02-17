package project.tronku.newst.UtilityClass

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

class VerticalViewPager : ViewPager {

    constructor(context:Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    init {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    private class VerticalPageTransformer: PageTransformer {

        override fun transformPage(page: View, position: Float) {
            when {
                position < -1 -> page.alpha = 0f
                position <= 1 -> {
                    page.alpha = 1f
                    page.translationX = page.width * -position
                    page.translationY = page.height * position
                }
                else -> page.alpha = 0f
            }

        }
    }

    fun swapXY(ev: MotionEvent) : MotionEvent {
        val x = (ev.y / width) * height
        val y = (ev.x / height) * width
        ev.setLocation(x, y)
        return ev
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val intercepted = super.onInterceptTouchEvent(swapXY(ev!!))
        swapXY(ev)
        return intercepted
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(swapXY(ev!!))
    }
}