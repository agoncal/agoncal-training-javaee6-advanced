#  ####################  #
#  Adding new JSF Pages  #
#  ####################  #

# faces-new-view --named ;

#  ############################  #
#  Adding new JSF Backing Beans  #
#  ############################  #

# faces-new-bean --named ;

#  ########################  #
#  Setting up project Stage  #
#  ########################  #

faces-project-stage --stage Development

#  ############################  #
#  Adding Web Jars dependencies  #
#  ############################  #

project-add-dependencies org.webjars:bootstrap:2.3.2 ;
project-add-dependencies org.primefaces:primefaces:5.0 ;
# project-add-dependencies org.webjars:jquery:1.11.1 ;
