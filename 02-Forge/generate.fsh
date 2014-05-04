project-new --named cdbookstore --topLevelPackage org.agoncal.training.javaee6adv --type war --finalName cdbookstore ;



jpa-setup --persistenceUnitName cdbookstorePU ;



jpa-new-entity --named Genre ;
jpa-new-field --named name --length 100 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 100 ;



jpa-new-entity --named Category ;
jpa-new-field --named name --length 100 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 100 ;



jpa-new-entity --named Publisher ;
jpa-new-field --named name --length 30 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 30 ;



jpa-new-entity --named MajorLabel ;
jpa-new-field --named name --length 30 ;

constraint-add --onProperty name --constraint NotNull ;
constraint-add --onProperty name --constraint Size --max 30 ;



java-new-enum --named Language --targetPackage org.agoncal.training.javaee6adv.model ;
java-new-enum-const ENGLISH ;
java-new-enum-const FRENCH ;
java-new-enum-const SPANISH ;
java-new-enum-const PORTUGUESE ;
java-new-enum-const ITALIAN ;
java-new-enum-const FINISH ;
java-new-enum-const DEUTSCH ;
java-new-enum-const RUSSIAN ;



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



jpa-new-entity --named Book ;
jpa-new-field --named title ;
jpa-new-field --named price --type java.lang.Float ;
jpa-new-field --named description --length 2000 ;
jpa-new-field --named isbn ;
jpa-new-field --named nbOfPages --type java.lang.Integer --columnName nb_of_pages ;
jpa-new-field --named publicationDate --typeName java.util.Date --temporalType DATE --columnName publication_date ;
jpa-new-field --named language --type org.agoncal.training.javaee6adv.model.Language ;
jpa-new-field --named imageURL --columnName image_url ;
jpa-new-field --named category --type org.agoncal.training.javaee6adv.model.Category --relationshipType Many-to-One
jpa-new-field --named author --type org.agoncal.training.javaee6adv.model.Author --relationshipType Many-to-One ;
jpa-new-field --named publisher --type org.agoncal.training.javaee6adv.model.Publisher --relationshipType Many-to-One ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 30 ;
constraint-add --onProperty description --constraint Size --min 1 --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;
constraint-add --onProperty isbn --constraint NotNull ;
constraint-add --onProperty isbn --constraint Size --max 15 ;
constraint-add --onProperty nbOfPages --constraint Min --value 1 ;
constraint-add --onProperty publicationDate --constraint Past ;



jpa-new-entity --named CD ;
jpa-new-field --named title ;
jpa-new-field --named price --type java.lang.Float ;
jpa-new-field --named description --length 2000 ;
jpa-new-field --named totalDuration --type java.lang.Float --columnName total_duration ;
jpa-new-field --named label --type org.agoncal.training.javaee6adv.model.MajorLabel --relationshipType Many-to-One ;
jpa-new-field --named genre --type org.agoncal.training.javaee6adv.model.Genre --relationshipType Many-to-One ;
jpa-new-field --named musicians --type org.agoncal.training.javaee6adv.model.Musician --relationshipType One-to-Many ;

constraint-add --onProperty title --constraint NotNull ;
constraint-add --onProperty title --constraint Size --min 1 --max 30 ;
constraint-add --onProperty description --constraint Size --min 1 --max 3000 ;
constraint-add --onProperty price --constraint Min --value 1 ;



scaffold-setup ;
scaffold-generate --webRoot admin --targets org.agoncal.training.javaee6adv.* ;



rest-setup ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.Author ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.Book ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.CD ;
rest-generate-endpoints-from-entities --targets org.agoncal.training.javaee6adv.Musician ;
