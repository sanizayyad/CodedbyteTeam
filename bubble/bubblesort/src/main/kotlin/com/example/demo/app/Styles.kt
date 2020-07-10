package com.example.demo.app

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val viewHeaders by cssclass()
        val authorsInfo by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.EXTRA_BOLD
            textFill = Color.RED
        }
        authorsInfo {
            padding = box(10.px)
            fontSize = 18.px

        }
        viewHeaders{
            fontSize = 18.px
            fontWeight = FontWeight.BOLD
            maxWidth = infinity
            alignment = Pos.CENTER
        }

    }
}