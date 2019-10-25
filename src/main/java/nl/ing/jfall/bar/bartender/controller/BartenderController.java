package nl.ing.jfall.bar.bartender.controller;

import nl.ing.jfall.bar.bartender.model.MenuItem;
import nl.ing.jfall.bar.bartender.repository.MenuRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BartenderController {

    private final MenuRepository menuRepository;

    public BartenderController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping("/menu")
    public List<MenuItem> getMenu() throws InterruptedException {
        if ((int)(Math.random()*100) % 10 == 0) throw new RuntimeException("oeps");
        if ((int)(Math.random()*100) % 5 == 0) Thread.sleep(10);
        return menuRepository.findAll();
    }

}