package lt.ku;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping	("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/")
	public String data(
			@RequestParam("fullPriceWithPVM") Double fullPriceWithPVM, 
			@RequestParam("amount") Integer amount, 
			Model model) {
		Integer PVM = 21;
		
		Double priceWithPVM = fullPriceWithPVM/amount;
		Double price = (priceWithPVM*100)/(100+PVM);
		Double amountPVM = priceWithPVM - price;
		Double fullAmountPVM = amountPVM * amount;
		Double fullPrice = price * amount;
		
		model.addAttribute("price", round(price));
		model.addAttribute("fullPrice", round(fullPrice));
		model.addAttribute("priceWithPVM", round(priceWithPVM));
		model.addAttribute("amountPVM", round(amountPVM));
		model.addAttribute("fullAmountPVM", round(fullAmountPVM));
		model.addAttribute("fullPriceWithPVM", round(fullPriceWithPVM));
		return "data";
	}
	
	public Double round(Double number) {
		return Math.round(number * 100)/100.0;
	}
}


