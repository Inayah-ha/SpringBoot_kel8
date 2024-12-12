package com.domain.models.repos;

import org.springframework.data.repository.CrudRepository;
import com.domain.models.entity.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>{

    java.util.Locale.Category save(java.util.Locale.Category category);


}
