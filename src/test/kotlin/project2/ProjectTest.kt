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
        assertTrue(trainsCSV.size > 0)
    }
    @Test
    fun getSegmentCapacityTest() {
        var segment:Segment = Segment(42, 0)
        assertEquals(42, segment.id)
    }
    @Test
    fun checkInitialTrainTest(){
        var train:Train = Train(mutableListOf(1, 2, 3),1,42)
        assertFalse(train.getDelayed())
        assertEquals(1, train.getCurrentSegment(0))
        assertEquals(2, train.getCurrentSegment(1))
        assertEquals(3, train.getCurrentSegment(2))
    }
}



