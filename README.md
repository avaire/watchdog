AvaIre Watchdog
============

AvaIre Watchdog is an application built for the Discord bot [AvaIre](https://github.com/avaire/avaire), the app will help manage [AvaIre](https://github.com/avaire/avaire) by starting, restarting and updating the bot, the application also comes with a terminal that allows you to see the output and interact with the bot.

## Installation

#### Server Requirements

Watchdog has a few system requirements, these requirements helps ensure that Watchdog, and all of it's features will work correctly.

 - [Java SE / JDK](https://www.oracle.com/index.html) >= 9
 - 128 MB Memory

#### Installing Watchdog

Start by cloning down the source code and building the binary using Gradle, optionally you can also use one of the [releases](https://github.com/avaire/watchdog/releases).

    git clone https://github.com/avaire/watchdog.git
    cd watchdog
    gradle build

The binary file can be found within the root of the `watchdog` directory, next we'll need to run Watchdog so it can download our [avaire](https://github.com/avaire/avaire) jar file, to do this run:

    java -jar Watchdog.jar

Once Watchdog has downloaded the jar file watchdog will automatically start trying to boot up the bot, the first time this happens it will fail since the configuration file has yet to be generated, once the process stops, open up the newly generated `config.yml` file and setup you discord and database settings, everything else is optional. When you're done configuring the bot, run Watchdog again to make it start the [avaire](https://github.com/avaire/avaire) process and watch for any commands Ava might send its way.

If the bot starts up normally you've successfully setup Watchdog to run [avaire](https://github.com/avaire/avaire)!

> **Note:** If you would like to start Ava with some different options you can pass then as arguments to Watchdog, for example to see what custom options [avaire](https://github.com/avaire/avaire) supports you can try using the `--help` argument.

    java -jar Watchdog.jar --help

## Deploy AvaIre using Watchdog on Heroku
You can deploy in a simple way to Heroku using the button below.

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

Before deploying, read [the configuration guide](https://avairebot.com/docs/master/configuration/), aswell as [the guide](https://avairebot.com/docs/master/heroku) regarding hosting with Heroku.

## License

AvaIre Watchdog is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).

---

<p align="center">
  <a href="https://discord.gg/gt2FWER"><img src="https://discordapp.com/api/guilds/284083636368834561/widget.png?style=banner2" alt="Discord server"></a>
  <br>AvaIre support server: https://discord.gg/gt2FWER
</p>

---

<p align="center">
    "Discord", "Discord App", and any associated logos are registered trademarks of Hammer & Chisel, inc.
</p>
