package exApiGdrive.quickstart.model;

import java.util.HashMap;
import java.util.Map;

import com.mxgraph.model.mxGeometry;

public class ChildGeometry extends mxGeometry{
	
	private String as;
	
	public ChildGeometry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChildGeometry(double x, double y, double width, double height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public ChildGeometry(double x, double y, double width, double height, String as) {
		super(x, y, width, height);
		this.as = as;
	}
	
	public Map<String, String> getAttributClass(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("x", Double.toString(x));
		map.put("y", Double.toString(y));
		map.put("width", Double.toString(width));
		map.put("height", Double.toString(height));
		map.put("as", as);
		return map;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}
	
	
	
}
