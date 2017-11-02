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
    var train1 = Train(schedule = mutableListOf(5, 2, 3, 0, 1))
    var train2 = Train(schedule = mutableListOf(4, 0, 0, 3, 4))
    var train3 = Train(schedule = mutableListOf(1, 2, 3, 4, 5))
    var train4 = Train(schedule = mutableListOf(5, 4, 3, 2, 1))
    var train5 = Train(schedule = mutableListOf(0, 0, 5, 5, 2))
    var train6 = Train(schedule = mutableListOf(3, 4, 5, 1, 2))
    var train7 = Train(schedule = mutableListOf(2, 1, 1, 1, 3))
    var train8 = Train(schedule = mutableListOf(1, 1, 0, 0, 0))
    var train9 = Train(schedule = mutableListOf(4, 3, 2, 5, 5))
    var train10 = Train(schedule = mutableListOf(1, 5, 4, 4, 4))
    var train11 = Train(schedule = mutableListOf(2, 0, 4, 3, 3))
    var train12 = Train(schedule = mutableListOf(3, 0, 0, 0, 0))
    var trains = mutableListOf(train1, train2, train3, train4, train5, train6, train7, train8, train9, train10, train11, train12)

    var network = Network(3, trains, 5,mutableListOf(2, 3, 3, 3, 3))
    network.simulateOneStep()
    writeResultstoConsole(trains, network)
}


fun external() {
    var trains: MutableList<Train> = parseScheduleCSV()
    var segments: MutableList<Int> = parseSegmentsCSV()
    var network = Network(3, trains, trains[0].getScheduleLenght(), segments)
    network.simulateOneStep()
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


        var segmentsChoiceStr: String = record.values[1]
        var segmentsChoiceArray: List<String> = segmentsChoiceStr.split(";")

        var segmentsChoiceList: MutableList<Int> = mutableListOf()

        for (segment in segmentsChoiceArray) {
            val segmentInt: Int = segment.toInt()
            segmentsChoiceList.add(segmentInt)
        }

        trainsCSV.add(Train(schedule = segmentsChoiceList))

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
    val trainrows: MutableList<Array<Any>> = mutableListOf()
    var i: Int = 0
    var row: Array<Any> = arrayOf("train number", "delayed/on time")
    trainrows.add(row)

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

    for (result in results) {

        i++

        if (result.getDelayed()) {
            row = arrayOf(i.toString(), "delayed")
        } else {
            row = arrayOf(i.toString(), "ontime")
        }
        trainrows.add(row)
    }

    csvWriter.writeRowsAndClose(trainrows)
}

fun writeResultstoConsole(results: List<Train>, network: Network) {

    var i: Int = 0
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

    for (result in results) {

        i++

        if (result.getDelayed()) {
            println(i.toString() + " - delayed")
        } else {
            println(i.toString() + " - ontime")
        }

    }

}

