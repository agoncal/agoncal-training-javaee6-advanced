#  #######################  #
#  Adding Arquillian Tests  #
#  #######################  #

# Install Arquillian add-on
# addon-install --groupId org.arquillian.forge --name arquillian-addon --version 1.0.0-SNAPSHOT

arquillian-setup --arquillianVersion 1.1.4.Final --testFramework junit --testFrameworkVersion 4.11
arquillian-container-setup --containerAdapter jbossas-remote-7  --containerAdapterVersion 7.2.0.Final
arquillian-container-setup --containerAdapter jbossas-managed-7 --containerAdapterVersion 7.2.0.Final
arquillian-container-configuration --container arquillian-jbossas-managed-7 --containerOption jbossHome --containerValue /Users/antoniombp/Tools/Software/JBoss/jboss-as-7.1.1.Final

arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/AuthorBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/BookBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/CategoryBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/CDBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/GenreBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/MajorLabelBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/MusicianBean.java
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/PublisherBean.java
