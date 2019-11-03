#!/bin/bash
mv plugins/ ../plugins/ # Save the plugins folder, below methode doesn't cover folders.
rm -rf $(find * -name "*" ! -name "Watchdog.jar" ! -name "Procfile" ! -name "plugins/*") # Remove everything except Procfile, Watchdog.jar and plugins folder
mv ../plugins/ . # Save the plugins folder placing it back
java -jar Watchdog.jar # Create needed config files by running Watchdog.jar
