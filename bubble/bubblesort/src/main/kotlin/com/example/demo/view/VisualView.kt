package com.example.demo.view

import com.example.demo.app.Styles
import tornadofx.*

class VisualView : View(){
    val arr:MutableList<Int> = mutableListOf<Int>(5, 6, 2, 9, 4, 2, 5, 2, 6, 5) //ar

    override val root = vbox {
        style{
            padding = box(20.px)
        }
        hbox (20.0){
            setMaxSize(Double.MAX_VALUE, 400.0)
            vbox (20.0){
                label("Visualization") {
                   addClass(Styles.viewHeaders)
                }
                borderpane {
                    center = MyBar(arr).root
                }
            }
            vbox (20.0){
                setMaxSize(350.0, Double.MAX_VALUE)
                label("Code") {
                    addClass(Styles.viewHeaders)
                }
                borderpane {
                    center = InfoView().root
                }

            }
        }

    }
}

