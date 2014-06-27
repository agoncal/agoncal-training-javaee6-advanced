#  ###############################  #
#  Creating the monitoring service  #
#  ###############################  #

ejb-new-bean --named MonitoringService --type SINGLETON ;
java-new-field --named logs --type java.util.List --generateGetter --generateSetter ;

#  ########################  #
#  Creating a CDI qualifier  #
#  ########################  #

cdi-new-qualifier --named Robot --targetPackage org.agoncal.training.javaee6adv.util ;
