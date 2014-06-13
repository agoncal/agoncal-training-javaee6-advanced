#  #######################  #
#  Adding Arquillian Tests  #
#  #######################  #

# Install Arquillian add-on
# addon-install --groupId org.arquillian.forge --name arquillian-addon --version 1.0.0-SNAPSHOT

arquillian-setup --arquillianVersion 1.1.4.Final --testFramework junit --testFrameworkVersion 4.11
arquillian-container-setup --containerAdapter wildfly-managed --containerAdapterVersion 8.1.0.Final
arquillian-container-setup --containerAdapter wildfly-remote --containerAdapterVersion 8.1.0.Final
arquillian-container-configuration --container arquillian-wildfly-managed --containerOption jbossHome --containerValue /Users/antoniombp/Tools/Software/JBoss/wildfly-8.1.0.Final

arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/AuthorBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/BookBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/CategoryBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/CDBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/GenreBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/MajorLabelBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/MusicianBean.java
