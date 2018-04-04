AvaIre Watchdog
============

AvaIre Watchdog is an application built for the Discord bot [AvaIre](https://github.com/avaire/avaire), the app will help manage [AvaIre](https://github.com/avaire/avaire) by starting, restarting and updating the bot, the application also comes with a terminal that allows you to see the output and interact with the bot.

## Installation

#### Server Requirements

Watchdog has a few system requirements, these requirements helps ensure that Watchdog, and all of it's features will work correctly.

 - [Git SCM](https://git-scm.com/)
 - [Gradle](https://gradle.org/) >= 4.4
 - [Java SE / JDK](https://www.oracle.com/index.html) >= 8
 - 128 MB Memory

#### Installing Watchdog

Start by cloning down the source code and building the binary using Gradle.

    git clone https://github.com/avaire/watchdog.git
    cd watchdog
    gradle build

The binary file can be found within the root of the `watchdog` directory, next we'll need to run Watchdog so it can create our [avaire](https://github.com/avaire/avaire) binary file, to do this run:

    java -jar Watchdog.jar

Once all the source code has been cloned down and the binary has been compiled, watchdog will automaticlly start trying to boot up the bot, the first time this happens it will fail since the configuration file has yet to be generated, once the process stops, open up the newly generated `config.yml` file and setup you discord and database settings, everything else is optional. When you're done configuring the bot, run Watchdog again to make it start the [avaire](https://github.com/avaire/avaire) process and watch for any commands Ava might sent its way.

If the bot starts up normally you've successfully setup Watchdog to run [avaire](https://github.com/avaire/avaire)!

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
