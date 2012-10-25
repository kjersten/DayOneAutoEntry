import org.joda.time.DateTime
import scala.sys.process._

object AutoEntry extends App {

  val today = DateTime.now().toString("yyyy-MM-dd")
  val dateFileName = "MostRecentEntryMadeOn.txt"
  val templateFileName = "template.txt"
    
  val dateFile = scala.io.Source.fromInputStream(ClassLoader.getSystemResourceAsStream(dateFileName))
  val mostRecentEntryDate = dateFile.mkString
  dateFile.close()

  if (!today.equals(mostRecentEntryDate)) {
    // create a new entry in DayOne with the contents of the template file
    "/usr/local/bin/dayone new" #< new java.io.File(ClassLoader.getSystemResource(templateFileName).toURI()) !

    // record the fact that my template file has been created
    AutoEntryUtil.writeToFile(dateFileName, today)
  }

}

