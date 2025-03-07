package com.server.vet.repository.pagination;

import com.server.vet.entity.Owner;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerPaginationRepository extends PagingAndSortingRepository<Owner, Long> {

}