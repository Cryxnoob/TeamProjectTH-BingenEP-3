package project2

import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.common.record.Record
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings

fun main(args:Array<String>) {

    loadCSV()

}


fun loadCSV(){
    var trains:MutableList<Train> = parseCSV()
    runScenario(trains)
}

fun runScenario(trains:MutableList<Train>){
    var network = Network(3, trains, 5)
    network.createSegments()
    network.simulateoneStep()
    safeResultsToCSV(trains)
}

fun parseCSV():MutableList<Train> {
    var trainsCSV:MutableList<Train> = mutableListOf()
    var settings = CsvParserSettings()
    settings.format.setLineSeparator("\n")
    settings.isHeaderExtractionEnabled = true

    var csvParser = CsvParser(settings)
    var reader = FileHandler().getReader("/schedule.csv")
    var allRows: MutableList<Record> = csvParser.parseAllRecords(reader)


    for (record in allRows){


        var segmentschoiceStr : String = record.values[1]
        var segmentschoiceArray: List<String> = segmentschoiceStr.split(";")

        var segmentschoiceList: MutableList<Int> = mutableListOf()

        for (segment in segmentschoiceArray) {
            val segmentInt: Int = segment.toInt()
            segmentschoiceList.add(segmentInt)
        }

        trainsCSV.add(Train(schedule = segmentschoiceList))

    }
    return trainsCSV
}
fun safeResultsToCSV(results: List<Train>, outputFile: String = "results.csv") {
    val writer = FileHandler().getWriter(outputFile)

    val csvWriter = CsvWriter(writer, CsvWriterSettings())



    // Write the record headers of this file
    val trainrows: MutableList<Array<Any>> = mutableListOf()
    var i:Int = 0
    var row:Array<Any> = arrayOf("train number", "segment", "delayed/on time")
    trainrows.add(row)
    for (result in results){

        i++

        if (result.getDelayed()){
            row = arrayOf(i.toString(), result.getcurrentSegment().toString(), "delayed")
        }
        else {
            row = arrayOf(i.toString(), result.getcurrentSegment().toString(), "ontime")
        }
        trainrows.add (row)
    }

    csvWriter.writeRowsAndClose(trainrows)
}