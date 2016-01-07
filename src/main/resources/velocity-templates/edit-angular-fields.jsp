<%--
  #%L
  Protogen
  %%
  Copyright (C) 2009 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
  %%
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  #L%
  --%>
<%@ include file="/WEB-INF/include.jsp"  %>
#foreach( $classVariable in $domainClass.listAll() )
 #if ( $domainClass.isUsesCompositeKey() && $classVariable.isPrimary() )
  #foreach( $a in $domainClass.getEntity().getPrimaryKeyAttributes() )
    #if ( $a.isForeign() )
    #else
      #set( $path = "id." + $a.getLowerLabel() )
	  #set( $label = $a.getLowerLabel() )
	  #if ( $deOb )
	    #set( $label = "${esc.d}{ " + ${domainClass.getSchema().getLowerLabel()} + ":deobfuscateColumn ( '"+ ${domainClass.getTableName()} + "', '" + ${a.getSqlLabel()} + "') }" )
	  #end
	     			<div class="form-group" ng-class="{ 'has-error' : resourceForm.${path}.${esc.d}invalid && !resourceForm.${path}.${esc.d}pristine }">
	      				<label for="${path}" class="control-label">$label</label>
	      				<input type="text" id="${path}" ng-model="resource.${path}" name="${path}" required="" class="form-control"/>
	      				<p ng-show="resourceForm.${path}.$invalid && !resourceForm.${path}.$pristine" class="help-block"> is required.</p>
		 			</div>
	#end
  #end
 #else
  #set( $path = $classVariable.getIdentifier() )
  #if ( $classVariable.getAttribType() == "FOREIGNATTRIBUTE" )
    #set( $path = $classVariable.getIdentifier() + "." + $classVariable.getDomainClass().getPrimaryKeys().iterator().next().getLowerIdentifier() )
  #end

  #set( $label = $generatorUtil.splitCapitalizedWords( $classVariable.getUpperIdentifier() ) )
  #if ( $deOb )
    #set( $label = "${esc.d}{ " + ${domainClass.getSchema().getLowerLabel()} + ":deobfuscateColumn ( '"+ ${domainClass.getTableName()} + "', '" + ${classVariable.getAttribute().getSqlLabel()} + "') }" )
  #end

  #if ( $classVariable.isPrimary() ) 
  #elseif ($classVariable.getAttribType() == "CHILD")	
  #else
                    <div class="form-group" ng-class="{ 'has-error' : resourceForm.${path}.${esc.d}invalid && !resourceForm.${path}.${esc.d}pristine }">
	      				<label for="${path}" class="control-label">$label</label>
    #if ( $classVariable.getAttribType() == "FOREIGNATTRIBUTE" )
					<form:select ng-model="resource.${path}" required="required" path="${path}" items="${esc.d}{${classVariable.getDomainClass().getLowerIdentifier()}List}" itemValue="${classVariable.getDomainClass().getPrimaryKeys().iterator().next().getLowerIdentifier()}" itemLabel="${classVariable.getDomainClass().getPrimaryKeys().iterator().next().getLowerIdentifier()}" class="form-control"/>
					<p ng-show="resourceForm.${path}.$invalid && !resourceForm.${path}.$pristine" class="help-block"> is required.</p>
					
    #else     
                    <input type="text" id="${path}" ng-model="resource.${path}" name="${path}" required="" class="form-control#if ( $classVariable.getType().equalsIgnoreCase( "date" ) ) dateinput#end"#if ( $classVariable.getType().equalsIgnoreCase( "date" ) ) data-provide="datepicker" data-date-format="yyyy-mm-dd" data-date-autoclose="true"#end/>
    				<p ng-show="resourceForm.${path}.$invalid && !resourceForm.${path}.$pristine" class="help-block"> is required.</p>
    #end
	     			</div>
  #end         
 #end
#end 