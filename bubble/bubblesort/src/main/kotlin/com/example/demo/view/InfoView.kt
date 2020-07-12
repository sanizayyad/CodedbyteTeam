package com.example.demo.view

import com.example.demo.controller.MenuBarController
import com.example.demo.controller.Processor
import com.example.demo.model.codeList
import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.*

class InfoView : View(){
    val processor: Processor by inject()


    override val root = vbox(20.0) {
        vbox {
            setPrefSize(Double.MAX_VALUE, 200.0)
            alignment = Pos.CENTER
            style {
                paddingLeft = 10.0
                paddingRight = 10.0
                backgroundColor += c("#FFFFFF")
                borderColor += box(c("#000000"))
            }

            label{
                textProperty().bind(processor.codeInfo)
                style{
                    fontSize = 18.px
                    maxWidth = infinity
                    textFill = c("#000000")
                }
            }
        }
        vbox(10){
            alignment = Pos.CENTER
            setPrefSize(Double.MAX_VALUE, 400.0)
            style {
                padding = box(5.px)
                backgroundColor += c("#FFFFFF")
                borderColor += box(c("#000000"))
            }
            listview (codeList){
                style{
                    alignment = Pos.CENTER
                }
                cellFormat{
                    prefHeight = 55.0
                    style{
                        backgroundColor += c("#FFFFFF")
                        alignment = Pos.CENTER
                    }
                    graphic =label(item.value){
                        style{
                            fontSize = 18.px
                            maxWidth = infinity
                            padding = box(5.px)
                            backgroundProperty().bindBidirectional(item.bgProperty)
                            textFillProperty().bindBidirectional(item.codeFillProperty)
                        }
                    }
                }
            }

        }
    }
}