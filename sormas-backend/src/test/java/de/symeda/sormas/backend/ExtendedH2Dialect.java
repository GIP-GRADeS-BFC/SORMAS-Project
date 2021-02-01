package de.symeda.sormas.backend;

import java.sql.Types;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.type.StandardBasicTypes;

public class ExtendedH2Dialect extends H2Dialect {
	public final static String UNACCENT = "unaccent";
	public final static String ILIKE = "ilike";

	public ExtendedH2Dialect() {
		super();
		// needed because of hibernate bug: https://hibernate.atlassian.net/browse/HHH-11938
		registerFunction("regexp_replace", new StandardSQLFunction("regexp_replace"));
		registerHibernateType(Types.OTHER, JsonBinaryType.class.getName());
		// The function unaccent is specific to PostgreSQL
		// With H2 let's make sure it wont fail by making the function "unaccent" do nothing
		registerFunction(UNACCENT, new SQLFunctionTemplate(StandardBasicTypes.STRING, "?1"));
		registerFunction(ILIKE, new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "?1 ILIKE ?2"));
	}
}
