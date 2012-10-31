package net.bitzoo.dayone {

  import org.joda.time.DateTime
  import scala.sys.process._

  object AutoEntry extends App {

    // use to debug the classpath
//    val urls = ClassLoader.getSystemClassLoader().asInstanceOf[java.net.URLClassLoader].getURLs()
//    for (url <- urls) { Console.println(url) }

    val today = DateTime.now().toString("yyyy-MM-dd")
    val templateFileName = "config/template.txt"
    val dateFileName = "config/MostRecentEntryMadeOn.txt"
    val dateFile = new java.io.File(ClassLoader.getSystemResource(dateFileName).toURI())

    val dateFileStream = scala.io.Source.fromFile(dateFile)
    val mostRecentEntryDate = dateFileStream.mkString
    dateFileStream.close()

    if (!today.equals(mostRecentEntryDate)) {

      // create a new entry in DayOne with the contents of the template file
      val swallowOutput = "/usr/local/bin/dayone new" #<
        new java.io.File(ClassLoader.getSystemResource(templateFileName).toURI()) !

      // record the fact that my template file has been created
      AutoEntryUtil.writeToFile(dateFile, today)
    }

  }

}

