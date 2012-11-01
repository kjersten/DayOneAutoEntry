package net.bitzoo.dayone {

  import org.joda.time.DateTime
  import scala.sys.process._

  object AutoEntry extends App {

    val today = DateTime.now().toString("yyyy-MM-dd")
    val templateFileName = "config/template.txt"
    val dateFileName = "config/MostRecentEntryMadeOn.txt"
      
    // find out when the most recent DayOne template entry was made
    val dateFile = new java.io.File(ClassLoader.getSystemResource(dateFileName).toURI())
    val dateFileStream = scala.io.Source.fromFile(dateFile)
    val mostRecentEntryDate = dateFileStream.mkString
    dateFileStream.close()

    // if a DayOne template entry wasn't made today...
    if (!today.equals(mostRecentEntryDate)) {
      
      // create a new entry in DayOne with the contents of the template file
      // write the output to a dead-end byte stream so nothing is written to console/sent to mail
      "/usr/local/bin/dayone new" #<
        new java.io.File(ClassLoader.getSystemResource(templateFileName).toURI()) #>
      	new java.io.ByteArrayOutputStream() !

      // record the fact that the template entry has been created
      AutoEntryUtil.writeToFile(dateFile, today)
    }

  }

}

