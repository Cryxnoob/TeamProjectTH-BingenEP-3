package project2

import com.univocity.parsers.common.record.Record
import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import org.junit.Test
import org.junit.Assert.*


class ProjectTest {
    @Test
    fun canParseCSVTest() {
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
        assertTrue(trainsCSV.size > 0)
    }
    @Test
    fun getSegmentCapacityTest() {
        var segment:Segment = Segment(42, 0)
        assertEquals(42, segment.id)
    }
    @Test
    fun checkInitialTrainTest(){
        var train:Train = Train(mutableListOf(1, 2, 3))
        assertFalse(train.getDelayed())
        assertEquals(1, train.getCurrentSegment(0))
        assertEquals(2, train.getCurrentSegment(1))
        assertEquals(3, train.getCurrentSegment(2))
    }
}

