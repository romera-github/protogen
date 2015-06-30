package protogen.test.resource;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Generated by Protogen 
 * @since 06/29/2015 14:23:33 CDT
 */
@RestController
@RequestMapping( "${pathPrefix}" )
public class DefaultResource extends AbstractDemoResource {

	@RequestMapping( value = "/**" , produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Map<String, Object>> mappingNotFound( HttpServletRequest request ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "error", true );
		map.put( "message", request.getRequestURI() + " could not be found." );
		return new ResponseEntity<Map<String, Object>>( map, HttpStatus.NOT_FOUND );
	}
	
}