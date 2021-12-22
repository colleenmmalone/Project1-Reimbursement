package testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.controller.HelloControllerTest;
import com.dao.LoginDAOTest;
import com.dao.TicketDAOTest;
import com.driver.MainDriverTest;
import com.model.LoginInfoTest;
import com.model.TicketsInfoTest;
import com.util.ConnectTest;
import com.util.HibernateTest;

@RunWith(Suite.class)
@SuiteClasses({
	MainDriverTest.class,
	ConnectTest.class,
	HibernateTest.class,
	LoginDAOTest.class,
	TicketDAOTest.class,
	TicketsInfoTest.class,
	LoginInfoTest.class,
	HelloControllerTest.class
	
})
public class AllTests {
	public AllTests() {
		// TODO Auto-generated constructor stub
	}
}
