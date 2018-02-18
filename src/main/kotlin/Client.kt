package de.kotlincook.sophiasearch

import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.stage.Stage


interface Completable {
    fun complete(input: String): Collection<String>
}

class App(): Application() {

    companion object {
        lateinit var completer: Completable;
    }

    override fun start(stage: Stage) {
        val edit = TextField()
        val completionsList = ListView<String>()
        val vBox = VBox(edit, completionsList).apply {
            spacing = 10.0
            padding = Insets(10.0)
        }

        edit.textProperty().addListener {
            _, _, input -> completionsList.items.setAll(completer.complete(input))
        }

        stage.scene = Scene(vBox)
        stage.show()
    }
}


fun main(args: Array<String>) {
    val lines = FileUtils.loadResourceLines("/streets.txt").toList()
    App.completer = SophiaSearch(lines)
    Application.launch(App::class.java, *args)
}