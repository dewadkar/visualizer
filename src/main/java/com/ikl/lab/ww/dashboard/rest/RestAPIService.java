package com.ikl.lab.ww.dashboard.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.ikl.lab.neo4j.cypher.Neo4jCypherQuery;

/**
 * @author dewadkar Visualizer com.ikl.lab.ww.dashboard.rest Oct 13, 2015
 *         2:52:04 PM
 * 
 */
@Path("/ikl")
public class RestAPIService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/one")
	public Response getResults() {

		// Neo4jCypherQuery nn = new Neo4jCypherQuery("http://localhost:7474/");
		// Map<String, List<JSONObject>> printDocWithConcepts = nn
		// .getDocWithConceptInJSON("Algorithm");
		// System.out.println(" Output : \n " + printDocWithConcepts.size());
		Neo4jCypherQuery nn = new Neo4jCypherQuery("http://localhost:7474/");

		// String querystring = args[0] == null ? "Algorithm" : args[0].trim();
		Map<String, List<JSONObject>> printDocWithConcepts = nn
				.getDocWithConceptInJSON("Algorithm");

		// String output = "Hello from: REST ";
		String output = new Gson().toJson(printDocWithConcepts);
		return Response.status(200).entity(output).build();
	}
}
