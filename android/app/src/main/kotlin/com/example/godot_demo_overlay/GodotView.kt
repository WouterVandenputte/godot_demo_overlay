package com.example.godot_demo_overlay

import android.content.Context
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.allViews
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

    override fun dispose() {
        // Optional cleanup
        activity.godotFragment?.let {
            activity.supportFragmentManager.beginTransaction().remove(it).commitNowAllowingStateLoss()
            activity.godotFragment = null
        }
    }

    init {
        linearLayout = LinearLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        frameLayout = FrameLayout(context).apply {

            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }
        frameLayout.id = View.generateViewId()

        linearLayout.addView(frameLayout)

        val godotFragment = GodotFragment()

        godotFragment.viewLifecycleOwnerLiveData.observeForever { owner ->
            owner?.let {
                godotFragment.view?.viewTreeObserver?.addOnGlobalLayoutListener(object :
                    ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        godotFragment.view?.viewTreeObserver?.removeOnGlobalLayoutListener(this)

                        // Now that layout is done, we can safely adjust z-order
                        godotFragment.view?.let {
                            for (v in it.allViews) {
                                if (v is SurfaceView) {
                                    v.setZOrderOnTop(true)
                                    Log.d("GodotView", "Set z-order on SurfaceView")
                                }
                            }
                        }
                    }
                })
            }
        }
        frameLayout.post {
            activity.supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, godotFragment)
                .commitNowAllowingStateLoss()

            activity.godotFragment = godotFragment
        }
    }
}