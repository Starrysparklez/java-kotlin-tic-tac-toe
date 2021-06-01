package ru.nellyd3v.tictactoe_fx.code

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.stage.Stage

class RestartModalController {
    @FXML
	private fun exitGame() { Main.window.close() }

	@FXML
	private fun restart(event: ActionEvent) {
		Data.matrix = arrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
		for (x in 0..8) { (Main.window.scene.lookup("#$x") as Button).text = " " }
		Data.gameEnd = false
		((event.source as Node).scene.window as Stage).close()
	}
}