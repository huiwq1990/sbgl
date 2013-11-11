package com.sbgl.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.OracleDialect;

public class MyOracleDialect extends OracleDialect{
	public MyOracleDialect(){
		super();
		registerHibernateType(java.sql.Types.NUMERIC, Hibernate.BIG_DECIMAL.getName());
		registerHibernateType(java.sql.Types.DECIMAL, Hibernate.LONG.getName());
		registerHibernateType(java.sql.Types.INTEGER, Hibernate.LONG.getName());
	}
}
