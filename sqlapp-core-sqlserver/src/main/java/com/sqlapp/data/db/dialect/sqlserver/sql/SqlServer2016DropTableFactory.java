/**
 * Copyright (C) 2021-2021 Tatsuo Satoh <multisqllib@gmail.com>
 *
 * This file is part of sqlapp-core-hsql.
 *
 * sqlapp-core-hsql is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * sqlapp-core-hsql is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with sqlapp-core-hsql.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sqlapp.data.db.dialect.sqlserver.sql;
import com.sqlapp.data.db.dialect.sqlserver.util.SqlServerSqlBuilder;
import com.sqlapp.data.db.sql.AbstractDropTableFactory;
import com.sqlapp.data.schemas.Table;

/**
 * DROP TABLE
 * 
 */
public class SqlServer2016DropTableFactory extends AbstractDropTableFactory<SqlServerSqlBuilder> {
	@Override
	protected void addDropObject(final Table table, final SqlServerSqlBuilder builder) {
		builder.drop().table().ifExists(this.getOptions().isDropIfExists());
		builder.name(table, this.getOptions().isDecorateSchemaName());
	}
}