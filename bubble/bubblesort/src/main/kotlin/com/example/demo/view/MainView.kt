package com.example.demo.view

import com.example.demo.controller.InputController
import com.example.demo.controller.Processor
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.layout.BorderPane
import tornadofx.*


class MainView : View("Bubble Sort Visualization") {
    val processor: Processor by inject()
    val inputController: InputController by inject()
    override val root = BorderPane()
    init {
        val dropdown = ComboBox<String>()
        with(root) {
            top (MenuBarView::class)
            center(VisualView::class)

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
                            slider(1.0, 10.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 3.0
                                valueProperty().bindBidirectional(processor.sortingSpeed)
                                disableProperty().bind(processor.isSorting)
                                style {
                                    paddingBottom = 15
                                }
                            }
                            slider(1.0, 10.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 3.0
                                valueProperty().bindBidirectional(processor.nSamples)
                                disableProperty().bind(processor.isSorting)
                            }
                            hbox(25) {
                                style {
                                    paddingTop = 8
                                }
                                dropdown.items = inputController.inputMethods
                                dropdown.selectionModel.selectFirst()
                                disableProperty().bind(processor.isSorting)
                                dropdown.valueProperty().addListener { observable, oldValue, newValue
                                    -> inputController.type = inputController.changeType(newValue)}
                                borderpane {
                                    center = dropdown
                                }
                                button("Enter Inputs") {
                                    action {
                                        inputController.getInput()
                                    }
                                }
                            }
                        }
                    }
                    center = vbox {
                        spacing = 5.0
                        alignment = Pos.CENTER_RIGHT
                        disableWhen{booleanBinding(processor.sampleList) { isEmpty() }}
                        style{
                            paddingRight = 60.0
                        }
                        button("Step Sort") {
                            action {
                                processor.stepSort()
                            }
                            disableProperty().bind(processor.isSorted)
                            setPrefSize(240.0, 40.0)
                        }
                        vbox {
                            style{
                                alignment = Pos.CENTER_RIGHT
                            }
                            disableProperty().bind(processor.isSorting)
                            button("Sort") {
                                action {
                                    processor.sort()
                                }
                                disableProperty().bind(processor.isSorted)
                                setPrefSize(240.0, 40.0)
                            }
                        }

                        button("Shuffle") {
                            action {
                               processor.shuffle()
                            }
                            disableProperty().bind(processor.isSorting)
                            setPrefSize(240.0, 40.0)
                        }
                        button("Reset") {
                            action {
                                dropdown.selectionModel.selectFirst()
                                processor.reset()
                            }
                            setPrefSize(240.0, 40.0)
                        }

                    }
                }

            }
        }
    }
}
