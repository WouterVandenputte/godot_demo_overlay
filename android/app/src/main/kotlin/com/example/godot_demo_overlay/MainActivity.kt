package com.example.godot_demo_overlay

import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine
import org.godotengine.godot.GodotFragment
import org.godotengine.godot.GodotHost

class MainActivity: FlutterFragmentActivity(), GodotHost {
    var godotFragment: GodotFragment? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        // Do some Godot setup
        flutterEngine
            .platformViewsController
            .registry
            .registerViewFactory("MyGodotView", GodotViewFactory(this))
    }

    override fun getActivity() = this

    override fun getGodot() = godotFragment?.godot
}