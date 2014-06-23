#  #####################  #
#  Creates a new project  #
#  #####################  #
project-new --named cdbookstore --topLevelPackage org.agoncal.training.javaee6adv --type war --finalName cdbookstore ;


# Setup the persistence unit in persistence.xml
# ############
jpa-setup --persistenceUnitName cdbookstorePU ;


#  ########################  #
#  Creates the domain model  #
#  ########################  #

# ISBN constraint
# ############
# TODO constraint-new-annotation --named ISBN ;


# Genre entity
# ############
jpa-new-entity --named Genre ;
jpa-new-field --named name --length 100 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 100 ;


# Category entity
# ############
jpa-new-entity --named Category ;
jpa-new-field --named name --length 100 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 100 ;


# Publisher
# ############
jpa-new-entity --named Publisher ;
jpa-new-field --named name --length 30 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 30 ;


# MajorLabel
# ############
jpa-new-entity --named MajorLabel ;
jpa-new-field --named name --length 30 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 30 ;


# Language enumeration
# ############
java-new-enum --named Language --targetPackage org.agoncal.training.javaee6adv.model ;
java-new-enum-const ENGLISH ;
java-new-enum-const FRENCH ;
java-new-enum-const SPANISH ;
java-new-enum-const PORTUGUESE ;
java-new-enum-const ITALIAN ;
java-new-enum-const FINISH ;
java-new-enum-const GERMAN ;
java-new-enum-const DEUTSCH ;
java-new-enum-const RUSSIAN ;


# Author entity
# ############
jpa-new-entity --named Author ;
jpa-new-field --named firstName --length 50 --columnName first_name ;
jpa-new-field --named lastName --length 50 --columnName last_name ;
jpa-new-field --named bio --length 5000 ;
jpa-new-field --named dateOfBirth --type java.util.Date --temporalType DATE --columnName date_of_birth ;
jpa-new-field --named age --type java.lang.Integer --transient ;
jpa-new-field --named preferredLanguage --type org.agoncal.training.javaee6adv.model.Language --columnName preferred_language ;

constraint-add --onProperty firstName --constraint NotNull ;
constraint-add --onProperty firstName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty lastName --constraint NotNull ;
constraint-add --onProperty lastName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty bio --constraint Size --max 5000 ;
constraint-add --onProperty dateOfBirth --constraint Past ;


# Musician entity
# ############
jpa-new-entity --named Musician ;
jpa-new-field --named firstName --length 50 --columnName first_name ;
jpa-new-field --named lastName --length 50 --columnName last_name ;
jpa-new-field --named bio --length 5000 ;
jpa-new-field --named dateOfBirth --type java.util.Date --temporalType DATE --columnName date_of_birth ;
jpa-new-field --named age --type java.lang.Integer --transient ;
jpa-new-field --named preferredInstrument --columnName preferred_instrument ;

constraint-add --onProperty firstName --constraint NotNull ;
constraint-add --onProperty firstName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty lastName --constraint NotNull ;
constraint-add --onProperty lastName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty bio --constraint Size --max 5000 ;
constraint-add --onProperty dateOfBirth --constraint Past ;


# Item entity
# ############
jpa-new-entity --named Item ;
jpa-new-field --named title --length 50 ;
jpa-new-field --named price --type java.lang.Float ;
jpa-new-field --named description --length 3000 ;
jpa-new-field --named imageURL --columnName image_url ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;
constraint-add --onProperty description --constraint Size --min 1 --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;


# Book entity
# ############
jpa-new-entity --named Book ;
jpa-new-field --named title --length 50 ;
jpa-new-field --named price --type java.lang.Float ;
jpa-new-field --named description --length 3000 ;
jpa-new-field --named imageURL --columnName image_url ;
jpa-new-field --named isbn  --length 15 ;
jpa-new-field --named nbOfPages --type java.lang.Integer --columnName nb_of_pages ;
jpa-new-field --named publicationDate --type java.util.Date --temporalType DATE --columnName publication_date ;
jpa-new-field --named language --type org.agoncal.training.javaee6adv.model.Language ;
# Relationships
jpa-new-field --named category --type org.agoncal.training.javaee6adv.model.Category --relationshipType Many-to-One
jpa-new-field --named author --type org.agoncal.training.javaee6adv.model.Author --relationshipType Many-to-One ;
jpa-new-field --named publisher --type org.agoncal.training.javaee6adv.model.Publisher --relationshipType Many-to-One ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;
constraint-add --onProperty description --constraint Size --min 1 --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;
constraint-add --onProperty isbn --constraint NotNull ;
# TODO Adding ISBN constraints constraint-add --onProperty isbn --constraint ISBN ;
constraint-add --onProperty nbOfPages --constraint Min --value 1 ;
constraint-add --onProperty publicationDate --constraint Past ;


# CD entity
# ############
jpa-new-entity --named CD ;
jpa-new-field --named title --length 50;
jpa-new-field --named price --type java.lang.Float ;
jpa-new-field --named description --length 3000 ;
jpa-new-field --named imageURL --columnName image_url ;
jpa-new-field --named totalDuration --type java.lang.Float --columnName total_duration ;
# Relationships
jpa-new-field --named label --type org.agoncal.training.javaee6adv.model.MajorLabel --relationshipType Many-to-One ;
jpa-new-field --named genre --type org.agoncal.training.javaee6adv.model.Genre --relationshipType Many-to-One ;
jpa-new-field --named musicians --type org.agoncal.training.javaee6adv.model.Musician --relationshipType Many-to-Many ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;
constraint-add --onProperty description --constraint Size --min 1 --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;


# Address entity
# ############
jpa-new-entity --named Address ;
jpa-new-field --named street1 ;
jpa-new-field --named street2 ;
jpa-new-field --named city ;
jpa-new-field --named state ;
jpa-new-field --named zipcode --columnName zip_code ;
jpa-new-field --named country ;

constraint-add --onProperty street1 --constraint Size --min 5 --max 50 ;
constraint-add --onProperty street1 --constraint NotNull ;
constraint-add --onProperty city --constraint Size --min 2 --max 50 ;
constraint-add --onProperty city --constraint NotNull ;
constraint-add --onProperty zipcode --constraint Size --min 1 --max 10 ;
constraint-add --onProperty zipcode --constraint NotNull ;
constraint-add --onProperty country --constraint Size --min 2 --max 50 ;
constraint-add --onProperty country --constraint NotNull ;


# CreditCardType enumeration
# ############
java-new-enum --named CreditCardType --targetPackage org.agoncal.training.javaee6adv.model ;
java-new-enum-const VISA ;
java-new-enum-const MASTER_CARD ;
java-new-enum-const AMERICAN_EXPRESS ;


# CreditCard entity
# ############
jpa-new-entity --named CreditCard ;
jpa-new-field --named creditCardNumber --columnName credit_card_number ;
jpa-new-field --named creditCardType --type org.agoncal.training.javaee6adv.model.CreditCardType --columnName credit_card_type ;
jpa-new-field --named creditCardExpDate --columnName credit_card_expiry_date  ;

constraint-add --onProperty creditCardNumber --constraint Size --min 1 --max 30 ;
constraint-add --onProperty creditCardNumber --constraint NotNull ;
constraint-add --onProperty creditCardType --constraint NotNull ;
constraint-add --onProperty creditCardExpDate --constraint Size --min 1 --max 5 ;
constraint-add --onProperty creditCardExpDate --constraint NotNull ;


# Email constraint
# ############
# TODO constraint-new-annotation --named Email ;


# Customer entity
# ############
jpa-new-entity --named Customer ;
jpa-new-field --named firstname --columnName first_name ;
jpa-new-field --named lastname --columnName last_name ;
jpa-new-field --named telephone ;
jpa-new-field --named email ;
jpa-new-field --named dateOfBirth --type java.util.Date --columnName date_of_birth ;
jpa-new-field --named age --type int --transient
# Embeddable Address
jpa-new-field --named street1 ;
jpa-new-field --named street2 ;
jpa-new-field --named city ;
jpa-new-field --named state ;
jpa-new-field --named zipcode --columnName zip_code ;
jpa-new-field --named country ;

constraint-add --onProperty firstname --constraint Size --min 2 --max 50 ;
constraint-add --onProperty firstname --constraint NotNull ;
constraint-add --onProperty lastname --constraint Size --min 2 --max 50 ;
constraint-add --onProperty lastname --constraint NotNull ;
constraint-add --onProperty dateOfBirth --constraint Past ;
# Embeddable Address
constraint-add --onProperty street1 --constraint Size --min 5 --max 50 ;
constraint-add --onProperty street1 --constraint NotNull ;
constraint-add --onProperty city --constraint Size --min 2 --max 50 ;
constraint-add --onProperty city --constraint NotNull ;
constraint-add --onProperty zipcode --constraint Size --min 1 --max 10 ;
constraint-add --onProperty zipcode --constraint NotNull ;
constraint-add --onProperty country --constraint Size --min 2 --max 50 ;
constraint-add --onProperty country --constraint NotNull ;


# OrderLine entity
# ############
jpa-new-entity --named OrderLine ;
jpa-new-field --named quantity --type int ;
# Relationships
jpa-new-field --named item --type org.agoncal.training.javaee6adv.model.Item --relationshipType Many-to-One ;

constraint-add --onProperty quantity --constraint Min --value 1 ;


# PurchaseOrder entity
# ############
jpa-new-entity --named PurchaseOrder ;
jpa-new-field --named quantity --type int ;
jpa-new-field --named orderDate --type java.util.Date --columnName order_date ;
# Relationships
jpa-new-field --named customer --type org.agoncal.training.javaee6adv.model.Customer --relationshipType Many-to-One ;
jpa-new-field --named orderLines --type org.agoncal.training.javaee6adv.model.OrderLine --relationshipType One-to-Many --columnName order_lines ;
# Embeddable Address
jpa-new-field --named street1 ;
jpa-new-field --named street2 ;
jpa-new-field --named city ;
jpa-new-field --named state ;
jpa-new-field --named zipcode --columnName zip_code ;
jpa-new-field --named country ;
# Embeddable Credit Card
jpa-new-field --named creditCardNumber --columnName credit_card_number ;
jpa-new-field --named creditCardType --type org.agoncal.training.javaee6adv.model.CreditCardType --columnName credit_card_type ;
jpa-new-field --named creditCardExpDate --columnName credit_card_expiry_date  ;

# Embeddable Address
constraint-add --onProperty street1 --constraint Size --min 5 --max 50 ;
constraint-add --onProperty street1 --constraint NotNull ;
constraint-add --onProperty city --constraint Size --min 2 --max 50 ;
constraint-add --onProperty city --constraint NotNull ;
constraint-add --onProperty zipcode --constraint Size --min 1 --max 10 ;
constraint-add --onProperty zipcode --constraint NotNull ;
constraint-add --onProperty country --constraint Size --min 2 --max 50 ;
constraint-add --onProperty country --constraint NotNull ;
# Embeddable Credit Card
constraint-add --onProperty creditCardNumber --constraint Size --min 1 --max 30 ;
constraint-add --onProperty creditCardNumber --constraint NotNull ;
constraint-add --onProperty creditCardType --constraint NotNull ;
constraint-add --onProperty creditCardExpDate --constraint Size --min 1 --max 5 ;
constraint-add --onProperty creditCardExpDate --constraint NotNull ;


#  #############################  #
#  Generates JSF beans and pages  #
#  #############################  #
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Genre
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Category
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Publisher
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.MajorLabel
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Author
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Musician
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Item
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Book
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.CD
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.Customer
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.PurchaseOrder
scaffold-generate --webRoot /admin --targets org.agoncal.training.javaee6adv.model.OrderLine


#  ########################  #
#  Generates REST endpoints  #
#  ########################  #
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.model.Author ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.model.Book ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.model.CD ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.model.Musician ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.model.Customer ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.model.PurchaseOrder ;


#  ##################  #
#  Cleans the pom.xml  #
#  ##################  #
project-remove-dependencies org.hibernate.javax.persistence:hibernate-jpa-2.0-api:jar:: ;
project-remove-dependencies javax.validation:validation-api:jar:: ;
project-remove-dependencies javax.enterprise:cdi-api:jar:: ;
project-remove-dependencies javax.annotation:jsr250-api:jar:: ;
project-remove-dependencies org.jboss.spec.javax.ejb:jboss-ejb-api_3.1_spec:jar:: ;
project-remove-dependencies org.jboss.spec.javax.servlet:jboss-servlet-api_3.0_spec:jar:: ;
project-remove-dependencies org.jboss.spec.javax.faces:jboss-jsf-api_2.0_spec:jar:: ;
project-remove-dependencies org.jboss.spec.javax.ws.rs:jboss-jaxrs-api_1.1_spec:jar:: ;

project-remove-managed-dependencies javax.annotation:jsr250-api:jar::1.0 ;
project-remove-managed-dependencies org.jboss.spec.javax.faces:jboss-jsf-api_2.0_spec:jar::1.0.0.Final ;
project-remove-managed-dependencies org.jboss.spec:jboss-javaee-6.0:pom::3.0.2.Final ;

project-add-dependencies org.jboss.spec:jboss-javaee-6.0:3.0.2.Final:provided:pom ;
project-add-dependencies org.jboss.resteasy:resteasy-client:3.0.8.Final:test:jar ;
