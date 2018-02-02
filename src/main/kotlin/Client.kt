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
            spacing = 15.0
            padding = Insets(15.0)
        }

        edit.textProperty().addListener {
            _ -> completionsList.items.setAll(completer.complete(edit.text))
        }

        stage.scene = Scene(vBox)
        stage.show()
    }
}


object TestCompleter : Completable {
    override fun complete(input: String): Collection<String> {
        return listOf("One $input", "Two ${input}s", "Three ${input}s")
    }
}


fun main(args: Array<String>) {
    val fileName = "/streets.txt"
    val ngrams = FileUtils.loadResourceLines(fileName).map { NormedString(it) }.toSortedSet()
    App.completer = SophiSearch(ngrams)
    Application.launch(App::class.java, *args)
}