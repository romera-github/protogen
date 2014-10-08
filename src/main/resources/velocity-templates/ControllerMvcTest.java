package ${packageName};

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
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Generated by Protogen
 * @since ${date}
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ${className}ControllerMvcTest extends AbstractControllerMVCTests {
	
    private MockMvc mockMvc;
    
    @Autowired
	private ${daoServiceName.substring(0, 1).toUpperCase()}${daoServiceName.substring(1)} ${daoServiceName};
    
    private ${className} first${className};
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

      #if ( ${domainClass.isUsesCompositeKey()} ) 
    	// at the moment, do nothing for composite keys 
      #elseif ( ${domainClass.getPrimaryKey().getType()} && ${domainClass.getPrimaryKey().getType()} != "Integer" )
        // at the moment, do nothing for ids generated without sequences  
      #else	  
        // add 20 records to test database
        for(int x=1; x<21; x++){
        	${className} ${className.substring(0, 1).toLowerCase()}${className.substring(1)} = new ${className}();
        	${daoServiceName}.get${className}Service().save(${className.substring(0, 1).toLowerCase()}${className.substring(1)});
	        if (x == 1){
	        	// use this ID for update, show, and delete assertions
	        	first${className} = ${className.substring(0, 1).toLowerCase()}${className.substring(1)};
	        }
        }   
      #end
    }

    @Test
    public void addShouldDisplayNew${className}Form() throws Exception {
       mockMvc.perform(get("${pathPrefix}/add${pathExtension}"))
       .andExpect(status().isOk())
       .andExpect(model().attributeExists("${className.substring(0, 1).toLowerCase()}${className.substring(1)}")) 
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
       .andExpect(model().attributeExists("${className.substring(0, 1).toLowerCase()}${className.substring(1)}List")) 
       .andExpect(view().name("${jspPath}/list_alt"));
    }
    
      #if ( ${domainClass.isUsesCompositeKey()} ) 
    	// at the moment, don't test datatables for composite keys 
      #elseif ( ${domainClass.getPrimaryKey().getType()} && ${domainClass.getPrimaryKey().getType()} != "Integer" )
        // at the moment, don't test datatables for ids generated without sequences  
      #else	  
    	    @Test
    	    public void defaultDatatableShouldReturnJSONDataWith10Rows() throws Exception {
    	    	mockMvc.perform(get("${pathPrefix}/datatable")
    	    			.param("display", "list")
    	    			.param("search[value]", "")
    	    			.param("search[regex]", "false")
    	    			.param("length", "10")
    	    			.param("start", "0")
    	    			.param("columnCount", "${columnNamesList.size()}")
    	    			.param("draw", "1")
    	    			.param("individualSearch", "true")
    	    			#foreach( $columnName in $columnNamesList )
    	    			   #set( $arrayIndex = $foreach.count - 1 )
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
    	        ;
    	    }
    	  
    	  
      #end
    
    
}