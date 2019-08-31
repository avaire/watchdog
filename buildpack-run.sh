# Cleaning build artifacts
mv Watchdog.jar ../Watchdog.jar
mv Procfile ../Procfile
rm -r *
mv ../Watchdog.jar .
mv ../Procfile .
# Adding in empty config files
curl -o config.yml https://raw.githubusercontent.com/avaire/avaire/master/src/main/resources/config.yml
curl -o constants.yml https://raw.githubusercontent.com/avaire/avaire/master/src/main/resources/constants.yml