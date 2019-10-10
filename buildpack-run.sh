#!/bin/bash
rm -rf $(find * -name "*" ! -name "Watchdog.jar" ! -name "Procfile" ! -name "plugins/*") # Remove everything except Procfile, Watchdog.jar and plugins folder
java -jar Watchdog.jar # Create needed config files by running Watchdog.jar
