package com.projectPS.Repository;

import com.projectPS.Model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients,Long> {
    Ingredients findByName(String name);

}
