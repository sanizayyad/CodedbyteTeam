package com.example.demo.controller

import com.example.demo.model.BarItem
import com.example.demo.model.Type
import com.example.demo.view.InputDialog
import javafx.collections.FXCollections
import tornadofx.*
import java.util.*

class InputController :Controller(){
    val processor: Processor by inject()

    val inputMethods = FXCollections.observableArrayList("Random", "Digits", "Alphabets")
    var dropDownItems  = FXCollections.observableArrayList<Any>();

    var type:Type? = Type.RANDOM
    private fun randomDropDown(){
        if (Random().nextInt(2) == 0){
            val buffList = FXCollections.observableArrayList<BarItem>()
            for (i in 0 until processor.nSamples.value){
                buffList.add(BarItem(Random().nextInt(20)))
            }
            processor.reassignList(SortedFilteredList<BarItem>(buffList))
        }
        else{
            val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            val buffList = FXCollections.observableArrayList<BarItem>()
            for (i in 0 until processor.nSamples.value){
                buffList.add(BarItem(upper[Random().nextInt(26)]))
            }
            processor.reassignList(SortedFilteredList<BarItem>(buffList))
        }
    }
    private fun digitsDropdown(){
        dropDownItems.clear()
        for (i in (1..20)){
            dropDownItems.add(i)
        }
    }
    private fun alphabetDropDown(){
        dropDownItems.clear()
        for (i in ('A'..'Z')){
            dropDownItems.add(i)
        }
    }
    fun changeType(typeName:String): Type?{
        when(typeName){
            "Random" -> return Type.RANDOM
            "Digits" -> return Type.DIGITS
            "Alphabets" -> return Type.ALPHABETS
            else -> print("errror type")
        }
        return null
    }
    fun getInput(){
        if(type == Type.RANDOM){
            randomDropDown()
        }else{
            when(type){
                Type.DIGITS -> digitsDropdown()
                Type.ALPHABETS -> alphabetDropDown()
                else -> print("errror type")
            }
            InputDialog(processor.nSamples.value, dropDownItems).openModal()
        }
    }
}