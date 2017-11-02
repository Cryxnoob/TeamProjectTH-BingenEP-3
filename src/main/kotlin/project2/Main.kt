package project2

import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.common.record.Record
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings

fun main(args: Array<String>) {

    scneario()
    external()

}

fun scneario() {
    var train1 = Train(schedule = mutableListOf(5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0, 1, 5, 2, 3, 0), priority = 1, id = 100)
    var train2 = Train(schedule = mutableListOf(4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3, 4, 4, 0, 0, 3), priority = 2, id = 101)
    var train3 = Train(schedule = mutableListOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4), priority = 3, id = 102)
    var train4 = Train(schedule = mutableListOf(5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2, 1, 5, 4, 3, 2), priority = 1, id = 103)
    var train5 = Train(schedule = mutableListOf(0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5, 2, 0, 0, 5, 5), priority = 2, id = 104)
    var train6 = Train(schedule = mutableListOf(3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1), priority = 3, id = 105)
    var train7 = Train(schedule = mutableListOf(2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 1, 1), priority = 1, id = 106)
    var train8 = Train(schedule = mutableListOf(1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0), priority = 2, id = 107)
    var train9 = Train(schedule = mutableListOf(4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5, 5, 4, 3, 2, 5), priority = 3, id = 108)
    var train10 = Train(schedule = mutableListOf(1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4, 4, 1, 5, 4, 4), priority = 1, id = 109)
    var train11 = Train(schedule = mutableListOf(2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3, 3, 2, 0, 4, 3), priority = 2, id = 110)
    var train12 = Train(schedule = mutableListOf(3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0), priority = 3, id = 111)
    var trains = mutableListOf(train1, train2, train3, train4, train5, train6, train7, train8, train9, train10, train11, train12)

    var network = Network(3, trains, 64, mutableListOf(2, 3, 3, 3, 3))

    network.runSimulation()

    writeResultsToConsole(trains, network)

}


fun external() {
    var trains: MutableList<Train> = parseScheduleCSV()
    var segments: MutableList<Int> = parseSegmentsCSV()
    var network = Network(3, trains, trains[0].getScheduleLength(), segments)
    network.runSimulation()
    saveResultsToCSV(trains, network = network)

}

fun parseScheduleCSV(): MutableList<Train> {
    var trainsCSV: MutableList<Train> = mutableListOf()
    var settings = CsvParserSettings()
    settings.format.setLineSeparator("\n")
    settings.isHeaderExtractionEnabled = true

    var csvParser = CsvParser(settings)
    var reader = FileHandler().getReader("/schedule.csv")
    var allRows: MutableList<Record> = csvParser.parseAllRecords(reader)


    for (record in allRows) {

        val segmentChoiceStr: String = record.values[1]
        val segmentChoiceArray: List<String> = segmentChoiceStr.split(";")

        val priority: Int = record.values[2].toInt()
        val id: Int = record.values[0].toInt()

        val segmentChoiceList: MutableList<Int> = mutableListOf()

        for (segment in segmentChoiceArray) {
            val segmentInt: Int = segment.toInt()
            segmentChoiceList.add(segmentInt)
        }

        trainsCSV.add(Train(schedule = segmentChoiceList, priority = priority, id = id))

    }
    return trainsCSV
}

fun parseSegmentsCSV(): MutableList<Int> {
    var segmentsCSV: MutableList<Int> = mutableListOf()
    var settings = CsvParserSettings()
    settings.format.setLineSeparator("\n")
    settings.isHeaderExtractionEnabled = true

    var csvParser = CsvParser(settings)
    var reader = FileHandler().getReader("/segments.csv")
    var allRows: MutableList<Record> = csvParser.parseAllRecords(reader)


    for (record in allRows) {


        var segmentCapacity: String = record.values[1]


        segmentsCSV.add(segmentCapacity.toInt())

    }
    return segmentsCSV
}

fun saveResultsToCSV(results: List<Train>, outputFile: String = "results.csv", network: Network) {


    val writer = FileHandler().getWriter(outputFile)

    val csvWriter = CsvWriter(writer, CsvWriterSettings())


    // Write the record headers of this file
    val trainRows: MutableList<Array<Any>> = mutableListOf()
    var row: Array<Any> = arrayOf("Train number", "schedule history")
    trainRows.add(row)
    for (result in results) {
        row = arrayOf(result.id.toString(), result.getScheduleDriven().joinToString(", ").replace("-1", "delayed"))
        trainRows.add(row)
    }

    csvWriter.writeRowsAndClose(trainRows)

    // segment capacities:
    val segmentWriter = FileHandler().getWriter("segmentcapacities.csv")

    val segmentCsvWriter = CsvWriter(segmentWriter, CsvWriterSettings())

    var segmentCapacities = network.getSegmentsCapacityTracking()

    // Write the record headers of this file
    val segmentRows: MutableList<Array<Any>> = mutableListOf()
    var segmentRow: Array<Any> = arrayOf("Segment number", "capacities remaining")
    segmentRows.add(segmentRow)
    var j: Int = 1
    for(capacities in segmentCapacities) {
        var resultString = ""
        for (capacity in capacities) {
            resultString = resultString + capacity.toString() + ";"
        }
        segmentRow = arrayOf("Segment #" + j, resultString)
        segmentRows.add(segmentRow)
        j++
    }
    segmentCsvWriter.writeRowsAndClose(segmentRows)
}

fun writeResultsToConsole(results: List<Train>, network: Network) {

    for (result in results) {
        println("Train #" + result.id + " drove the following schedule: " + result.getScheduleDriven().joinToString(", ").replace("-1", "delayed"))
    }

    var segmentCapacities = network.getSegmentsCapacityTracking()
    var x: Int = 1
    for(capacities in segmentCapacities) {
        var resultString = "Segment #" + x.toString() + " remaining capacities: "
        for (capacity in capacities) {
            resultString = resultString + capacity.toString() + " - "
        }
        println(resultString)
        x++
    }
}

