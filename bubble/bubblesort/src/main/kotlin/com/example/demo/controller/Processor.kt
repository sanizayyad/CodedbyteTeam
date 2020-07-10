package com.example.demo.controller

import com.example.demo.model.*
import com.example.demo.view.InputDialog
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import tornadofx.*
import java.util.*
import tornadofx.getValue
import tornadofx.setValue

class Processor : Controller(){
    val inputMethods = FXCollections.observableArrayList("Random", "Digits", "Alphabets")
    var dropDownItems  = FXCollections.observableArrayList<Any>();

    val isSortingProperty = SimpleBooleanProperty(false)
    var isSorting by isSortingProperty


    var sortingSpeed = SimpleIntegerProperty(5)
    var nSamples = SimpleIntegerProperty(5)
    var type:Type? = Type.RANDOM
    var sampleList = SortedFilteredList<BarItem>(initialList)

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
    fun changeType(typeName:String):Type?{
       when(typeName){
           "Random" -> return Type.RANDOM
           "Digits" -> return Type.DIGITS
           "Alphabets" -> return Type.ALPHABETS
           else -> print("errror type")
       }
        return null
    }
    fun getInput(){
        when(type){
            Type.RANDOM -> {
                var rand = Random().nextInt(2)
                if (rand == 0)digitsDropdown()
                else alphabetDropDown()
            }
            Type.DIGITS -> digitsDropdown()
            Type.ALPHABETS -> alphabetDropDown()
            else -> print("errror type")
        }
        InputDialog(nSamples.value, dropDownItems).openModal()
    }

    fun reset(){
        sortingSpeed.value = 5
        nSamples.value = 5
        sampleList.clear()
    }
    fun shuffle(){
        val rgen = Random()
        for (i in sampleList.indices) {
            val randomPosition = rgen.nextInt(sampleList.size)
            sampleList.swap(i, randomPosition)
            sampleList[i].background = State.NOTACTIVE.background
        }
    }
    fun sort(){
        isSorting = true
            for(i in  0 until sampleList.size){
                for(j in  0 until sampleList.size - i - 1){
                    if (sampleList[j].getValue() > sampleList[j+1].getValue()){
                        sampleList[j].background = State.ACTIVE.background
                        sampleList[j+1].background = State.ACTIVE.background
                        sampleList.swap(j,j+1)
                    }
                }
            }
            sampleList.forEach {
                it.background =  State.SORTED.background
            }
        isSorting = false
    }

    fun reassignList(newList: SortedFilteredList<BarItem>){
        sampleList.clear()
        sampleList.addAll(newList)
        if (isSorted()){
            sampleList.forEach {
                it.background =  State.SORTED.background
            }
        }

    }
    fun isSorted():Boolean{
        for(i in  0 until sampleList.size-1){
            if (sampleList[i].getValue() > sampleList[i+1].getValue())
                return false
        }
        return true
    }
}