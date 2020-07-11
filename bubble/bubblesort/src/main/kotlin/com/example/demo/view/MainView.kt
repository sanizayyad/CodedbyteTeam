package com.example.demo.view

import com.example.demo.controller.Processor
import javafx.geometry.Pos
import javafx.scene.control.ComboBox
import javafx.scene.layout.BorderPane
import tornadofx.*


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
                                disableProperty().bind(processor.isSorting)
                                style {
                                    paddingBottom = 15
                                }
                            }
                            slider(0.0, 10.0) {
                                isShowTickLabels = true
                                isShowTickMarks = true
                                majorTickUnit = 5.0
                                valueProperty().bindBidirectional(processor.nSamples)
                                disableProperty().bind(processor.isSorting)
                            }
                            hbox(25) {
                                style {
                                    paddingTop = 8
                                }
                                dropdown.items = processor.inputMethods
                                dropdown.selectionModel.selectFirst()
                                disableProperty().bind(processor.isSorting)
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
                            disableProperty().bind(processor.isSorted)
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


//class MainView: View() {
//    val r1 = vbox {
//        setPrefSize(20.0,20.0)
//        style{
//            background = State.ACTIVE.background
//        }
//    }
//    val r2 = vbox {
//        setPrefSize(20.0,20.0)
//        style{
//            background = State.NOTACTIVE.background
//        }
//    }
//    val r3 = vbox {
//        setPrefSize(20.0,20.0)
//        style{
//            background = State.SORTED.background
//        }
//    }
//
//
//    override val root = vbox {
//        button("Animate").action {
//            sequentialTransition {
//                timeline {
//                    keyframe(0.5.seconds) {
//                        keyvalue(r1.translateXProperty(), 50.0, interpolator = Interpolator.EASE_BOTH)
//                    }
//                }
//                timeline {
//                    keyframe(0.5.seconds) {
//                        keyvalue(r2.translateXProperty(), 100.0, interpolator = Interpolator.EASE_BOTH)
//                    }
//                }
//                timeline {
//                    keyframe(0.5.seconds) {
//                        keyvalue(r3.translateXProperty(), 150.0, interpolator = Interpolator.EASE_BOTH)
//                    }
//                }
//            }
//        }
//        pane {
//            add(r1)
//            add(r2)
//            add(r3)
//        }
//    }
//}