#  #####################  #
#  Adding a Service Tier  #
#  #####################  #

ejb-new-bean --named AuthorService ;
ejb-new-bean --named BookService ;
ejb-new-bean --named CategoryService ;
ejb-new-bean --named CDService ;
ejb-new-bean --named GenreService ;
ejb-new-bean --named MajorLabelService ;
ejb-new-bean --named MusicianService ;
ejb-new-bean --named PublisherService ;

#  ###############################  #
#  Adding a CDI Bean for Producers  #
#  ###############################  #

cdi-new-bean --named Resources --targetPackage org.agoncal.training.javaee6adv.util ;

#  #######################  #
#  Adding Arquillian Tests  #
#  #######################  #

arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/service/AuthorService.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/service/BookService.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/service/CategoryService.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/service/CDService.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/service/GenreService.java ;
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/service/MajorLabelService.java ;
