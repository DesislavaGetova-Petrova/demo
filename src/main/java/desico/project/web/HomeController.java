package desico.project.web;


import desico.project.service.CarouselService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CarouselService carouselService;

    public HomeController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }
    private Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("firstImg", carouselService.firstImage());
        model.addAttribute("secondImg", carouselService.secondImage());
        model.addAttribute("thirdImg", carouselService.thirdImage());
        return "info";
    }
    @GetMapping("/offer")
    public String ourOffer() {
        return "offer";
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        LOGGER.info("LOG");
        return ResponseEntity.ok().build();
    }


}