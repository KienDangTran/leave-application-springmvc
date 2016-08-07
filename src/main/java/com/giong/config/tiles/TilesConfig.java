package com.giong.config.tiles;

import com.giong.constant.View;
import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

import java.util.HashMap;
import java.util.Map;

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
		TilesConfig.addDefaultLayoutDef(View.ERROR_403.getViewName(), "Access is Denied", View.ERROR_403.getViewUrl());
		TilesConfig.addDefaultLayoutDef(View.ERROR_404.getViewName(), "Page not found", View.ERROR_404.getViewUrl());
		TilesConfig.addDefaultLayoutDef(View.ERROR.getViewName(), "Internal Server Error", View.ERROR.getViewUrl());
		TilesConfig.addDefaultLayoutDef(View.HOME.getViewName(), "Home", View.HOME.getViewUrl());
		TilesConfig.addDefaultLayoutDef(View.EMPLOYEE_SUMMARY.getViewName(), "Employee Summary",
			View.EMPLOYEE_SUMMARY.getViewUrl());
		TilesConfig.addDefaultLayoutDef(View.EMPLOYEE_DETAIL.getViewName(), "Employee Details",
			View.EMPLOYEE_DETAIL.getViewUrl());
	}

	/**
	 * <code>Adds default layout definitions</code>
	 *
	 * @param name    <code>Name of the view</code>
	 * @param title   <code>Page title</code>
	 * @param content <code>Body JSP file path</code>
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
