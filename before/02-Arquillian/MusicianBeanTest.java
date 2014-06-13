import org.agoncal.training.javaee6adv.model.Musician;
import org.agoncal.training.javaee6adv.view.MusicianBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class MusicianBeanTest
{

   @Inject
   private MusicianBean musicianbean;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class)
            .addClass(MusicianBean.class)
            .addClass(Musician.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(musicianbean);
   }

	@Test
	public void should_crud() {
		// Creates an object
		Musician musician = new Musician();
		musician.setFirstName("Dummy value");
		musician.setLastName("Dummy value");

		// Inserts the object into the database
		musicianbean.setMusician(musician);
		musicianbean.create();
		musicianbean.update();
		musician = musicianbean.getMusician();
		assertNotNull(musician.getId());

		// Finds the object from the database and checks it's the right one
		musician = musicianbean.findById(musician.getId());
		assertEquals("Dummy value", musician.getFirstName());

		// Deletes the object from the database and checks it's not there anymore
		musicianbean.setId(musician.getId());
		musicianbean.create();
		musicianbean.delete();
		musician = musicianbean.findById(musician.getId());
		assertNull(musician);
	}
}
