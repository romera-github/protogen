package protogen.test.resource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import protogen.test.ryanlorentzen.domain.*;
import edu.uiowa.icts.lightsound.domain.Subject;
import edu.uiowa.icts.spring.GenericDaoListOptions;

/**
 * Generated by Protogen 
 * @since ${date}
 */
@RestController
@RequestMapping( "null/ryanlorentzen/twokeytable" )
public class TwoKeyTableResource extends AbstractRyanlorentzenResource {

    private static final Log log = LogFactory.getLog( TwoKeyTableResource.class );
    
    @RequestMapping( value = { "{twoKeyTableId}" }, method = RequestMethod.GET, produces = "application/json"  )
    public TwoKeyTable get(@PathVariable( "twoKeyTableId" ) Integer twoKeyTableId ) {
         return fooBarDaoService.getTwoKeyTableService().findById( twoKeyTableId );
    }

}