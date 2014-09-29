#  ########################  #
#  Setting up project Stage  #
#  ########################  #

faces-set-project-stage --stage Development ;


#  ############################  #
#  Adding Web Jars dependencies  #
#  ############################  #

project-add-dependencies org.webjars:bootstrap:2.3.2 ;
project-add-dependencies org.primefaces:primefaces:5.0 ;


#  ################  #
#  Copies resources  #
#  ################  #
cd ~~ ;
cp ../before/07-WebApplication/book src/main/webapp/ ;
cp ../before/07-WebApplication/banner.png src/main/webapp/resources/ ;
cp ../before/07-WebApplication/forge-logo.png src/main/webapp/resources/ ;
cp ../before/07-WebApplication/forge-style.css src/main/webapp/resources/ ;
cp ../before/07-WebApplication/index.xhtml src/main/webapp/ ;
cp ../before/07-WebApplication/mainTemplate.xhtml src/main/webapp/resources/scaffold/ ;
cp ../before/07-WebApplication/pageTemplate.xhtml src/main/webapp/resources/scaffold/ ;

cp ../before/07-WebApplication/BookBean.java src/main/java/org/agoncal/training/javaee6adv/view ;
cp ../before/07-WebApplication/BookBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view ;
cp ../before/07-WebApplication/BookServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service ;
cp ../before/07-WebApplication/CDBean.java src/main/java/org/agoncal/training/javaee6adv/view ;
cp ../before/07-WebApplication/CDBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view ;
cp ../before/07-WebApplication/CDServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service ;
