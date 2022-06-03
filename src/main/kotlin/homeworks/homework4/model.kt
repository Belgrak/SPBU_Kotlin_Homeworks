package homeworks.homework4

import homeworks.homework4.ConstsAndSettings.BIG_STEP
import homeworks.homework4.ConstsAndSettings.HEIGHT
import homeworks.homework4.ConstsAndSettings.LISTS_CHART_NAME_COROUTINES
import homeworks.homework4.ConstsAndSettings.LISTS_CHART_NAME_THREADS
import homeworks.homework4.ConstsAndSettings.LIST_STEP
import homeworks.homework4.ConstsAndSettings.MAX_COUNT
import homeworks.homework4.ConstsAndSettings.MAX_LIST_SIZE
import homeworks.homework4.ConstsAndSettings.MAX_RANDOM_NUM
import homeworks.homework4.ConstsAndSettings.MIDDLE_COUNT
import homeworks.homework4.ConstsAndSettings.MIN_COUNT
import homeworks.homework4.ConstsAndSettings.MIN_LIST_SIZE
import homeworks.homework4.ConstsAndSettings.MIN_RANDOM_NUM
import homeworks.homework4.ConstsAndSettings.SMALL_STEP
import homeworks.homework4.ConstsAndSettings.THREADS_CHART_NAME
import homeworks.homework4.ConstsAndSettings.VERTICAL_OFFSET
import homeworks.homework4.ConstsAndSettings.WIDTH
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomHLine
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomText
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleXContinuous
import jetbrains.letsPlot.scale.scaleYContinuous
import jetbrains.letsPlot.tooltips.layerTooltips
import java.awt.Desktop
import java.io.File
import java.util.Collections.max
import java.util.Collections.min
import kotlin.system.measureTimeMillis

fun timeFromThreadsChart() {
    val list = generateRandomList(MAX_LIST_SIZE)
    val threadsToTime = linkedMapOf<Int, Long>()

    threadsToTime[1] = measureTimeMillis { list.mergeSort() }
    for (threadsCount in MIN_COUNT..MIDDLE_COUNT step SMALL_STEP) {
        threadsToTime[threadsCount] = measureTimeMillis { list.mergeSort(threadsCount) }
    }
    for (threadsCount in MIDDLE_COUNT..MAX_COUNT step BIG_STEP) {
        threadsToTime[threadsCount] = measureTimeMillis { list.mergeSort(threadsCount) }
    }
    val data = mapOf(
        "Threads" to threadsToTime.keys,
        "Time" to threadsToTime.values
    )
    val maxTime = threadsToTime.maxByOrNull { it.value }
    val minTime = threadsToTime.minByOrNull { it.value }

    val plotLine = geomLine(color = "red", tooltips = layerTooltips().format("Time", "{} ms"))
    val minLine = geomHLine(yintercept = min(threadsToTime.values)) +
        geomText(
            label = "Minimum: ${minTime?.key} threads, ${minTime?.value} ms",
            x = MAX_COUNT / 2,
            y = (minTime?.value ?: 0) + VERTICAL_OFFSET
        )
    val maxLine = geomHLine(yintercept = max(threadsToTime.values)) +
        geomText(
            label = "Maximum: ${maxTime?.key} threads, ${maxTime?.value} ms",
            x = MAX_COUNT / 2,
            y = (maxTime?.value ?: 0) - VERTICAL_OFFSET
        )
    val style = scaleYContinuous(format = "{} ms") + scaleXContinuous(breaks = threadsToTime.keys.toList()) +
        ggsize(WIDTH, HEIGHT) + labs(
        title = "Multithreaded merge sort",
        subtitle = "Time dependence on threads count chart. List size = $MAX_LIST_SIZE"
    )
    val plot = letsPlot(data) { x = "Threads"; y = "Time" } + plotLine + minLine + maxLine + style
    val path = ggsave(plot, THREADS_CHART_NAME, path = "src/main/resources")
    val file = File(path)
    Desktop.getDesktop().browse(file.toURI())
}

fun timeFromListsChart(sortMode: SortMode) {
    val listsToTime = linkedMapOf<Int, Long>()
    for (listSize in MIN_LIST_SIZE..MAX_LIST_SIZE step LIST_STEP) {
        listsToTime[listSize] = measureTimeMillis { generateRandomList(listSize).mergeSort(MIDDLE_COUNT, sortMode) }
    }
    val data = mapOf(
        "ListSize" to listsToTime.keys,
        "Time" to listsToTime.values
    )
    val maxTime = listsToTime.maxByOrNull { it.value }
    val minTime = listsToTime.minByOrNull { it.value }

    val plotLine = geomLine(color = "red", tooltips = layerTooltips().format("Time", "{} ms"))
    val minLine = geomHLine(yintercept = min(listsToTime.values)) +
        geomText(
            label = "Minimum: ${minTime?.key} elements, ${minTime?.value} ms",
            x = MAX_LIST_SIZE / 2,
            y = (minTime?.value ?: 0) + VERTICAL_OFFSET
        )
    val maxLine = geomHLine(yintercept = max(listsToTime.values)) +
        geomText(
            label = "Maximum: ${maxTime?.key} elements, ${maxTime?.value} ms",
            x = MAX_LIST_SIZE / 2,
            y = (maxTime?.value ?: 0) - VERTICAL_OFFSET
        )
    val titleAndSubtitle = when (sortMode) {
        SortMode.MULTITHREADED -> labs(
            title = "Multithreaded merge sort",
            subtitle = "Time dependence on list size chart. Threads count = $MIDDLE_COUNT"
        )
        SortMode.ASYNCHRONOUS -> labs(
            title = "Asynchronous merge sort",
            subtitle = "Time dependence on list size chart. Coroutines count = $MIDDLE_COUNT"
        )
    }
    val style = scaleYContinuous(format = "{} ms") + scaleXContinuous(breaks = listsToTime.keys.toList()) +
        ggsize(WIDTH, HEIGHT) + titleAndSubtitle

    val plot = letsPlot(data) { x = "ListSize"; y = "Time" } + plotLine + minLine + maxLine + style
    val path = when (sortMode) {
        SortMode.MULTITHREADED -> ggsave(plot, LISTS_CHART_NAME_THREADS, path = "src/main/resources")
        SortMode.ASYNCHRONOUS -> ggsave(plot, LISTS_CHART_NAME_COROUTINES, path = "src/main/resources")
    }
    val file = File(path)
    Desktop.getDesktop().browse(file.toURI())
}

fun timeFromCoroutinesChart() {
    val list = generateRandomList(MAX_LIST_SIZE)
    val coroutinesToTime = linkedMapOf<Int, Long>()

    coroutinesToTime[1] = measureTimeMillis { list.mergeSort() }
    for (coroutinesCount in MIN_COUNT..MIDDLE_COUNT step SMALL_STEP) {
        coroutinesToTime[coroutinesCount] = measureTimeMillis { list.mergeSort(coroutinesCount, true) }
    }
    for (coroutinesCount in MIDDLE_COUNT..MAX_COUNT step BIG_STEP) {
        coroutinesToTime[coroutinesCount] = measureTimeMillis { list.mergeSort(coroutinesCount, true) }
    }
    val data = mapOf(
        "Coroutines" to coroutinesToTime.keys,
        "Time" to coroutinesToTime.values
    )
    val maxTime = coroutinesToTime.maxByOrNull { it.value }
    val minTime = coroutinesToTime.minByOrNull { it.value }

    val plotLine = geomLine(color = "red", tooltips = layerTooltips().format("Time", "{} ms"))
    val minLine = geomHLine(yintercept = min(coroutinesToTime.values)) +
            geomText(
                label = "Minimum: ${minTime?.key} coroutines, ${minTime?.value} ms",
                x = MAX_COUNT / 2,
                y = (minTime?.value ?: 0) + VERTICAL_OFFSET
            )
    val maxLine = geomHLine(yintercept = max(coroutinesToTime.values)) +
            geomText(
                label = "Maximum: ${maxTime?.key} coroutines, ${maxTime?.value} ms",
                x = MAX_COUNT / 2,
                y = (maxTime?.value ?: 0) - VERTICAL_OFFSET
            )
    val style = scaleYContinuous(format = "{} ms") + scaleXContinuous(breaks = coroutinesToTime.keys.toList()) +
            ggsize(WIDTH, HEIGHT) + labs(
        title = "Asynchronous merge sort",
        subtitle = "Time dependence on coroutines count chart. List size = $MAX_LIST_SIZE"
    )
    val plot = letsPlot(data) { x = "Coroutines"; y = "Time" } + plotLine + minLine + maxLine + style
    val path = ggsave(plot, COROUTINES_CHART_NAME, path = "src/main/resources")
    val file = File(path)
    Desktop.getDesktop().browse(file.toURI())
}

fun generateRandomList(size: Int, start: Int = MIN_RANDOM_NUM, end: Int = MAX_RANDOM_NUM): MutableList<Int> {
    val randomList = mutableListOf<Int>()
    repeat(size) { randomList.add((start..end).random()) }
    return randomList
}

