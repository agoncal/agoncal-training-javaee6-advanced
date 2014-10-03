#  #########################  #
#  Install Arquillian add-on
#  #########################  #

# addon-install --coordinate org.arquillian.forge:arquillian-addon,1.0.0-SNAPSHOT ;
# addon-install-from-git --url https://github.com/aslakknutsen/plugin-arquillian.git --branch forge2 ;

#  ################  #
#  Setup Arquillian
#  ################  #

arquillian-setup --arquillianVersion 1.1.5.Final --testFramework junit --testFrameworkVersion 4.11 --containerAdapter wildfly-managed --containerAdapterVersion 8.1.0.Final ;
arquillian-container-setup --containerAdapter wildfly-remote --containerAdapterVersion 8.1.0.Final ;
arquillian-container-configuration --container arquillian-wildfly-managed --containerOption jbossHome --containerValue /Users/antoniombp/Tools/Software/JBoss/wildfly-8.1.0.Final ;

#  #########################  #
#  Generate Arquillian tests
#  #########################  #

# JSF Beacking Beans
# ##################
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/AuthorBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/BookBean.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/CDBean.java ;

# REST Endpoints
# ##############
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/rest/AuthorEndpoint.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/rest/BookEndpoint.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/rest/CDEndpoint.java ;


#  ################  #
#  Copies resources  #
#  ################  #
cd ~~ ;
cp ../before/02-Arquillian/rest/CustomerEndpointTest.java src/test/java/org/agoncal/training/javaee6adv/rest/ ;
cp ../before/02-Arquillian/rest/MusicianEndpointTest.java src/test/java/org/agoncal/training/javaee6adv/rest/ ;
cp ../before/02-Arquillian/view/CategoryBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/CustomerBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/GenreBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/ItemBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/MajorLabelBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/MusicianBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/OrderLineBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/PublisherBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
cp ../before/02-Arquillian/view/PurchaseOrderBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view/ ;
