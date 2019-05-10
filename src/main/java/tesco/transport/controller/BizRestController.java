/**
 * 
 */
package tesco.transport.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ndevados
 *
 */

@RestController
public class BizRestController {

	@RequestMapping(value = "/api/search", method = RequestMethod.POST)
	public String search() {

		return "check";
	}

}
