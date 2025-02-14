package com.example.godot_demo_overlay

import android.content.Context
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class GodotViewFactory(private val activity: MainActivity) : PlatformViewFactory(
    StandardMessageCodec.INSTANCE) {
    override fun create(context: Context, viewId: Int, args: Any?) : PlatformView {
        val creationParams = args as Map<String?, Any?>?
        return GodotView(context, viewId, creationParams, activity);
    }
}