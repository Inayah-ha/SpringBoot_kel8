package com.domain.models.repos;

import org.springframework.data.repository.CrudRepository;
import com.domain.models.entity.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

}
