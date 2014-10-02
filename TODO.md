* Get rid of the quantity attribute in Purchase order. Not needed as there is a quantity in OrderLine

Add JBoss repository

<profile>
         <id>jboss-public-repository</id>
         <repositories>
            <repository>
               <id>jboss-public-repository</id>
               <name>JBoss Repository</name>
               <url>https://repository.jboss.org/nexus/content/groups/public/</url>
               <releases>
                  <enabled>true</enabled>
               </releases>
               <snapshots>
                  <enabled>false</enabled>
               </snapshots>
            </repository>
            <repository>
               <id>jboss-snapshots-repository</id>
               <url>https://repository.jboss.org/nexus/content/repositories/snapshots/</url>
               <layout>default</layout>
               <releases>
                  <enabled>false</enabled>
               </releases>
               <snapshots>
                  <enabled>true</enabled>
               </snapshots>
            </repository>
            <repository>
               <id>jboss-redhat-repository</id>
               <url>https://maven.repository.redhat.com/techpreview/all</url>
               <layout>default</layout>
            </repository>
         </repositories>
         <pluginRepositories>
            <pluginRepository>
               <id>jboss-public-repository</id>
               <name>JBoss Public Maven Repository Group</name>
               <url>https://repository.jboss.org/nexus/content/groups/public/</url>
               <layout>default</layout>
               <releases>
                  <enabled>true</enabled>
               </releases>
               <snapshots>
                  <enabled>true</enabled>
               </snapshots>
            </pluginRepository>
         </pluginRepositories>
      </profile>
