package com.example.godot_demo_overlay

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import io.flutter.plugin.platform.PlatformView
import org.godotengine.godot.GodotFragment

class GodotView(
    context: Context, id: Int, creationParams: Map<String?, Any?>?,
    private val activity: MainActivity
) : PlatformView {
    private val linearLayout: LinearLayout
    private val frameLayout: FrameLayout

    override fun getView(): View {
        return linearLayout
    }

    override fun dispose() {}

    init {
        linearLayout = LinearLayout(context)
        linearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        frameLayout = FrameLayout(context)
        frameLayout.id = View.generateViewId()
        linearLayout.addView(frameLayout)

        frameLayout.apply {
            //frameLayout.layoutParams.height = 300
            val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
            val godotFragment = GodotFragment()
            fragmentTransaction.replace(frameLayout.id, godotFragment)
            fragmentTransaction.commit()

            activity.godotFragment = godotFragment;
        }
    }


}