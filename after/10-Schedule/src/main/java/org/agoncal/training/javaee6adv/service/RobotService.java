package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.util.Robot;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Stateless
@LocalBean
@Named
public class RobotService implements Serializable
{

   // Frequency with which the bot will book *
   public static final long DURATION = TimeUnit.SECONDS.toMillis(3);

   @Inject
   private RandomService randomService;

   @Inject
   private Event<Book> boughtBookEvent;

   @Inject
   @Robot
   private Event<String> robotMessageEvent;

   @Resource
   private TimerService timerService;

   @Timeout
   private void buy(Timer timer)
   {
      // Select a book at random
      Book book = randomService.getBook();

      String message = " ## " + randomService.getCustomer().getFirstname() + " " + randomService.getCustomer().getLastname() + " bought " + book.getTitle() + " at " + new Date().toString() + "\n";

      boughtBookEvent.fire(book);
      robotMessageEvent.fire(message);
   }

   public Timer startRobot()
   {
      String startMessage = "==========================\n" + "Robot started at " + new Date().toString() + "\n";
      robotMessageEvent.fire(startMessage);
      return timerService.createIntervalTimer(0, DURATION, new TimerConfig(null, false));
   }

   public void stopRobot()
   {
      String stopMessage = "==========================\n" + "Bot stopped at " + new Date().toString() + "\n";
      robotMessageEvent.fire(stopMessage);

      for (Timer activeTimer : timerService.getTimers())
      {
         activeTimer.cancel();
      }
   }
}