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
cp ../before/08-WebApplication/book src/main/webapp/ ;
cp ../before/08-WebApplication/cd src/main/webapp/ ;
cp ../before/08-WebApplication/banner.png src/main/webapp/resources/ ;
cp ../before/08-WebApplication/forge-logo.png src/main/webapp/resources/ ;
cp ../before/08-WebApplication/forge-style.css src/main/webapp/resources/ ;
cp ../before/08-WebApplication/index.xhtml src/main/webapp/ ;
cp ../before/08-WebApplication/mainTemplate.xhtml src/main/webapp/resources/scaffold/ ;
cp ../before/08-WebApplication/pageTemplate.xhtml src/main/webapp/resources/scaffold/ ;

cp ../before/08-WebApplication/BookBean.java src/main/java/org/agoncal/training/javaee6adv/view ;
cp ../before/08-WebApplication/BookBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view ;
cp ../before/08-WebApplication/BookServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service ;
cp ../before/08-WebApplication/CDBean.java src/main/java/org/agoncal/training/javaee6adv/view ;
cp ../before/08-WebApplication/CDBeanTest.java src/test/java/org/agoncal/training/javaee6adv/view ;
cp ../before/08-WebApplication/CDServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service ;
