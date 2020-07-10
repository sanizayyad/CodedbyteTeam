package com.example.demo.controller

import com.example.demo.view.MainView
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.Image
import javafx.stage.FileChooser
import tornadofx.*
import java.awt.Rectangle
import java.awt.Robot
import java.io.IOException
import javax.imageio.ImageIO

class MenuBarController :Controller(){
    val mainView: MainView by inject()


    fun save(view:View){
        val fileChooser = FileChooser()
        val extFilter = FileChooser.ExtensionFilter("(PNG)", "*.png")
        fileChooser.extensionFilters.add(extFilter)
        val file = fileChooser.showSaveDialog(primaryStage)

        val x = view.root.localToScreen(0.0, 0.0).x
        val y = view.root.localToScreen(0.0, 0.0).y
        val width  = mainView.root.boundsInParent.width
        val height  = mainView.root.boundsInParent.height
        val image = Robot().createScreenCapture(Rectangle(x.toInt(),y.toInt(),width.toInt(),height.toInt()))
        val convImage = SwingFXUtils.toFXImage(image,null)

        file?.let{
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(convImage as Image?, null), "png", file)
            } catch (e: IOException) {
            }
        }
    }
}