import static org.junit.Assert.*;

import org.junit.Test;


public class InboxTableModelTest {
	InboxTableModel inb = new InboxTableModel();
	@Test
	public void testGetColumnNameInt() {
		assertEquals("Received On:",inb.getColumnName(2));
	}

}
