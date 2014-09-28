#  #########################  #
#  Install Arquillian add-on
#  #########################  #

# addon-install --coordinate org.arquillian.forge:arquillian-addon,1.0.0-SNAPSHOT ;
# addon-install-from-git --url https://github.com/aslakknutsen/plugin-arquillian.git --branch forge2 ;

#  ################  #
#  Setup Arquillian
#  ################  #

# arquillian-setup --arquillianVersion 1.1.5.Final --testFramework junit --testFrameworkVersion 4.11 --containerAdapter wildfly-managed --containerAdapterVersion 8.1.0.Final ;
# arquillian-container-setup --containerAdapter wildfly-remote --containerAdapterVersion 8.1.0.Final ;
# arquillian-container-configuration --container arquillian-wildfly-managed --containerOption jbossHome --containerValue /Users/antoniombp/Tools/Software/JBoss/wildfly-8.1.0.Final ;

#  #########################  #
#  Generate Arquillian tests
#  #########################  #

# JSF Beacking Beans
# ##################
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/AuthorBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/BookBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/CategoryBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/CDBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/GenreBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/ItemBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/MajorLabelBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/OrderLineBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/PurchaseOrderBean.java ;

# REST Endpoints
# ##############
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/rest/AuthorEndpoint.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/rest/BookEndpoint.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/rest/CDEndpoint.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/rest/MusicianEndpoint.java ;


#  ################  #
#  Copies resources  #
#  ################  #
cd ~~ ;
cp ../before/02-Arquillian/CustomerBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/PublisherBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/MusicianBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/CustomerEndpointTest.java src/test/java/org/agoncal/training/javaee6adv/rest/ ;
cp ../before/02-Arquillian/MusicianEndpointTest.java src/test/java/org/agoncal/training/javaee6adv/rest/ ;
