package ${packageName};

#set( $classNameLowerCaseFirstLetter = $display.uncapitalize($className) )

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ${basePackageName}.dao.*;
import ${basePackageName}.domain.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Generated by Protogen
 * @since ${date}
 */
public class ${className}ControllerMvcTest extends AbstractControllerMVCTests {
	
    private ${className} first${className};
    
    @Before
    public void before() {
      #if ( ${domainClass.isUsesCompositeKey()} ) 
    	// at the moment, do nothing for composite keys 
      #elseif ( ${domainClass.getPrimaryKey().getType()} && ${domainClass.getPrimaryKey().getType()} != "Integer" )
        // at the moment, do nothing for ids generated without sequences  
      #else	  
        // add 20 records to test database
        for(int x=1; x<21; x++){
        	${className} ${classNameLowerCaseFirstLetter} = new ${className}();
        	${daoServiceName}.get${className}Service().save(${classNameLowerCaseFirstLetter});
	        if (x == 1){
	        	// use this ID for update, show, and delete assertions
	        	first${className} = ${classNameLowerCaseFirstLetter};
	        }
        }   
      #end
    }

    @Test
    public void addShouldDisplayNew${className}Form() throws Exception {
       mockMvc.perform(get("${pathPrefix}/add${pathExtension}"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("${classNameLowerCaseFirstLetter}")) 
       .andExpect(view().name("${jspPath}/edit"));
    }
    
    @Test
    public void listShouldSimplyLoadPage() throws Exception {
       mockMvc.perform(get("${pathPrefix}/list${pathExtension}"))
       .andExpect(status().isOk())
       .andExpect(view().name("${jspPath}/list"));
    }
    
    @Test
    public void indexShouldDisplayListPage() throws Exception {
       mockMvc.perform(get("${pathPrefix}/"))
       .andExpect(status().isOk())
       .andExpect(view().name("${jspPath}/list"));
    }
    
    @Test
    public void listAltShouldLoadListOf${className}s() throws Exception {
       mockMvc.perform(get("${pathPrefix}/list_alt${pathExtension}"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("${classNameLowerCaseFirstLetter}List")) 
       .andExpect(view().name("${jspPath}/list_alt"));
    }
    
    
      #if ( ${domainClass.isUsesCompositeKey()} ) 
    	// at the moment, don't test datatables for composite keys 
      #elseif ( ${domainClass.getPrimaryKey().getType()} && ${domainClass.getPrimaryKey().getType()} != "Integer" )
        // at the moment, don't test datatables for ids generated without sequences  
      #else	  
 /*   	  
    @Test
    public void saveShouldPersistAndRedirectToListView() throws Exception {
       int count = ${daoServiceName}.get${className}Service().list().size();
       
       mockMvc.perform(post("${pathPrefix}/save${pathExtension}")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:${pathPrefix}/list${pathExtension}"));   
       
       assertEquals("${className} count should increase by 1", count +1 , ${daoServiceName}.get${className}Service().list().size());
	}
  */
    @Test
    public void editShouldLoadObjectAndDisplayForm() throws Exception {
    	mockMvc.perform(get("${pathPrefix}/edit${pathExtension}").param("${domainClass.getPrimaryKey().getLowerIdentifier()}", first${className}.get${domainClass.getPrimaryKey().getUpperIdentifier()}().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("${classNameLowerCaseFirstLetter}")) 
         .andExpect(view().name("${jspPath}/edit"));
    }
    
    @Test
    public void showShouldLoadAndDisplayObject() throws Exception {
    	mockMvc.perform(get("${pathPrefix}/show${pathExtension}").param("${domainClass.getPrimaryKey().getLowerIdentifier()}", first${className}.get${domainClass.getPrimaryKey().getUpperIdentifier()}().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("${classNameLowerCaseFirstLetter}")) 
         .andExpect(view().name("${jspPath}/show"));
    }
    
    @Test
    public void deleteGetShouldLoadAndDisplayYesNoButtons() throws Exception {
    	mockMvc.perform(get("${pathPrefix}/delete${pathExtension}").param("${domainClass.getPrimaryKey().getLowerIdentifier()}", first${className}.get${domainClass.getPrimaryKey().getUpperIdentifier()}().toString()))
         .andExpect(status().isOk())
         .andExpect(model().attributeExists("${classNameLowerCaseFirstLetter}")) 
         .andExpect(view().name("${jspPath}/delete"));
    }
    
    @Test
    public void deletePostSubmitYesShouldDeleteAndRedirectToListView() throws Exception {
        int count = ${daoServiceName}.get${className}Service().list().size();

       mockMvc.perform(post("${pathPrefix}/delete${pathExtension}").param("${domainClass.getPrimaryKey().getLowerIdentifier()}", first${className}.get${domainClass.getPrimaryKey().getUpperIdentifier()}().toString())
       .param("submit", "Yes")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:${pathPrefix}/list${pathExtension}"));  
       
       assertEquals("count should decrease by 1", count - 1 , ${daoServiceName}.get${className}Service().list().size());
    }
    
    @Test
    public void deletePostSubmitNoShouldNotDeleteAndRedirectToListView() throws Exception {
        int count = ${daoServiceName}.get${className}Service().list().size();

       mockMvc.perform(post("${pathPrefix}/delete${pathExtension}").param("${domainClass.getPrimaryKey().getLowerIdentifier()}", first${className}.get${domainClass.getPrimaryKey().getUpperIdentifier()}().toString())
       .param("submit", "No")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:${pathPrefix}/list${pathExtension}"));  
       
       assertEquals("count should NOT decrease by 1", count , ${daoServiceName}.get${className}Service().list().size());
    }
      
    @Test
    public void defaultDatatableShouldReturnJSONDataWith10Rows() throws Exception {
    	mockMvc.perform(get("${pathPrefix}/datatable${pathExtension}")
			.param("display", "list")
			.param("search[value]", "")
			.param("search[regex]", "false")
			.param("length", "10")
			.param("start", "0")
			.param("columnCount", "${columnNamesList.size()}")
			.param("draw", "1")
			.param("individualSearch", "true")
			.param("columns[0][data]","0").param("columns[0][name]","urls").param("columns[0][searchable]","false").param("columns[0][orderable]","false").param("columns[0][search][regex]","false").param("columns[0][search][value]","")
			#foreach( $columnName in $columnNamesList )
			   #set( $arrayIndex = $foreach.count)
			.param("columns[${arrayIndex}][data]","${arrayIndex}").param("columns[${arrayIndex}][name]","${columnName}").param("columns[${arrayIndex}][searchable]","true").param("columns[${arrayIndex}][orderable]","true").param("columns[${arrayIndex}][search][regex]","false").param("columns[${arrayIndex}][search][value]","")
			#end    	    			
			.accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is(${daoServiceName}.get${className}Service().list().size())))
    	.andExpect(jsonPath("$.recordsFiltered", is(${daoServiceName}.get${className}Service().list().size())))
    	.andExpect(jsonPath("$.draw", is("1")))
    	// max # of returned data rows should be 10 per "length" value
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
    	.andExpect(jsonPath("$.data[0][0]", containsString("show?")))
		.andExpect(jsonPath("$.data[0][0]", containsString("edit?")))
		.andExpect(jsonPath("$.data[0][0]", containsString("delete?")))
        ;
    }
    	  
    @Test
    public void defaultDatatableShouldReturnErrorTextForBogusColumnName() throws Exception {
    	mockMvc.perform(get("${pathPrefix}/datatable${pathExtension}")
			.param("display", "list")
			.param("search[value]", "")
			.param("search[regex]", "false")
			.param("length", "10")
			.param("start", "1")
			.param("columnCount", "1")
			.param("draw", "1")
			.param("individualSearch", "true")
			.param("columns[0][data]","0").param("columns[0][name]","asdfasdf").param("columns[0][searchable]","true").param("columns[0][orderable]","true").param("columns[0][search][regex]","false").param("columns[0][search][value]","")
			.accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is(${daoServiceName}.get${className}Service().list().size())))
    	.andExpect(jsonPath("$.recordsFiltered", is(${daoServiceName}.get${className}Service().list().size())))
    	.andExpect(jsonPath("$.draw", is("1")))
    	.andExpect(jsonPath("$.data", hasSize(is(10))))
    	.andExpect(jsonPath("$.data[0].error", is("[error: column asdfasdf not supported]")))
    	;
    }    
    	  
    @Test
    public void defaultDatatableShouldReturnException() throws Exception {
    	mockMvc.perform(get("${pathPrefix}/datatable${pathExtension}")
			.param("display", "list")
			.param("search[value]", "")
			.param("search[regex]", "false")
			.param("length", "10")
			.param("start", "1")
			.param("columnCount", "1")
			.param("draw", "1")
			.param("individualSearch", "true")
			.param("order[0][column]","1").param(".order[0][dir]", "asc")
			.accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(content().contentType("application/json"))
    	.andExpect(jsonPath("$.recordsTotal", is(0)))
    	.andExpect(jsonPath("$.recordsFiltered", is(0)))
    	.andExpect(jsonPath("$.draw", is("1")))
    	.andExpect(jsonPath("$.data", IsNull.nullValue()))
    //	.andExpect(jsonPath("$.error", is("")))
    	;
    }      
      #end
    
    
}