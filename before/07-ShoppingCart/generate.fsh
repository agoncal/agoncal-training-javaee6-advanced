#  ###################  #
#  Adding domain model  #
#  ###################  #


#  #############################  #
#  Generates JSF beans and pages  #
#  #############################  #
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Customer
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Order


#  #####################  #
#  Adding a Service Tier  #
#  #####################  #

ejb-new-bean --named CustomerService ;
ejb-new-bean --named OrderService ;
