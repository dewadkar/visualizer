package com.ikl.lab.neo4j.cypher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.ikl.lab.jdbc.executor.CypherExecutor;
import com.ikl.lab.jdbc.executor.JdbcCypherExecutor;

public class Neo4jCypherQuery {

	private final CypherExecutor cypher;

	private static final String QUERY_TEMPLATE_PART_1 = "MATCH (m{name:\"";
	private static final String QUERY_TEMPLATE_PART_2 = "\"})-[r:Contains]-(s) WITH collect(s) as nodes Match n-[r:Contains]-(m)WHERE n in nodes RETURN m as Concept";// .name

	// "MATCH (m{name:\""
	// + queryString
	// +
	// "\"})-[r:Contains]-(s) WITH collect(s) as nodes Match n-[r:Contains]-(m)WHERE n in nodes RETURN n,r,m",

	public static void main(String[] args) {
		Neo4jCypherQuery nn = new Neo4jCypherQuery("http://localhost:7474/");

		// String querystring = args[0] == null ? "Algorithm" : args[0].trim();
		Map<String, List<JSONObject>> printDocWithConcepts = nn
				.getDocWithConceptInJSON("Algorithm");

		printOpMapJson(printDocWithConcepts);

	}

	public Neo4jCypherQuery(String uri) {
		cypher = createCypherExecutor(uri);
	}

	private JdbcCypherExecutor createCypherExecutor(String uri) {
		try {
			String auth = new URL(uri).getUserInfo();
			if (auth != null) {
				String[] parts = auth.split(":");
				return new JdbcCypherExecutor(uri, parts[0], parts[1]);
			}
			return new JdbcCypherExecutor(uri, "neo4j", "webahead");
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Invalid Neo4j-ServerURL " + uri);
		}
	}

	public Map<String, List<Double>> getDocuments(String queryString) {

		Map<String, Object> m = new HashMap<String, Object>();
		// List<String> conceptList = new ArrayList<String>();
		Iterator<Map<String, Object>> it = cypher
				.query(QUERY_TEMPLATE_PART_1 + queryString
						+ QUERY_TEMPLATE_PART_2, m);
		final Map<String, List<Double>> docMap = new LinkedHashMap<String, List<Double>>();
		final List<Double> relevances = new LinkedList<Double>();

		while (it.hasNext()) {

			Map<String, Object> m1 = (Map<String, Object>) it.next();
			for (Map.Entry<String, Object> entry : m1.entrySet()) {

				Object obj = entry.getValue();
				String val = "";
				Gson gson = new Gson();

				if (obj instanceof Map) {
					Map value = (Map) entry.getValue();
					val = gson.toJson(value);
					JSONObject json = null;
					try {
						json = new JSONObject(val);
						double vald = json.optDouble("Relevance");
						String parent = json.getString("parent");
						List<Double> list = docMap.containsKey(parent) ? docMap
								.get(parent) : new LinkedList<Double>();
						list.add(vald);
						docMap.put(parent, relevances);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return docMap;

	}

	/**
	 * Map<String,List<JSONObject>>
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, List<JSONObject>> getDocWithConceptInJSON(
			String queryString) {

		Map<String, Object> m = new HashMap<String, Object>();
		// List<String> conceptList = new ArrayList<String>();
		Iterator<Map<String, Object>> it = cypher
				.query(QUERY_TEMPLATE_PART_1 + queryString
						+ QUERY_TEMPLATE_PART_2, m);
		final Map<String, List<JSONObject>> docMap = new LinkedHashMap<String, List<JSONObject>>();
		final List<JSONObject> relevances = new LinkedList<JSONObject>();

		while (it.hasNext()) {

			Map<String, Object> m1 = (Map<String, Object>) it.next();
			for (Map.Entry<String, Object> entry : m1.entrySet()) {

				Object obj = entry.getValue();
				String val = "";
				Gson gson = new Gson();

				if (obj instanceof Map) {
					Map<?, ?> value = (Map<?, ?>) entry.getValue();
					val = gson.toJson(value);
					JSONObject json = null;
					try {
						json = new JSONObject(val);

						// System.out.println(" " + val);
						String parent = json.optString("parent");
						List<JSONObject> list = docMap.containsKey(parent) ? docMap
								.get(parent)
								: new LinkedList<JSONObject>();
						list.add(json);
						docMap.put(parent, relevances);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return docMap;

	}

	private static void printOpMapJson(Map<String, List<JSONObject>> map) {
		Set<String> keys = map.keySet();
		for (String key : keys) {
			List<JSONObject> relevences = map.get(key);
			System.out.println(" For document " + key);
			for (JSONObject rel : relevences) {
				System.out.println(" " + rel);
			}

		}
	}

	private static void printOpMap(Map<String, List<Double>> map) {
		Set<String> keys = map.keySet();
		for (String key : keys) {
			List<Double> relevences = map.get(key);
			System.out.println(" For document " + key);
			for (Double rel : relevences) {
				System.out.println(" " + rel);
			}

		}
	}

}
