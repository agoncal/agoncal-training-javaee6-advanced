# Java EE 6 Advanced Training - Schedule

In this module you will add a scheduler (a robot) that will buy random books for random customers.

## Generate the project with JBoss Forge

* Launch JBoss Forge (enter the `$FORGE_HOME/bin/forge` command)
* Go to the `cdbookstore` directory
* Execute the `generate.fsh` script with the command `run before/09-Schedule/generate.fsh` 

# DOJO - Add a monitoring page 

We want to monitor the activity of the robot (display a message each time a book is bought). For that we need a monitoring page that displays every message.
 
## Add a monitoring page
 
* `cp ../before/09-Schedule/monitoring.xhtml src/main/webapp`
* In the `mainTemplate.xhtml` add a link to the `monitoring.xhtml`

## Add a monitoring service 

* In `MonitoringService`
* Change the `logs` attribute from `private List logs` to `private List<String> logs = new ArrayList<>();`
* Add a `public void newMessage(String robotMessage)` that adds the `robotMessage` to the `logs`
* Make sure you can access the `MonitoringService` from the `monitoring.xhtml` page
 
# DOJO - Add a scheduler that buys random books

## The Robot Service buys books randomly

* Copy the `RobotService.java` class, it will randomly buy books
* `cp ../before/09-Schedule/RobotService.java src/main/java/org/agoncal/training/javaee6adv/service`
* Fix the bugs

## Build, Deploy and check the web application
                 
* With a browser go to [http://localhost:8080/cdbookstore]()
* View a Book (for example [http://localhost:8080/cdbookstore/faces/book/view.xhtml?id=-12]())
* Click on the _Start_ button, the robot should start buying books

## Execute the tests in a remote environment

* Start WildFly (`$WILDFLY_HOME/bin/standalone.sh`)
* `mvn -Parquillian-wildfly-remote test` will execute the tests with WildFly up and running and with the application deployed

# Backup your code

* Save a backup of your code at `../after/09-Schedule/`
