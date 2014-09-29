package org.agoncal.training.javaee6adv.view;

import org.agoncal.training.javaee6adv.util.Robot;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class MonitoringBean
{

   private List<String> messages = new ArrayList<>();

   public void newMessage(@Observes @Robot String robotMessage)
   {
      messages.add(robotMessage);
   }

   public List getMessages()
   {
      return messages;
   }

   public void setMessages(List<String> messages)
   {
      this.messages = messages;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (messages != null)
         result += "messages: " + messages;
      return result;
   }
}