package com.ikl.lab.jdbc.executor;

import java.util.Iterator;
import java.util.Map;

public interface CypherExecutor {
	Iterator<Map<String, Object>> query(String statement,
			Map<String, Object> params);
}
