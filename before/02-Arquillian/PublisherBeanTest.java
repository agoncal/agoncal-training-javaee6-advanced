import org.agoncal.training.javaee6adv.model.Publisher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PublisherBeanTest {

	@Inject
	private PublisherBean publisherbean;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(PublisherBean.class)
				.addClass(Publisher.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void should_be_deployed() {
		assertNotNull(publisherbean);
	}

	@Test
	public void should_crud() {
		// Creates an object
		Publisher publisher = new Publisher();
		publisher.setName("Dummy publisher");

		// Inserts the object into the database
		publisherbean.setExample(publisher);
		publisherbean.create();
		publisher = publisherbean.getExample();
		assertNotNull(publisher.getId());

		// Finds the object from the database
		publisherbean.findById(publisher.getId());
		publisher = publisherbean.getExample();
		assertEquals("Dummy publisher", publisher.getName());

		publisherbean.delete();
		publisherbean.findById(publisher.getId());
		assertNull(publisherbean.getExample());
	}
}
