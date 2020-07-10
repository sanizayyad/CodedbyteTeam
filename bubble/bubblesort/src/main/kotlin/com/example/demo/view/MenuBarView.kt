package com.example.demo.view
import com.example.demo.controller.MenuBarController
import tornadofx.*

class MenuBarView : View(){
    val menuBarController: MenuBarController by inject()

    override val root =  menubar {
        menu("File") {
            item("Save", "Shortcut+S").action {
                action {
                   menuBarController.save(this@MenuBarView)
                }
            }
            separator()
            item("Quit", "Shortcut+Q").action {
                super.close()
            }
        }
        menu("Help") {
            item("About").action {
                AboutView().openModal()
            }
            item("Authors").action {
                AuthorsView().openModal()
            }
        }
    }
}
