package com.bailey.rod.kotlinnewsreader.app.command

/**
 * A command to be asynchronously executed by the [CommandEngine]. An ICommand is
 * given to the [CommandEngine], along with an [ICommandSuccessHandler],
 * [ICommandProgressMonitor] and [ICommandErrorHandler]. The [execute] method is
 * called on a non-UI thread when passed into the [CommandEngine]
 */
interface ICommand<out T : Any?> {

	/**
	 * Will be called on a non-UI thread (background thread) so that UI responsiveness is
	 * not damaged.
	 */
	fun execute(): T?
}