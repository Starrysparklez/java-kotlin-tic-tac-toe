package ru.starrysparklez.tictactoe_fx

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Modality
import javafx.stage.Stage
import kotlin.random.Random

class Controller {
	private val playerChar = 'x'
	private val botChar = 'o'

	private fun gameEnded(player: Char?) {
		Data.gameEnd = true

		val restartModal = Stage()
		restartModal.initOwner(Main.window)
		restartModal.initModality(Modality.APPLICATION_MODAL)
		restartModal.isResizable = false
		restartModal.title = "Игра окончена."
		restartModal.scene = Scene(FXMLLoader.load(
				javaClass.classLoader.getResource("restartModal.fxml")
		), 250.0, 100.0)
		if (player != null)
				(restartModal.scene.lookup("#text") as Label).text = "Победитель: $player\nВаше действие?"
		else (restartModal.scene.lookup("#text") as Label).text = "Ничья\nВаше действие?"

		restartModal.show()
	}

    private fun checkWin(player: Char) {
		if (Data.gameEnd) return

		// shit code oh yeah o_o
		if (Data.matrix[0] == player && Data.matrix[1] == player && Data.matrix[2] == player) gameEnded(player)
		if (Data.matrix[3] == player && Data.matrix[4] == player && Data.matrix[5] == player) gameEnded(player)
		if (Data.matrix[6] == player && Data.matrix[7] == player && Data.matrix[8] == player) gameEnded(player)
		if (Data.matrix[0] == player && Data.matrix[4] == player && Data.matrix[8] == player) gameEnded(player)
		if (Data.matrix[6] == player && Data.matrix[4] == player && Data.matrix[2] == player) gameEnded(player)
		if (Data.matrix[0] == player && Data.matrix[3] == player && Data.matrix[6] == player) gameEnded(player)
		if (Data.matrix[1] == player && Data.matrix[4] == player && Data.matrix[7] == player) gameEnded(player)
		if (Data.matrix[2] == player && Data.matrix[5] == player && Data.matrix[8] == player) gameEnded(player)
	}

	private fun makeBotStep() {
		if (Data.gameEnd) return

		var botSelectedPosition: Int
		if (Data.matrix[0] != ' ' && Data.matrix[0] != ' ' && Data.matrix[2] != ' '
				&& Data.matrix[3] != ' ' && Data.matrix[4] != ' ' && Data.matrix[5] != ' '
				&& Data.matrix[6] != ' ' && Data.matrix[7] != ' ' && Data.matrix[8] != ' ') {
			Data.gameEnd = true
			gameEnded(null)
			return
		}
		while (true) {
			botSelectedPosition = Random.nextInt(9)
			if (Data.matrix[botSelectedPosition] == ' ') break
		}
		Data.matrix[botSelectedPosition] = botChar
		(Main.window.scene.lookup("#$botSelectedPosition") as Button).text = botChar.toString()
		checkWin(botChar) // check if the bot win
	}

    @FXML
    private fun controllerClicked(event: ActionEvent) {
		if (Data.gameEnd) return // if the game has been ended, then do nothing :shrug:

		val button = event.target as Button
        val position = Integer.parseInt(button.id)

		if (Data.matrix[position] != ' ') return // if current position is not empty, then do nothing

		Data.matrix[position] = playerChar
		button.text = playerChar.toString()
		checkWin(playerChar) // check if player win
		makeBotStep()
    }
}