package edu.uiowa.icts.protogen.springhibernate.velocity;

/*
 * #%L
 * Protogen
 * %%
 * Copyright (C) 2009 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

import edu.uiowa.webapp.Schema;

public class AbstractControllerMVCTestsGeneratorTest {

	@Test
	public void shouldGenerateJavaSourceCodeForAbstractSpringMVCTestFile() {
		String packageName = "edu.uiowa.icts";

		Schema schema = new Schema();
		schema.setSqlLabel( "ictssysadmin" );
		schema.setUid( "ictssysadmin-uid" );
		schema.setLabel( "ictssysadmin" );

		AbstractControllerMVCTestsGenerator generator = new AbstractControllerMVCTestsGenerator( schema, packageName, new Properties() );
		String sourceCode = generator.javaSourceCode();

		assertThat( sourceCode, containsString( "package edu.uiowa.icts.ictssysadmin.controller;" ) );
		assertThat( sourceCode, containsString( "* Generated by Protogen" ) );

		SimpleDateFormat ft = new SimpleDateFormat( "EEE MMM dd" );
		assertThat( sourceCode, containsString( ft.format( new Date() ) ) );

	}

}
