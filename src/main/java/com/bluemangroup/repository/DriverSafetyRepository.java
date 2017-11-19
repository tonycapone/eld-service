package com.bluemangroup.repository;

import com.bluemangroup.model.dao.DriverSafety;

import java.util.Set;

public interface DriverSafetyRepository extends ReadOnlyRepository<DriverSafety, DriverSafety.Id> {

    DriverSafety findFirstById(DriverSafety.Id id);

}
