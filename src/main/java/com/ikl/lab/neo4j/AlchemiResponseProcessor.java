package com.ikl.lab.neo4j;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ikl.lab.beans.Concept;

/**
 * @author dewadkar infant neo4j Jun 21, 2015 3:07:53 PM
 * 
 */
public class AlchemiResponseProcessor {

	// final String response =
	// "{    \"status\": \"OK\",    \"usage\": \"By accessing AlchemyAPI or using information generated by AlchemyAPI, you are agreeing to be bound by the AlchemyAPI Terms of Use: http://www.alchemyapi.com/company/terms.html\",    \"url\": \"\",    \"language\": \"english\",    \"concepts\": [        {            \"text\": \"Cloud computing\",            \"relevance\": \"0.96444\",            \"dbpedia\": \"http://dbpedia.org/resource/Cloud_computing\",            \"freebase\": \"http://rdf.freebase.com/ns/m.02y_9m3\",            \"yago\": \"http://yago-knowledge.org/resource/Cloud_computing\"        },        {            \"text\": \"Virtual machine\",            \"relevance\": \"0.247907\",            \"dbpedia\": \"http://dbpedia.org/resource/Virtual_machine\",            \"freebase\": \"http://rdf.freebase.com/ns/m.07yf2\"        },        {            \"text\": \"Federation\",            \"relevance\": \"0.212532\",            \"dbpedia\": \"http://dbpedia.org/resource/Federation\",            \"freebase\": \"http://rdf.freebase.com/ns/m.014j8_\",            \"opencyc\": \"http://sw.opencyc.org/concept/Mx4rr38VkMsbEdmAAAACs6hRjg\"        },        {            \"text\": \"Azure Services Platform\",            \"relevance\": \"0.202637\",            \"website\": \"http://www.microsoft.com/windowsazure/\",            \"dbpedia\": \"http://dbpedia.org/resource/Azure_Services_Platform\",            \"freebase\": \"http://rdf.freebase.com/ns/m.04y7lrx\"        },        {            \"text\": \"Jodi Phillis\",            \"relevance\": \"0.200621\",            \"dbpedia\": \"http://dbpedia.org/resource/Jodi_Phillis\",            \"freebase\": \"http://rdf.freebase.com/ns/m.045ttl\",            \"yago\": \"http://yago-knowledge.org/resource/Jodi_Phillis\",            \"musicBrainz\": \"http://zitgist.com/music/artist/fbdf4326-163e-4d6d-8cb8-8f37f29f8214\"        },        {            \"text\": \"Authentication\",            \"relevance\": \"0.19922\",            \"dbpedia\": \"http://dbpedia.org/resource/Authentication\",            \"freebase\": \"http://rdf.freebase.com/ns/m.0cw7p\"        },        {            \"text\": \"Red Eye Records\",            \"relevance\": \"0.190983\",            \"dbpedia\": \"http://dbpedia.org/resource/Red_Eye_Records\",            \"freebase\": \"http://rdf.freebase.com/ns/m.0fgz_l\",            \"yago\": \"http://yago-knowledge.org/resource/Red_Eye_Records\"        },        {            \"text\": \"Infrastructure\",            \"relevance\": \"0.176601\",            \"dbpedia\": \"http://dbpedia.org/resource/Infrastructure\",            \"freebase\": \"http://rdf.freebase.com/ns/m.017kvv\",            \"opencyc\": \"http://sw.opencyc.org/concept/Mx4rvoE5zpwpEbGdrcN5Y29ycA\"        }    ]}";

	public List<Concept> getDocConcepts(final String jsonString) {

		List<Concept> concepts = new LinkedList<Concept>();
		try {
			JSONObject jsonResponse = new JSONObject(jsonString);
			JSONArray jsonCocepts = jsonResponse.optJSONArray("concepts");
			for (int i = 0; i < jsonCocepts.length(); i++) {
				JSONObject jsonConcept = jsonCocepts.getJSONObject(i);
				Concept concept = new Concept();
				concept.setName(jsonConcept.optString("text"));
				concept.setDbpedia(jsonConcept.optString("dbpedia"));
				concept.setFreebase(jsonConcept.optString("freebase"));
				concept.setOpencyc(jsonConcept.optString("opencyc"));
				concept.setRelevance(jsonConcept.optString("relevance"));
				concept.setYago(jsonConcept.optString("yago"));

				concepts.add(concept);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return concepts;
	}
}
