package exApiGdrive.quickstart;

import java.util.HashMap;
import java.util.Map;

public class Cell {
	
	private String id;
	private String name;
	private String source;
	
	public Cell() {
		super();
	}
	
	public Cell(String id, String name, String source) {
		super();
		this.id = id;
		this.name = name;
		this.source = source;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + ", name=" + name + ", source=" + source + "]";
	}

	public Map<String, String> getAttributClass(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", this.id);
		map.put("name", this.name);
		map.put("source", this.source);
		return map;
	}
 
	
}
