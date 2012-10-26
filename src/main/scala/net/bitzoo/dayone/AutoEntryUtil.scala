package net.bitzoo.dayone

object AutoEntryUtil {

  // from http://stackoverflow.com/questions/4604237/how-to-write-to-a-file-in-scala

def using[A <: {def close(): Unit}, B](param: A)(f: A => B): B =
try { f(param) } finally { param.close() }

//def writeToFile(fileName:String, data:String) = 
//  using (new java.io.FileWriter(fileName)) {
//    fileWriter => fileWriter.write(data)
//  }

def writeToFile(fileName:java.io.File, data:String) = 
  using (new java.io.FileWriter(fileName)) {
    fileWriter => fileWriter.write(data)
  }

}