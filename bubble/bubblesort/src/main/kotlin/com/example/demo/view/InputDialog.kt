package com.example.demo.view
import com.example.demo.controller.Processor
import com.example.demo.model.BarItem
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Pos
import tornadofx.*

class InputDialog(nSamples: Int, dropDownItems: Any) : View("Input Dialog") {
    val processor: Processor by inject()
    var dropDown =  dropDownItems as ObservableList<Any>?
    var buffList = FXCollections.observableArrayList<BarItem>();

    override val root = vbox (25){
        style{
            padding = box(20.px)
        }
        label("Enter your inputs : ") {

        }
        hbox(10) {
            for (i in 0 until nSamples){
                if (dropDown?.get(0) is Int)
                    buffList.add(BarItem(1))
                else
                    buffList.add(BarItem('A'))
                combobox<Any> {
                    items = dropDown
                    selectionModel.selectFirst()
                }.valueProperty().addListener { observable, oldValue, newValue
                    -> if(newValue != null) buffList[i] = BarItem(newValue)
                }
            }

        }

        hbox(15) {
            alignment = Pos.BASELINE_RIGHT
            button("Cancel") {
                action {
                    super.close()
                }
                setPrefSize(100.0, 40.0)
            }
            button("Ok") {
                action {
                    processor.reassignList(SortedFilteredList<BarItem>(buffList))
                    buffList.clear()
                    super.close()
                }
                setPrefSize(100.0, 40.0)
            }
        }
    }
}
