package Riwi.Survey_management.infrastructure.abstrac_service;



import org.springframework.data.domain.Page;

import Riwi.Survey_management.utils.enums.SortType;

public interface CrudService<RQ,RS,ID> {
    
    public RS create(RQ request);

    public RS getById(ID id);

    public RS update(RQ request, ID id);

    public void delete(ID id);

    public Page<RS> getAll(int page, int size, SortType sortType);
    
}
