import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const myGodotView = 'MyGodotView';

class GodotView extends StatefulWidget {
  const GodotView({super.key});

  @override
  State<GodotView> createState() => _GodotViewState();
}

class _GodotViewState extends State<GodotView> {
  @override
  Widget build(BuildContext context) {
    return Platform.isAndroid
        ? const AndroidView(
            viewType: myGodotView,
            layoutDirection: TextDirection.ltr,
            creationParams: null,
            creationParamsCodec: StandardMessageCodec())
        : const UiKitView(
            viewType: myGodotView,
            layoutDirection: TextDirection.ltr,
            creationParams: null,
            creationParamsCodec: StandardMessageCodec(),
          );
  }
}
