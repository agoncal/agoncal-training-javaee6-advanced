#  #####################  #
#  Adding a Service Tier  #
#  #####################  #

ejb-new-bean --named AuthorService ;
ejb-new-bean --named BookService ;
ejb-new-bean --named CategoryService ;
ejb-new-bean --named CDService ;
ejb-new-bean --named CustomerService ;
ejb-new-bean --named GenreService ;
ejb-new-bean --named ItemService ;
ejb-new-bean --named MajorLabelService ;
ejb-new-bean --named MusicianService ;
ejb-new-bean --named OrderLineService ;
ejb-new-bean --named PublisherService ;
ejb-new-bean --named PurchaseOrderService ;

#  ###############################  #
#  Adding a CDI Bean for Producers  #
#  ###############################  #

cdi-new-bean --named Resources --targetPackage org.agoncal.training.javaee6adv.util ;

#  #######################  #
#  Adding Arquillian Tests  #
#  #######################  #

arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/AuthorService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/BookService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/CategoryService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/CDService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/GenreService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/ItemService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/MajorLabelService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/OrderLineService.java ;
arquillian-create-test --value src/main/java/org/agoncal/training/javaee6adv/service/PurchaseOrderService.java ;


#  ################  #
#  Copies resources  #
#  ################  #
cd ~~ ;
cp ../before/06-Service/AbstractService.java src/main/java/org/agoncal/training/javaee6adv/service/ ;
cp ../before/06-Service/MusicianService.java src/main/java/org/agoncal/training/javaee6adv/service/ ;
cp ../before/06-Service/MusicianServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service/ ;
cp ../before/06-Service/PublisherService.java src/main/java/org/agoncal/training/javaee6adv/service/ ;
cp ../before/06-Service/PublisherServiceTest.java src/test/java/org/agoncal/training/javaee6adv/service/ ;
