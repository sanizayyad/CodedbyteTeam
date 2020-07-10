package com.example.demo.view

import com.example.demo.controller.Processor
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.*

class MyBar : View(){
    val processor: Processor by inject()

    override val root =  stackpane {

        setPrefSize(600.0, 400.0)
        listview(processor.sampleList) {
            style{
                backgroundColor += c("#ffffff")
                alignment = Pos.BOTTOM_CENTER
                paddingTop = 10.0
            }
            isEditable = true
            orientation = Orientation.HORIZONTAL

            cellFormat{

                prefWidth = 600.0 / processor.sampleList.size
                style{
                    alignment = Pos.BOTTOM_CENTER
                    backgroundColor += c("#FFFFFF")
                }
                graphic = vbox {
                    var maxValue = processor.sampleList.maxBy { barItem -> barItem.getValue() }?.getValue()
                    maxHeight = (it.getValue() / maxValue!!) * 400
                    style {
                        alignment = Pos.BOTTOM_CENTER
                        backgroundProperty().bindBidirectional((item.backgroundProperty))
                    }
                    label ("${item.value}"){
                        style{
                            textFill = c("#FFFFFF")
                            fontSize = 20.px
                            fontWeight = FontWeight.EXTRA_BOLD
                        }
                    }
                }
            }
        }

        label ("List is empty!"){
            style{
                fontSize = 50.px
            }
            visibleWhen { booleanBinding(processor.sampleList) { isEmpty() } }
        }
    }

}
