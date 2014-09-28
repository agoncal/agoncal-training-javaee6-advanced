package org.agoncal.training.javaee6adv.service;

import org.agoncal.training.javaee6adv.model.Book;
import org.agoncal.training.javaee6adv.util.Robot;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import javax.ejb.Timer;
import java.util.concurrent.TimeUnit;

@Stateless
@LocalBean
@Named
public class RobotService implements Serializable {

   /** Frequency with which the bot will book **/
   public static final long DURATION = TimeUnit.SECONDS.toMillis(3);

   @Inject
   private RandomService randomService;

   @Timeout
   private void buy(Timer timer) {

      // Select a book at random
      Book book = randomService.getBook();

      String message = new StringBuilder(" ")
            .append(randomService.getCustomer().getFirstname())
            .append(" ")
            .append(randomService.getCustomer().getLastname())
            .append(" bought ")
            .append(book.getTitle())
            .append(" at ")
            .append(new Date().toString())
            .append("\n").toString();

      boughtCD.fire(book);
      robotMessage.fire(message);
   }

   public Timer startRobot() {
      String startMessage = new StringBuilder("==========================\n")
            .append("Robot started at ").append(new Date().toString()).append("\n")
            .toString();
      robotMessage.fire(startMessage);
      return timerService.createIntervalTimer(0, DURATION, new TimerConfig(null, false));
   }

   public void stopRobot() {
      String stopMessage = new StringBuilder("==========================\n")
            .append("Bot stopped at ").append(new Date().toString()).append("\n")
            .toString();
      robotMessage.fire(stopMessage);

      for (Iterator iterator = timerService.getTimers().iterator(); iterator.hasNext(); ) {
         Timer activeTimer = (Timer) iterator.next();
         activeTimer.cancel();

      }
   }
}