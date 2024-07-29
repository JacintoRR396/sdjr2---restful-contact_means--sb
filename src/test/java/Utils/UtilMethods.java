package Utils;

import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

public class UtilMethods {

	public static <T> void assertGenericPage ( Integer offset, Integer limit, Page<T> pageRes ) {
		assertNotNull( pageRes );
		assertFalse( pageRes.isEmpty() );
		assertEquals( offset, pageRes.getPageable().getPageNumber() );
		assertEquals( limit, pageRes.getPageable().getPageSize() );
	}
}
