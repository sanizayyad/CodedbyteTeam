package com.example.demo.view

import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.text.FontWeight
import tornadofx.*
import java.awt.Color


class MainView : View("Bubble Sort Visualization") {

    val inputMethods = FXCollections.observableArrayList("Digits",
            "Alphabets", "Random")
    val codelist = listOf<String>("do", "\tswapped = false", "for i in 1 until indexOfLastUnsortedElement-1",
            "\t\tif leftElement > rightElement", "\t\t\tswap(leftElement, rightElement)", "\t\t\tswapped = true", "while swapped")
    val arr = listOf(5, 6, 2, 9, 4, 2, 5, 2, 111, 5) //ar
    // ray for example
    override val root = BorderPane()

    init {
        with(root) {

            top = menubar {
                menu("File") {
                    item("Save", "Shortcut+S").action {
                        println("Saving!")
                    }
                    separator()
                    item("Quit", "Shortcut+Q").action {
                        println("Quitting!")
                    }
                }
                menu("Help") {
                    item("About").action { }
                    item("Authors").action { }
                }
            }

            center = vbox {
                style{
                    padding = box(20.px)
                }
                hbox {
                    alignment = Pos.CENTER
                    spacing = 300.0
                    label("Visualization") {
                        style {
                            fontSize = 18.px
                            fontWeight = FontWeight.NORMAL
                        }
                    }
                    label("Code") {
                        style {
                            fontSize = 18.px
                            fontWeight = FontWeight.NORMAL
                        }
                    }
                }
                hbox {
                    spacing = 20.0
                barchart("", CategoryAxis(), NumberAxis()) {
                    series("Values") {
                        for (i in 0..(arr.size - 1)) {
                            data("$i", arr[i])
                        }
                    }

                }
//                    vbox {
//                        setPrefSize(600.0,600.0)
//                        label ( "sssasa" )
//                        style{
//                            backgroundColor += c("#000000")
//                        }
//                    }
                    vbox {
                        setMaxSize(350.0, 1000.0)
                        spacing = 20.0
                        vbox {
                            setPrefSize(Double.MAX_VALUE, 400.0)
                            alignment = Pos.CENTER
                            style {
                                paddingLeft = 10.0
                                paddingRight = 10.0
                                paddingBottom = 10.0
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
                        vbox{
                            spacing = 10.0
                            alignment = Pos.CENTER
                            setPrefSize(Double.MAX_VALUE, 600.0)
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
            }
            bottom = form {

                borderpane {
                    style {
                        paddingTop = 10
                        paddingBottom = 10
                    }
                    left = hbox {
                        spacing = 10.0

                        vbox {
                            spacing = 25.0
                            label("Number of Samples: ") {
                                style {
                                    fontSize = 16.px
                                }
                            }
                            label("Input Method: ") {
                                style {
                                    fontSize = 16.px
                                }
                            }
                            label("Sorting Speed: ") {
                                style {
                                    fontSize = 16.px
                                }
                            }
                        }
                        vbox {
                            alignment = Pos.CENTER_LEFT
                            spacing = 8.0
                            setPrefSize(300.0, Double.MIN_VALUE)
                            slider(0.0, 10.0, 1.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 5.0
                            }
                            combobox<String> {
                                items = inputMethods

                            }
                            slider(0.0, 10.0, 1.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 5.0
                                style {
                                    paddingTop = 4
                                }
                            }
                        }
                    }
                    center = vbox {
                        spacing = 5.0
                        alignment = Pos.CENTER_RIGHT
                        style{
                            paddingRight = 60.0
                        }
                        button("Sort") {
                            action {
                                println("Run algoritm!")
                            }
                            setPrefSize(240.0, 40.0)
                        }
                        button("Shuffle") {
                            action {
                                println("Run algoritm!")
                            }
                            setPrefSize(240.0, 40.0)
                        }
                        button("Reset") {
                            action {
                                println("Run algoritm!")
                            }
                            setPrefSize(240.0, 40.0)

                        }
                    }
                }

            }
        }
    }
}
