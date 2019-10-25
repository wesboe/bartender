package nl.ing.jfall.bar.bartender.repository;


import nl.ing.jfall.bar.bartender.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, String> {
}
