#  ###############################  #
#  Creating the monitoring service  #
#  ###############################  #

cdi-new-bean --named MonitoringBean --scoped APPLICATION --targetPackage org.agoncal.training.javaee6adv.view ;
java-new-field --named messages --type java.util.List --generateGetter --generateSetter ;


#  ########################  #
#  Creating a CDI qualifier  #
#  ########################  #

cdi-new-qualifier --named Robot --targetPackage org.agoncal.training.javaee6adv.util ;


#  ################  #
#  Copies resources  #
#  ################  #
cd ~~ ;
cp ../before/10-Schedule/monitoring.xhtml src/main/webapp ;
cp ../before/10-Schedule/RobotService.java src/main/java/org/agoncal/training/javaee6adv/service ;

