import 'package:flutter/material.dart';
import 'package:godot_demo_overlay/godot_view.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      body: Stack(
        children: [
          SizedBox(
            width: size.width,
            height: size.height,
            child: GodotView(),
          ),
          Align(
            alignment: Alignment.centerLeft,
            child:
                Container(color: Colors.purpleAccent, width: 100, height: 100),
          ),
          Align(
            alignment: Alignment.bottomRight,
            child: FloatingActionButton.large(
              onPressed: () {},
              tooltip: 'Button Over Godot',
            ),
          ),
        ],
      ),
    );
  }
}
