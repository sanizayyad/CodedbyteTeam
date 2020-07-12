package com.example.demo.controller

import com.example.demo.model.*
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.util.Duration.seconds
import tornadofx.*
import java.lang.Exception
import java.util.*


class Processor : Controller(){
    var codeInfo = SimpleStringProperty("List is empty")
    var isSorted = SimpleBooleanProperty(true)
    var isSorting = SimpleBooleanProperty(false)
    var sortingSpeed = SimpleIntegerProperty(10)
    var nSamples = SimpleIntegerProperty(5)
    var sampleList = SortedFilteredList<BarItem>()
    private var timeline:Timeline = Timeline()


    fun reset(){
        timeline.stop()
        i = 0
        j = 0
        isSorting.value = false
        isSorted.value = false
        sortingSpeed.value = 10
        nSamples.value = 5
        codeState(-1)
        codeInfo.value = "list is empty"
        sampleList.clear()
    }
    fun shuffle(){
        val rgen = Random()
        for (i in sampleList.indices) {
            val randomPosition = rgen.nextInt(sampleList.size)
            sampleList.swap(i, randomPosition)
        }
        timeline.stop()
        i = 0
        j = 0
       if(!checkSorted()){
           isSorted.value = false
           codeInfo.value = "list is unsorted"
           sampleList.forEach {
               it.background = State.NOTACTIVE.background
           }
       }
    }

    private  fun codeState(index:Int){
        codeList.forEach {
            it.bg = CodeBg.NOTACTIVE.color
            it.codeFill = CodeFill.NOTACTIVE.color
        }
        try {
            codeList[index].bg = CodeBg.ACTIVE.color
            codeList[index].codeFill = CodeFill.ACTIVE.color
        }catch (e: Exception) { }
    }
    private fun changeBarState(type:String, pos:Int = 0){
        if(type == "active"){
            sampleList[j].background = State.ACTIVE.background
            sampleList[j + 1].background = State.ACTIVE.background
        }else if(type == "activeComplete"){
            try {
                sampleList[pos -1].background = State.NOTACTIVE.background
                sampleList[pos - 2].background = State.NOTACTIVE.background
            }
            catch (e: Exception) { }
        }else{
            try {
                sampleList[sampleList.size - i - 1].background = State.SORTED.background
                sampleList[sampleList.size - i - 2].background = State.NOTACTIVE.background
            }
            catch (e: Exception) { }finally {
                j = 0
                i++
            }
        }

    }
    var i = 0
    var j = 0

    fun stepSort() {
        val speed = (sortingSpeed.value.toDouble()+3)/100
        isSorting.value = true
        if (i < sampleList.size) {
            codeState(0)
            if(j < sampleList.size - i - 1){
                codeState(1)
                val jTemp = j
                val timeline = Timeline(KeyFrame(seconds(speed), EventHandler {
                    codeInfo.value = "Checking if ${sampleList[j].value} > ${sampleList[j + 1].value} and swap them if that is true.."
                    changeBarState("active")
                    codeState(2)
                    val a = sampleList[j].getValue()
                    val b = sampleList[j+1].getValue()
                    if (a > b) {
                        sampleList.swap(j, j + 1)
                        codeState(3)
                    }
                    j++
                }))
                timeline.play()
                timeline.setOnFinished {
                    changeBarState("activeComplete", jTemp)
                }
            }else{
                changeBarState("sorted")
            }
        } else {
            i = 0
            j = 0
            isSorting.value = false
            isSorted.value = true
            codeInfo.value = "List is sorted"
            codeState(-1)
            println("finished")
        }

    }
    fun sort(){
        val speed = ((sortingSpeed.value.toDouble()+3)/100) * sampleList.size
        if (!isSorting.value){
            timeline = Timeline(
                    KeyFrame(seconds(speed), EventHandler {
                        stepSort()
                    }))
            val comparisons = ((sampleList.size + 1) * sampleList.size)/2
            timeline.cycleCount = comparisons + 1
            timeline.play()
        }

    }

    fun reassignList(newList: SortedFilteredList<BarItem>){
        sampleList.clear()
        sampleList.addAll(newList)
        if (checkSorted()){
            isSorted.value = true
            codeInfo.value = "list is sorted"
            sampleList.forEach {
                it.background =  State.SORTED.background
            }
        }else{
            isSorted.value = false
            codeInfo.value = "list is unsorted"
        }

    }
    private fun checkSorted():Boolean{
        for(i in  0 until sampleList.size-1){
            if (sampleList[i].getValue() > sampleList[i+1].getValue())
                return false
        }
        isSorted.value = true
        return true
    }
}