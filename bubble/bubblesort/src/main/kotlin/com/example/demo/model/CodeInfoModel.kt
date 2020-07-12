package com.example.demo.model

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

enum class CodeBg(val color: Background) {
    ACTIVE(Background(BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))),
    NOTACTIVE(Background(BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))),
}

enum class CodeFill(val color: Paint) {
    ACTIVE(Color.WHITE),
    NOTACTIVE(Color.BLACK),
}

class CodeInfoModelItem(value: String,bg: CodeBg = CodeBg.NOTACTIVE,codeFill: CodeFill = CodeFill.NOTACTIVE) {
    val valueProperty = SimpleStringProperty(value)
    var value by valueProperty

    val bgProperty = SimpleObjectProperty<Background>(bg.color)
    var bg by bgProperty

    val codeFillProperty = SimpleObjectProperty<Paint>(codeFill.color)
    var codeFill by codeFillProperty
}

class CodeInfoModel(property: ObjectProperty<CodeInfoModelItem>) : ItemViewModel<CodeInfoModelItem>(itemProperty = property) {
    var value = bind(autocommit = true) { item?.valueProperty }
    var bg = bind(autocommit = true) { item?.bgProperty }
    var codeFill = bind(autocommit = true) { item?.codeFillProperty }
}

var codeList = FXCollections.observableArrayList<CodeInfoModelItem>(
       CodeInfoModelItem("for i in 0 until unsortedList.size"),
        CodeInfoModelItem("\tfor j in 0 until unsortedList.size - i -1"),
        CodeInfoModelItem("\t\tif leftElement > rightElement"),
        CodeInfoModelItem("\t\t\tswap(leftElement, rightElement)"))
