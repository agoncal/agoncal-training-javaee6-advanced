#  #####################  #
#  Creates a new project  #
#  #####################  #
project-new --named cdbookclient --topLevelPackage org.agoncal.training.javaee6adv.client --type jar --finalName cdbookclient  --createMain ;

#  ##################  #
#  Cleans the pom.xml  #
#  ##################  #

project-add-dependencies org.jboss.resteasy:resteasy-client:3.0.8.Final ;
