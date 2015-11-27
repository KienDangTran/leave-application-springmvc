package com.giong.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

public class TilesConfig implements DefinitionsFactory {
	
	private static final Map<String, Definition> tilesDefinitions = new HashMap<String, Definition>();
	private static final Attribute BASE_TEMPLATE = new Attribute("/WEB-INF/views/layout/template.jsp");
	
	@Override
	public Definition getDefinition(String name, Request tilesContext) {
		return TilesConfig.tilesDefinitions.get(name);
	}
	
	/**
	 * <code>Add Apache tiles definitions</code>
	 */
	public static void addDefinitions() {
		TilesConfig.addDefaultLayoutDef("home", "Home", "/WEB-INF/views/home.jsp");
		TilesConfig.addDefaultLayoutDef("employee", "Employee Management", "/WEB-INF/views/admin/employee/employee.jsp");
	}
	
	/**
	 * <code>Adds default layout definitions</code>
	 * 
	 * @param name
	 *            <code>Name of the view</code>
	 * @param title
	 *            <code>Page title</code>
	 * @param content
	 *            <code>Body JSP file path</code>
	 */
	private static void addDefaultLayoutDef(String name, String title, String content) {
		final Map<String, Attribute> attributes = new HashMap<String, Attribute>();
		
		attributes.put("title", new Attribute(title));
		attributes.put("header", new Attribute("/WEB-INF/views/layout/header.jsp"));
		attributes.put("menu", new Attribute("/WEB-INF/views/layout/menu.jsp"));
		attributes.put("content", new Attribute(content));
		attributes.put("footer", new Attribute("/WEB-INF/views/layout/footer.jsp"));
		
		TilesConfig.tilesDefinitions.put(name, new Definition(name, TilesConfig.BASE_TEMPLATE, attributes));
	}
}
