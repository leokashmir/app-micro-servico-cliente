import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class StartController {

    @RequestMapping(value="/", method= RequestMethod.GET)
	 public RedirectView conusltaAPI() {
	    return new RedirectView("https://app-micro-servico-cliente.herokuapp.com/swagger-ui.html");
	}

}
