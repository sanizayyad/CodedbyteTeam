package com.example.demo.view

import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.TextInputDialog
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import tornadofx.*
import java.awt.event.KeyEvent

class MainView : View("Bubble Sort Visualization") {

    val inputMethods = FXCollections.observableArrayList("Random", "Digits",
            "Alphabets")
    override val root = BorderPane()


    init {
        with(root) {

            top = MenuBarView().root
            center = VisualView().root
            bottom = form {

                borderpane {
                    style {
                        paddingTop = 10
                        paddingBottom = 10
                    }
                    left = hbox(10.0) {
                        vbox (30){
                            label("Sorting Speed: ") {
                                style {
                                    fontSize = 16.px
                                }
                            }
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

                        }
                        vbox {
                            setPrefSize(300.0, Double.MIN_VALUE)
                            style{
                                paddingBottom = 10.0
                            }
                            slider(0.0, 10.0, 1.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 5.0
                                style {
                                    paddingBottom = 15
                                }
                            }
                            slider(0.0, 10.0, 1.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 5.0
                            }
                            combobox<String> {
                                items = inputMethods
                                selectionModel.selectFirst()
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
                                GetInput().getInput("all")
                            }
                            setPrefSize(240.0, 40.0)
                        }
                    }
                }

            }
        }
    }
}

class GetInput(){
    fun getInput(inputType: String): MutableList<Int>{
        if(inputType != "Random"){
            InputDialog().openModal()
        }else{

        }
        return  mutableListOf()
    }
}


class InputDialog : View("Input Dialog") {
    override val root = vbox {
        label("Enter your inputs seperated by spaces") {

        }
        textfield {  }
        hbox {
            button("Cancel") {
                action {
                }
                setPrefSize(100.0, 40.0)
            }
            button("Ok") {
                action {
                }
                setPrefSize(100.0, 40.0)
            }
        }
    }

}