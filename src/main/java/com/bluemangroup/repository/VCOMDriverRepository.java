package com.bluemangroup.repository;

import com.bluemangroup.model.dao.VCOMDriver;
import org.springframework.stereotype.Repository;

@Repository
public interface VCOMDriverRepository extends ReadOnlyRepository<VCOMDriver, VCOMDriver.Id> {

}
