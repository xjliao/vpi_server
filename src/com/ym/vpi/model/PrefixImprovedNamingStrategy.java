package com.ym.vpi.model;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

public class PrefixImprovedNamingStrategy extends ImprovedNamingStrategy
		implements NamingStrategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4251597688663277278L;
	private String tablePrefix;
	private String columnPrefix;
	private String maxLength;

	public PrefixImprovedNamingStrategy() {
	}

	@Override
	public String columnName(String columnName) {
		// TODO Auto-generated method stub
		return super.columnName(columnName);
	}

	@Override
	public String tableName(String tableName) {
		// TODO Auto-generated method stub
		return super.tableName(tableName);
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return columnPrefix + super.propertyToColumnName(propertyName);
	}

	@Override
	public String classToTableName(String className) {
		// TODO Auto-generated method stub
		return tablePrefix + super.classToTableName(className);
	}

	@Override
	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {
		// TODO Auto-generated method stub
		return super.foreignKeyColumnName(propertyName, propertyEntityName,
				propertyTableName, referencedColumnName);
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		// TODO Auto-generated method stub
		return super.joinKeyColumnName(joinedColumn, joinedTable);
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getColumnPrefix() {
		return columnPrefix;
	}

	public void setColumnPrefix(String columnPrefix) {
		this.columnPrefix = columnPrefix;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
}
