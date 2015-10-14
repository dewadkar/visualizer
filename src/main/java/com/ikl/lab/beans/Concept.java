package com.ikl.lab.beans;

/**
 * @author dewadkar infant beans Jun 21, 2015 3:11:23 PM
 * 
 */
public class Concept {

	private String name;
	private String relevance;
	private String dbpedia;
	private String yago;
	private String freebase;
	private String opencyc;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the relevance
	 */
	public String getRelevance() {
		return relevance;
	}

	/**
	 * @param relevance
	 *            the relevance to set
	 */
	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	/**
	 * @return the dbpedia
	 */
	public String getDbpedia() {
		return dbpedia;
	}

	/**
	 * @param dbpedia
	 *            the dbpedia to set
	 */
	public void setDbpedia(String dbpedia) {
		this.dbpedia = dbpedia;
	}

	/**
	 * @return the yago
	 */
	public String getYago() {
		return yago;
	}

	/**
	 * @param yago
	 *            the yago to set
	 */
	public void setYago(String yago) {
		this.yago = yago;
	}

	/**
	 * @return the freebase
	 */
	public String getFreebase() {
		return freebase;
	}

	/**
	 * @param freebase
	 *            the freebase to set
	 */
	public void setFreebase(String freebase) {
		this.freebase = freebase;
	}

	/**
	 * @return the opencyc
	 */
	public String getOpencyc() {
		return opencyc;
	}

	/**
	 * @param opencyc
	 *            the opencyc to set
	 */
	public void setOpencyc(String opencyc) {
		this.opencyc = opencyc;
	}
}
