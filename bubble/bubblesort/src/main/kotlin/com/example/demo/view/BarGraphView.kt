package com.example.demo.view

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.*

class MyBar(datas: MutableList<Int>) : View(){
    var highest = datas.max() ?: 0
    override val root =  hbox {
        setPrefSize(600.0, 400.0)
        style{
            backgroundColor += c("#ffffff")
            alignment = Pos.BOTTOM_CENTER
            paddingTop = 20.0
        }
        spacing = 10.0
        for (i in datas.indices){
            vbox {
                maxHeight = (datas[i].toDouble()/highest) * 400
                prefWidth = 600.0/datas.size
                style {
                    if(i == 5 || i == 6){
                        backgroundColor += c("#10321e")
                    }else{
                        backgroundColor += c("#808080")
                    }
                    alignment = Pos.BOTTOM_CENTER
                }
                label ("${datas[i]}"){
                    style{
                        textFill = c("#FFFFFF")
                        fontSize = 20.px
                        fontWeight = FontWeight.EXTRA_BOLD
                    }
                }
            }
        }

    }
}
