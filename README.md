DayOneAutoEntry
===============

A Scala project designed to create a journal template for Day One.

##Usage

First, create an executable jar file (that includes all dependencies) by navigating to the root project directory and typing (note: you must have gradle installed):
> gradle build

On your computer, create a config folder.  Copy the two files from src/main/resources/config into it:
MostRecentEntryMadeOn.txt
template.txt

Edit the template.txt file with the text you would like to have as your journal template.

The project can now be scheduled using crontab.  In order execute the code, type:
java -cp <path_to_DayOneAutoEntry_jar_file>:<path_to_the_config_directory> net.bitzoo.dayone.AutoEntry