package com.example.demo.view
import tornadofx.*

class MenuBarView : View(){
    override val root =  menubar {
        menu("File") {
            item("Save", "Shortcut+S").action {
                println("Saving!")
            }
            separator()
            item("Quit", "Shortcut+Q").action {
                println("Quitting!")
            }
        }
        menu("Help") {
            item("About").action { }
            item("Authors").action { }
        }
    }
}
