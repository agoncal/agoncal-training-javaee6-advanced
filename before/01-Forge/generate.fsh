#  #####################  #
#  Creates a new project  #
#  #####################  #
project-new --named cdbookstore --topLevelPackage org.agoncal.training.javaee6adv --type war --finalName cdbookstore --version 1.0.0 ;


# Setup the persistence unit in persistence.xml
# ############
jpa-setup --persistenceUnitName cdbookstorePU ;


#  ########################  #
#  Creates the domain model  #
#  ########################  #

# ISBN constraint
# ############
# TODO Adding ISBN constraint on property doesn't work [FORGE-1801]
constraint-new-annotation --named Name ;
constraint-new-annotation --named ISBN ;
constraint-new-annotation --named Title ;
constraint-new-annotation --named Description ;
constraint-new-annotation --named Email ;


#  Country entity
#  ############
jpa-new-entity --named Country ;
jpa-new-field --named isoCode --length 2 --columnName iso_code --not-nullable ;
jpa-new-field --named name --length 80 --not-nullable ;
jpa-new-field --named printableName --length 80 --columnName printable_name --not-nullable ;
jpa-new-field --named iso3 --length 3 ;
jpa-new-field --named numcode --length 3 ;
# Constraints
constraint-add --onProperty isoCode --constraint NotNull ;
constraint-add --onProperty isoCode --constraint Size --min 2 --max 2 ;
constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --min 2 --max 80 ;
constraint-add --onProperty printableName --constraint NotNull ;
constraint-add --onProperty printableName --constraint Size --min 2 --max 80 ;
constraint-add --onProperty iso3 --constraint NotNull ;
constraint-add --onProperty iso3 --constraint Size --min 3 --max 3 ;
constraint-add --onProperty numcode --constraint NotNull ;
constraint-add --onProperty numcode --constraint Size --min 3 --max 3 ;
# Cache
java-add-annotation --annotation javax.persistence.Cacheable ;


# Address entity
# ############
jpa-new-embeddable --named Address ;
jpa-new-field --named street1 --length 50 --not-nullable ;
jpa-new-field --named street2 ;
jpa-new-field --named city --length 50 --not-nullable ;
jpa-new-field --named state ;
jpa-new-field --named zipcode --columnName zip_code --length 10 --not-nullable ;
# Relationships
jpa-new-field --named country --type org.agoncal.training.javaee6adv.model.Country --relationshipType Many-to-One --cascadeType PERSIST ;

constraint-add --onProperty street1 --constraint NotNull ;
constraint-add --onProperty street1 --constraint Size --min 5 --max 50 ;
constraint-add --onProperty city --constraint NotNull ;
constraint-add --onProperty city --constraint Size --min 2 --max 50 ;
constraint-add --onProperty zipcode --constraint NotNull ;
constraint-add --onProperty zipcode --constraint Size --min 1 --max 10 ;
constraint-add --onProperty country --constraint NotNull ;
constraint-add --onProperty country --constraint Valid ;


# Customer entity
# ############
jpa-new-entity --named Customer ;
jpa-new-field --named firstName --columnName first_name ;
jpa-new-field --named lastName --columnName last_name ;
jpa-new-field --named dateOfBirth --type java.util.Date --columnName date_of_birth ;
jpa-new-field --named age --type java.lang.Integer --transient ;
jpa-new-field --named telephone ;
jpa-new-field --named email ;
# Embeddable Address
# TODO jpa-new-field --named homeAddress --type org.agoncal.training.javaee6adv.model.Address --relationshipType Embedded ; [1108]
jpa-new-field --named street1 ;
jpa-new-field --named street2 ;
jpa-new-field --named city ;
jpa-new-field --named state ;
jpa-new-field --named zipcode --columnName zip_code ;
jpa-new-field --named country ;

constraint-add --onProperty firstName --constraint NotNull ;
constraint-add --onProperty firstName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty lastName --constraint NotNull ;
constraint-add --onProperty lastName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty dateOfBirth --constraint Past ;
# Embeddable Address
constraint-add --onProperty street1 --constraint NotNull ;
constraint-add --onProperty street1 --constraint Size --min 5 --max 50 ;
constraint-add --onProperty city --constraint NotNull ;
constraint-add --onProperty city --constraint Size --min 2 --max 50 ;
constraint-add --onProperty zipcode --constraint NotNull ;
constraint-add --onProperty zipcode --constraint Size --min 1 --max 10 ;
constraint-add --onProperty country --constraint NotNull ;
constraint-add --onProperty country --constraint Size --min 2 --max 50 ;


# CreditCardType enumeration
# ############
java-new-enum --named CreditCardType --targetPackage org.agoncal.training.javaee6adv.model ;
java-new-enum-const VISA ;
java-new-enum-const MASTER_CARD ;
java-new-enum-const AMERICAN_EXPRESS ;


# CreditCard entity
# ############
jpa-new-embeddable --named CreditCard ;
jpa-new-field --named creditCardNumber --columnName credit_card_number --length 30 --not-nullable ;
jpa-new-field --named creditCardType --type org.agoncal.training.javaee6adv.model.CreditCardType --columnName credit_card_type --not-nullable ;
jpa-new-field --named creditCardExpDate --columnName credit_card_expiry_date --length 5 --not-nullable ;

constraint-add --onProperty creditCardNumber --constraint NotNull ;
constraint-add --onProperty creditCardNumber --constraint Size --min 1 --max 30 ;
constraint-add --onProperty creditCardType --constraint NotNull ;
constraint-add --onProperty creditCardExpDate --constraint NotNull ;
constraint-add --onProperty creditCardExpDate --constraint Size --min 1 --max 5 ;


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


# Person mapped superclass
# ############
jpa-new-mapped-superclass --named Person ;
jpa-new-field --named firstName --length 50 --columnName first_name ;
jpa-new-field --named lastName --length 50 --columnName last_name ;
jpa-new-field --named dateOfBirth --type java.util.Date --temporalType DATE --columnName date_of_birth ;
jpa-new-field --named age --type java.lang.Integer --transient ;

constraint-add --onProperty firstName --constraint NotNull ;
constraint-add --onProperty firstName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty lastName --constraint NotNull ;
constraint-add --onProperty lastName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty dateOfBirth --constraint Past ;


# Author entity
# ############
jpa-new-entity --named Author ;
jpa-new-field --named firstName --length 50 --columnName first_name ;
jpa-new-field --named lastName --length 50 --columnName last_name ;
jpa-new-field --named dateOfBirth --type java.util.Date --temporalType DATE --columnName date_of_birth ;
jpa-new-field --named age --type java.lang.Integer --transient ;
jpa-new-field --named preferredLanguage --type org.agoncal.training.javaee6adv.model.Language --columnName preferred_language ;
jpa-new-field --named bio --length 5000 ;

constraint-add --onProperty firstName --constraint NotNull ;
constraint-add --onProperty firstName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty lastName --constraint NotNull ;
constraint-add --onProperty lastName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty dateOfBirth --constraint Past ;
constraint-add --onProperty bio --constraint Size --max 5000 ;


# Musician entity
# ############
jpa-new-entity --named Musician ;
jpa-new-field --named firstName --length 50 --columnName first_name ;
jpa-new-field --named lastName --length 50 --columnName last_name ;
jpa-new-field --named dateOfBirth --type java.util.Date --temporalType DATE --columnName date_of_birth ;
jpa-new-field --named age --type java.lang.Integer --transient ;
jpa-new-field --named bio --length 5000 ;
jpa-new-field --named preferredInstrument --columnName preferred_instrument ;

constraint-add --onProperty firstName --constraint NotNull ;
constraint-add --onProperty firstName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty lastName --constraint NotNull ;
constraint-add --onProperty lastName --constraint Size --min 2 --max 50 ;
constraint-add --onProperty dateOfBirth --constraint Past ;
constraint-add --onProperty bio --constraint Size --max 5000 ;


# Review entity
# ############
jpa-new-entity --named Review ;
jpa-new-field --named rate --type java.lang.Short ;
jpa-new-field --named description --length 3000 ;
jpa-new-field --named postedDate --type java.util.Date --temporalType DATE --columnName posted_date --not-updatable ;
# Relationships
jpa-new-field --named customer --type org.agoncal.training.javaee6adv.model.Customer --relationshipType Many-to-One ;

constraint-add --onProperty rate --constraint Max --value 5 ;
constraint-add --onProperty description --constraint NotNull ;
constraint-add --onProperty description --constraint Size --min 2 --max 50 ;


# Item entity
# ############
jpa-new-entity --named Item ;
jpa-new-field --named title --length 50 ;
jpa-new-field --named price --type java.lang.Float ;
jpa-new-field --named description --length 3000 ;
jpa-new-field --named imageURL --columnName image_url ;
jpa-new-field --named tags --type 'java.util.List<String>' ;
# Relationships
jpa-new-field --named customerReviews --type org.agoncal.training.javaee6adv.model.Review --columnName customer_reviews --relationshipType One-to-Many  ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;
constraint-add --onProperty description --constraint Size --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;


# Chapter entity
# ############
jpa-new-entity --named Chapter ;
jpa-new-field --named title ;
jpa-new-field --named resume ;
jpa-new-field --named number
jpa-new-field --named nbOfPages --type java.lang.Integer --columnName nb_of_pages ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;

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
jpa-new-field --named illustrations --type java.lang.Boolean ;
jpa-new-field --named language --type org.agoncal.training.javaee6adv.model.Language ;
# Relationships
jpa-new-field --named category --type org.agoncal.training.javaee6adv.model.Category --relationshipType Many-to-One
jpa-new-field --named author --type org.agoncal.training.javaee6adv.model.Author --relationshipType Many-to-One ;
jpa-new-field --named publisher --type org.agoncal.training.javaee6adv.model.Publisher --relationshipType Many-to-One ;
jpa-new-field --named chapters --type org.agoncal.training.javaee6adv.model.Chapter --relationshipType One-to-Many ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;
constraint-add --onProperty description --constraint Size --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;
constraint-add --onProperty isbn --constraint NotNull ;
# TODO Adding ISBN constraints constraint-add --onProperty isbn --constraint ISBN ;  [FORGE-1801]
constraint-add --onProperty nbOfPages --constraint Min --value 1 ;
constraint-add --onProperty publicationDate --constraint Past ;


# Track entity
# ############
jpa-new-entity --named Track ;
jpa-new-field --named title --length 50 --not-nullable ;
jpa-new-field --named description --length 3000 ;
jpa-new-field --named duration --type java.lang.Float ;
# TODO byte[] --lob
jpa-new-field --named wav ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;
constraint-add --onProperty title --constraint Size --max 3000 ;


# CD entity
# ############
jpa-new-entity --named CD ;
jpa-new-field --named title --length 50;
jpa-new-field --named price --type java.lang.Float ;
jpa-new-field --named description --length 3000 ;
jpa-new-field --named imageURL --columnName image_url ;
jpa-new-field --named totalDuration --type java.lang.Float --columnName total_duration ;
jpa-new-field --named nbOfCDs --type java.lang.Integer --columnName nb_of_cds ;
# Relationships
jpa-new-field --named track --type org.agoncal.training.javaee6adv.model.Track --relationshipType One-to-Many ;
jpa-new-field --named label --type org.agoncal.training.javaee6adv.model.MajorLabel --relationshipType Many-to-One ;
jpa-new-field --named genre --type org.agoncal.training.javaee6adv.model.Genre --relationshipType Many-to-One ;
jpa-new-field --named musicians --type org.agoncal.training.javaee6adv.model.Musician --relationshipType Many-to-Many ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 50 ;
constraint-add --onProperty description --constraint Size --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;


# OrderLine entity
# ############
jpa-new-entity --named OrderLine ;
jpa-new-field --named quantity --type java.lang.Integer ;
# Relationships
jpa-new-field --named item --type org.agoncal.training.javaee6adv.model.Item --relationshipType Many-to-One ;

constraint-add --onProperty quantity --constraint NotNull ;
constraint-add --onProperty quantity --constraint Min --value 1 ;


# PurchaseOrder entity
# ############
jpa-new-entity --named PurchaseOrder ;
jpa-new-field --named orderDate --type java.util.Date --columnName order_date --not-updatable ;
jpa-new-field --named vatRate --type java.lang.Float --columnName vat_rate ;
jpa-new-field --named amountVat --type java.lang.Float --transient ;
jpa-new-field --named totalWithout --type java.lang.Float --transient ;
jpa-new-field --named total --type java.lang.Float --transient ;
jpa-new-field --named discountRate --type java.lang.Float --columnName discount_rate ;
jpa-new-field --named amountDiscount --type java.lang.Float --transient ;
jpa-new-field --named totalAfterDiscount --type java.lang.Float --transient ;
# Relationships
jpa-new-field --named customer --type org.agoncal.training.javaee6adv.model.Customer --relationshipType Many-to-One ;
jpa-new-field --named orderLines --type org.agoncal.training.javaee6adv.model.OrderLine --relationshipType One-to-Many --columnName order_lines ;
# Embeddable Address
# TODO jpa-new-field --named deliveryAddress --type org.agoncal.training.javaee6adv.model.Address --relationshipType Embedded ; [1108]
jpa-new-field --named street1 ;
jpa-new-field --named street2 ;
jpa-new-field --named city ;
jpa-new-field --named state ;
jpa-new-field --named zipcode --columnName zip_code ;
jpa-new-field --named country ;
# Embeddable Credit Card
# TODO jpa-new-field --named creditCard --type org.agoncal.training.javaee6adv.model.CreditCard --relationshipType Embedded ; [1108]
jpa-new-field --named creditCardNumber --columnName credit_card_number ;
jpa-new-field --named creditCardType --type org.agoncal.training.javaee6adv.model.CreditCardType --columnName credit_card_type ;
jpa-new-field --named creditCardExpDate --columnName credit_card_expiry_date  ;

# Embeddable Address
constraint-add --onProperty street1 --constraint NotNull ;
constraint-add --onProperty street1 --constraint Size --min 5 --max 50 ;
constraint-add --onProperty city --constraint NotNull ;
constraint-add --onProperty city --constraint Size --min 2 --max 50 ;
constraint-add --onProperty zipcode --constraint NotNull ;
constraint-add --onProperty zipcode --constraint Size --min 1 --max 10 ;
constraint-add --onProperty country --constraint NotNull ;
constraint-add --onProperty country --constraint Size --min 2 --max 50 ;
# Embeddable Credit Card
constraint-add --onProperty creditCardNumber --constraint NotNull ;
constraint-add --onProperty creditCardNumber --constraint Size --min 1 --max 30 ;
constraint-add --onProperty creditCardType --constraint NotNull ;
constraint-add --onProperty creditCardExpDate --constraint NotNull ;
constraint-add --onProperty creditCardExpDate --constraint Size --min 1 --max 5 ;

