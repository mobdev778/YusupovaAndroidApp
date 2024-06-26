package com.github.mobdev778.yusupova.core.router.impl.nodes

import com.github.mobdev778.yusupova.core.router.api.AnyScreenId
import com.github.mobdev778.yusupova.core.router.api.ScreenId

internal class RouterNodeList : ArrayList<RouterNode>(), AutoCloseable {

    var parent: RouterNodeList? = null
    private val screenCounters = HashMap<String, Counter>()

    private var onListChanged: ((RouterNodeList) -> Unit)? = null

    private val commandQueue = CommandQueue { command ->
        when (command) {
            is Command.Back -> handleBackCommand(command)
            is Command.Forward -> handleForwardCommand(command)
            is Command.Update -> handleUpdateCommand(command)
        }
    }

    fun init(headNode: RouterNode, parent: RouterNodeList?, onListChanged: (RouterNodeList) -> Unit) {
        add(headNode)
        this.parent = parent
        this.onListChanged = onListChanged
    }

    fun addCommand(command: Command) {
        commandQueue.addCommand(command)
    }

    override fun close() {
        this.parent = null
        this.onListChanged = null
        commandQueue.close()
    }

    private fun handleBackCommand(command: Command.Back) {
        val reachability = when {
            command.screenId is AnyScreenId -> Reachability.LOCAL
            else -> getReachability(command.screenId)
        }

        when (reachability) {
            Reachability.NONE -> return
            Reachability.LOCAL -> {
                screenCounters.getOrPut(removeLast().screenId.key) { Counter(0) }.count--
                while (size > 1 && ScreenId.Comparator.compare(command.screenId, last().screenId) != 0) {
                    screenCounters.getOrPut(removeLast().screenId.key) { Counter(0) }.count--
                }
            }
            Reachability.GLOBAL -> {
                repeat(size - 1) {
                    removeLast()
                }
            }
        }

        if (size >= 1) {
            last().resultListener.invoke(command.result)
        } else {
            val first = get(0)
            first.backScreenId = command.screenId
            first.result = command.result
        }
    }

    private fun handleForwardCommand(command: Command.Forward) {
        screenCounters.getOrPut(command.node.screenId.key) { Counter(0) }.count++
        last().resultListener = command.node.resultListener
        command.node.resultListener = {}
        add(command.node)
        addCommand(Command.Update())
    }

    private fun handleUpdateCommand(command: Command.Update) {
        when {
            command.isExpired -> {
                Unit
            }
            else -> {
                onListChanged?.invoke(this)
            }
        }
    }

    private fun getReachability(screenId: ScreenId): Reachability {
        val key = screenId.key
        val count = screenCounters[key]?.count ?: 0
        if (count > 0) return Reachability.LOCAL
        return when {
            parent?.getReachability(screenId) != Reachability.NONE -> Reachability.GLOBAL
            else -> Reachability.NONE
        }
    }

    enum class Reachability {
        LOCAL,
        GLOBAL,
        NONE
    }

    class Counter(var count: Int)
}
