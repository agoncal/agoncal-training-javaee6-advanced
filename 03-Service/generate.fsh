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
