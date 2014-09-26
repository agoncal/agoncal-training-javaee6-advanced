#  #####################  #
#  Creates a new project  #
#  #####################  #
project-new --named cdbookclient --topLevelPackage org.agoncal.training.javaee6adv.client --type jar --finalName cdbookclient --version 1.0.0 --createMain ;

#  ##################  #
#  Cleans the pom.xml  #
#  ##################  #

project-add-dependencies org.jboss.resteasy:resteasy-client:3.0.9.Final ;
project-add-dependencies org.glassfish:javax.json:1.0.4 ;
