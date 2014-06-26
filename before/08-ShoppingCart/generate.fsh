#  ###################  #
#  Adding domain model  #
#  ###################  #

java-new-class --named ShoppingCartItem --targetPackage org.agoncal.training.javaee6adv.view ;
java-new-field --named item --type org.agoncal.training.javaee6adv.model.Item --generateGetter --generateSetter ;
java-new-field --named quantity --type java.lang.Integer --generateGetter --generateSetter ;

constraint-add --onProperty item --constraint NotNull ;
constraint-add --onProperty quantity --constraint NotNull ;
constraint-add --onProperty quantity --constraint Min --value 1 ;

#  #############################  #
#  Generates JSF beans and pages  #
#  #############################  #

faces-new-bean --named ShoppingCartBean ;

#  #########################  #
#  Generate Arquillian tests
#  #########################  #

# JSF Beacking Beans
# ##################
arquillian-create-test --value cdbookstore/src/main/java/org/agoncal/training/javaee6adv/view/ShoppingCartBean.java ;
