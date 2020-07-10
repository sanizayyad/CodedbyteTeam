package com.example.demo.view

import com.example.demo.controller.Processor
import javafx.embed.swing.SwingFXUtils
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.layout.BorderPane
import javafx.scene.shape.Rectangle
import javafx.stage.Screen
import jdk.nashorn.internal.objects.NativeRegExp.source
import tornadofx.*
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


class MainView : View("Bubble Sort Visualization") {
    val processor: Processor by inject()
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
                            slider(0.0, 10.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 5.0
                                valueProperty().bindBidirectional(processor.sortingSpeed)
                                disableWhen{booleanBinding(processor.isSorting) { false}}
                                style {
                                    paddingBottom = 15
                                }
                            }
                            slider(0.0, 10.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 5.0
                                valueProperty().bindBidirectional(processor.nSamples)
                                disableWhen{booleanBinding(processor.isSorting) { false}}

                            }
                            hbox(25) {
                                style {
                                    paddingTop = 8
                                }
                                dropdown.items = processor.inputMethods
                                dropdown.selectionModel.selectFirst()
                                dropdown.disableWhen{booleanBinding(processor.isSorting) { false}}
                                dropdown.valueProperty().addListener { observable, oldValue, newValue
                                    -> processor.type = processor.changeType(newValue)}
                                borderpane {
                                    center = dropdown
                                }
                                button("Enter Inputs") {
                                    action {
                                        processor.getInput()
                                    }
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
                                processor.sort()
                            }
                            disableWhen{booleanBinding(processor.sampleList) { isEmpty() }}
                            setPrefSize(240.0, 40.0)
                        }

                        button("Shuffle") {
                            action {
                               processor.shuffle()
                            }
                            disableWhen{booleanBinding(processor.sampleList) { isEmpty() }}
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


