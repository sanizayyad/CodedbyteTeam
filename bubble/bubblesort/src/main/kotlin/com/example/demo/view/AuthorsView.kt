package com.example.demo.view

import com.example.demo.app.Styles
import tornadofx.*

class AuthorsView : View("Authors") {

    override val root = vbox (){
       style{
           padding = box(20.px)
       }

        label("Состав команды:"){
            addClass(Styles.heading)
        }
        label("Сани Заяд, группа 8304"){
            addClass(Styles.authorsInfo)
        }
        label("Нгуен Ши Хай, группа 8381"){
            addClass(Styles.authorsInfo)
        }
        label("Распределение ролей:"){
            addClass(Styles.heading)
        }
        label("Сани Заяд : лидер, алгоритмист, тестировщик, фронтенд:"){
            addClass(Styles.authorsInfo)
        }
        label("Нгуен Хай : алгоритмист, тестировщик, фронтенд, документация"){
            addClass(Styles.authorsInfo)
        }
    }
}
