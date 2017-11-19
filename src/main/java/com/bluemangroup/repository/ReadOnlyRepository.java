package com.bluemangroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
interface ReadOnlyRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {}

