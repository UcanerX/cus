/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.tools.db.ds.pooled;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 连接包装，用于丰富功能
 * @author Looly
 *
 */
public abstract class ConnectionWraper implements Connection{
	
	protected Connection raw;//真正的连接

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return raw.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return raw.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return raw.createStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return raw.prepareStatement(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return raw.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return raw.nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		raw.setAutoCommit(autoCommit);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return raw.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		raw.commit();
	}

	@Override
	public void rollback() throws SQLException {
		raw.rollback();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return raw.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		raw.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return raw.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		raw.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException {
		return raw.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		raw.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return raw.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return raw.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		raw.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return raw.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return raw.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return raw.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return raw.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		raw.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		raw.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return raw.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return raw.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return raw.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		raw.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		raw.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return raw.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return raw.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return raw.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return raw.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return raw.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return raw.prepareStatement(sql, columnNames);
	}

	@Override
	public Clob createClob() throws SQLException {
		return raw.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return raw.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return raw.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return raw.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return raw.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		raw.setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		raw.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return raw.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return raw.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return raw.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return raw.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		raw.setSchema(schema);
	}

	@Override
	public String getSchema() throws SQLException {
		return raw.getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		raw.abort(executor);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		raw.setNetworkTimeout(executor, milliseconds);
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return raw.getNetworkTimeout();
	}

	/**
	 * @return 实际的连接对象
	 */
	public Connection getRaw(){
		return this.raw;
	}
}
