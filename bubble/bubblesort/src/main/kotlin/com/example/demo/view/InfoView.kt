package com.example.demo.view

import javafx.geometry.Pos
import tornadofx.*

class InfoView : View(){

    val codelist = listOf<String>("do", "\tswapped = false", "for i in 1 until indexOfLastUnsortedElement-1",
            "\t\tif leftElement > rightElement", "\t\t\tswap(leftElement, rightElement)", "\t\t\tswapped = true", "while swapped")

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

            label("Checking if 2 > 5 and swap them if that is true.." +
                    "\nThe current value of swapped = true"){
                style{
                    fontSize = 16.px
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
            for (i in codelist.indices){
                label("${codelist[i]}"){
                    style{
                        fontSize = 16.px
                        maxWidth = infinity
                        padding = box(5.px)
                        if(i == 3){
                            backgroundColor += c("#000000")
                            textFill = c("#FFFFFF")
                        }
                    }
                }
            }

        }
    }
}