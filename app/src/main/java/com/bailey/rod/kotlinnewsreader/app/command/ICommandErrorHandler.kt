package com.bailey.rod.kotlinnewsreader.app.command

/**
 * Invoked by the [CommandEngine] when an [ICommand] experiences an error while executing
 * or handling a returned result.
 */
interface ICommandErrorHandler {

	fun showError()
}