package tranminhduc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tranminhduc.demo.entity.Category;
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
