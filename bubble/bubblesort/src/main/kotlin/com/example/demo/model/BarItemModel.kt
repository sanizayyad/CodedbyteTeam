package com.example.demo.model

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import tornadofx.*
import java.util.*
import tornadofx.getValue
import tornadofx.setValue

enum class State(val background: Background) {
    ACTIVE(Background(BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY))),
    NOTACTIVE(Background(BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY))),
    SORTED(Background(BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)))
}

enum class Type{
    DIGITS,
    ALPHABETS,
    RANDOM
}

class BarItem(value: Any,state: State = State.NOTACTIVE) {
    val valueProperty = SimpleObjectProperty<Any>(value)
    var value by valueProperty

    val backgroundProperty = SimpleObjectProperty<Background>(state.background)
    var background by backgroundProperty


    fun getValue():Double{
        if(value is Char)
            return (value as Char).toInt().toDouble() - 64.0
        else if(value is Int)
            return  (value as Int).toDouble()
        return 0.0
    }
}

class BarItemModel(property: ObjectProperty<BarItem>) : ItemViewModel<BarItem>(itemProperty = property) {
    var value = bind(autocommit = true) { item?.valueProperty }
    var background = bind(autocommit = true) { item?.backgroundProperty }
}
var initialList = FXCollections.observableArrayList<BarItem>(
        BarItem(1),
        BarItem(4),
        BarItem(3),
        BarItem(5),
        BarItem(2)
)