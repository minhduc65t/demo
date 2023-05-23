package tranminhduc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tranminhduc.demo.entity.Category;
import tranminhduc.demo.repository.ICategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAllCategories(){ return categoryRepository.findAll();}

    public Category getCategoryById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        }else {
            throw new RuntimeException("Category Not Found");
        }
    }
    public Category saveCategory(Category category){return categoryRepository.save(category);}

    public void deleteCategory(Long id){categoryRepository.deleteById(id);}
}
