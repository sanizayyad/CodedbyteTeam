package com.example.demo.view

import com.example.demo.controller.Processor
import com.example.demo.model.BarItem
import com.example.demo.model.BarItemModel
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import javafx.scene.text.FontWeight
import tornadofx.*

class BarGraphItemFragment :ListCellFragment<BarItem>(){
    val processor: Processor by inject()
    val bar = BarItemModel(itemProperty)

    override val root  = VBox()

    init {

        with(root){
            style {
                alignment = Pos.BOTTOM_CENTER
                backgroundColor += c("#808080")
            }
            label (bar.value){
                style{
                    textFill = c("#FFFFFF")
                    fontSize = 20.px
                    fontWeight = FontWeight.EXTRA_BOLD
                }
            }
        }
    }


}
