package com.ikl.lab.neo4j;

import java.util.List;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.ikl.lab.beans.Concept;

/**
 * @author dewadkar infant neo4j Jun 21, 2015 3:05:30 PM
 * 
 */
public class Neo4jConceptWriter {

	GraphDatabaseFactory dbFactory;
	GraphDatabaseService db;

	public Neo4jConceptWriter(String dbStorePath) {
		dbFactory = new GraphDatabaseFactory();
		if (dbStorePath.isEmpty()) {
			dbStorePath = "C:\\datas\\neo4j\\new1";
		}

		db = dbFactory.newEmbeddedDatabase(dbStorePath);
	}

	public boolean writeConceptToNeo4j(List<Concept> concepts, String fileName) {

		try (Transaction tx = db.beginTx()) {

			Label parentLable = DynamicLabel.label(fileName);
			Node parentNode = db.createNode(parentLable);
			for (Concept concept : concepts) {
				Label myLabel = DynamicLabel.label(concept.getName());
				Node conceptNode = db.createNode(myLabel);
				conceptNode.setProperty("parent", fileName);
				conceptNode.setProperty("name", concept.getName());
				conceptNode.setProperty("Dbpedia", concept.getDbpedia());
				conceptNode.setProperty("FreeBase", concept.getFreebase());
				conceptNode.setProperty("Opencyc", concept.getOpencyc());
				conceptNode.setProperty("Relevance", concept.getRelevance());
				conceptNode.setProperty("Yago", concept.getYago());

				RelationshipType relationshipType = DynamicRelationshipType
						.withName("Contains");

				Relationship relationship = parentNode.createRelationshipTo(
						conceptNode, relationshipType);
				relationship.setProperty("Relevance", concept.getRelevance());

			}
			tx.success();
			System.out.println("Done successfully");
			return true;
		}
	}
}
