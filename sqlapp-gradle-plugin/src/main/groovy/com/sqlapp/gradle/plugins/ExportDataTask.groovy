/*
 * Copyright (C) 2007-2017 Tatsuo Satoh <multisqllib@gmail.com>
 *
 * This file is part of sqlapp-gradle-plugin.
 *
 * sqlapp-gradle-plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * sqlapp-gradle-plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with sqlapp-gradle-plugin.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.sqlapp.gradle.plugins

import com.sqlapp.data.converter.Converters;
import com.sqlapp.data.db.command.export.ExportData2FileCommand
import com.sqlapp.data.db.sql.Options
import com.sqlapp.data.schemas.DbObject;
import com.sqlapp.data.schemas.rowiterator.WorkbookFileType
import com.sqlapp.gradle.plugins.pojo.ExportDataPojo
import java.util.function.Consumer;

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

class ExportDataTask extends AbstractDbTask {
	
	@TaskAction
	def exec() {
		ExportData2FileCommand command=new ExportData2FileCommand();
		initialize(command);
		run(command);
	}
	
	protected void initialize(ExportData2FileCommand command){
		ExportDataPojo pojo=project.exportData;
		this.pojo=pojo;
		command.setDataSource(this.createDataSource());
		command.onlyCurrentCatalog=pojo.onlyCurrentCatalog;
		command.onlyCurrentSchema=pojo.onlyCurrentSchema;
		command.includeSchemas=pojo.includeSchemas;
		command.excludeSchemas=pojo.excludeSchemas;
		command.includeTables=pojo.includeTables;
		command.excludeTables=pojo.excludeTables;
		command.directory=getFile(pojo.directory);
		command.useSchemaNameDirectory=pojo.useSchemaNameDirectory;
		if (pojo.csvEncoding!=null){
			command.csvEncoding=pojo.csvEncoding;
		}
		command.sheetName=pojo.sheetName;
		command.tableOptions=pojo.tableOptions;
		command.defaultExport=pojo.defaultExport;
		WorkbookFileType workbookFileType=WorkbookFileType.parse(pojo.outputFileType);
		if (workbookFileType!=null){
			command.outputFileType=workbookFileType;
		}
		command.sheetName=pojo.sheetName;
		command.converters=pojo.converters;
	}
	
	
}
