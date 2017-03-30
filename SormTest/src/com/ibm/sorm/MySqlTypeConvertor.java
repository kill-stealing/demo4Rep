package com.ibm.sorm;

public class MySqlTypeConvertor implements TypeConvertor {

	@Override
	public String databaseType2JavaType(String columnType) {
		// varchar-->String
		if ("varchar".equalsIgnoreCase(columnType)
				|| "char".equalsIgnoreCase(columnType)
				|| "text".equalsIgnoreCase(columnType)
				|| "longtext".equalsIgnoreCase(columnType)) {
			return "String";
		} else if ("int".equalsIgnoreCase(columnType)
				|| "tinyint".equalsIgnoreCase(columnType)
				|| "smallint".equalsIgnoreCase(columnType)
				|| "integer".equalsIgnoreCase(columnType)
				|| "bigint".equalsIgnoreCase(columnType)) {
			return "Integer";
		} else if ("bigint".equalsIgnoreCase(columnType)) {
			return "Long";
		} else if ("double".equalsIgnoreCase(columnType)
				|| "float".equalsIgnoreCase(columnType)) {
			return "Double";
		} else if ("clob".equalsIgnoreCase(columnType)) {
			return "java.sql.Clob";
		} else if ("blob".equalsIgnoreCase(columnType)) {
			return "java.sql.Blob";
		} else if ("date".equalsIgnoreCase(columnType)||"datetime".equalsIgnoreCase(columnType)) {
			return "java.sql.Date";
		} else if ("time".equalsIgnoreCase(columnType)) {
			return "java.sql.Time";
		} else if ("timestamp".equalsIgnoreCase(columnType)) {
			return "java.sql.Timestamp";
		} else {
			return "String";
		}
	}

	@Override
	public String JavaType2databaseType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
