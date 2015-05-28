package it.polimi.awt.domain;

public class Request {
	
	private String query;
	private boolean flikrOk = true;
	private boolean panoramioOk = true;
	
	public String getQuery(){
		
		return query;
	}
	
	public void setQuery(String query){
		
		this.query = query;
	}
	
	public boolean isflikrOk() {
		
		return flikrOk;
	}
	public void setflikrOk(boolean flikrOk) {
		
		this.flikrOk = flikrOk;
	}	
	
	public boolean ispanoramioOk() {
		
		return panoramioOk;
	}
	public void setpanoramioOk(boolean panoramioOk) {
		
		this.panoramioOk = panoramioOk;
	}	

}
