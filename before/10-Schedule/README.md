# Java EE 6 Advanced Training - Schedule

In this module you will add a scheduler (a robot) that will buy random books for random customers.

# Preparation

## Check

* Check the `generate.fsh` script
* Check the `monitoring.xhtml` page 
* Check the `RobotService` class 

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run before/10-Schedule/generate.fsh` 

# DOJO - Add a monitoring page and service

We want to monitor the activity of the robot (display a message each time a book is bought). For that we need a monitoring page that displays every message.
 
## Add a monitoring page
 
* Check the copied `monitoring.xhtml` page
* In the `resources/scaffold/mainTemplate.xhtml` add a link to the `monitoring.xhtml` page

## Add a monitoring service 

* In `MonitoringBean`
* Change the `messages` attribute from `private List messages` to `private List<String> messages = new ArrayList<>();`
* Create a `public void newMessage(String robotMessage)` that adds the `robotMessage` to the `messages`
* Make sure you can access the `MonitoringService` from the `monitoring.xhtml` page
 
# KATA - Add a monitoring page and service

# DOJO - Add a scheduler that buys random books

## The Robot Service buys books randomly

* Check the copied `RobotService.java` class, it will randomly buy books
* Fix the bugs (it needs a couple of CDI events and a `TimerService`)
* `MonitoringBean` should observe the `Robot` messages

## Build, Deploy and check the web application
                 
* With a browser go to [http://localhost:8080/cdbookstore]()
* View a Book (for example [http://localhost:8080/cdbookstore/faces/book/view.xhtml?id=-12]())
* Click on the _Start_ button, the robot should start buying books

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# Backup your code

* Save a backup of your code at `../after/10-Schedule/`
