package com.rossio.exhibitions.model

import org.springframework.data.jpa.repository.JpaRepository

interface ExhibitionsRepository : JpaRepository<ExhibitionDAO, Long>

interface ExhibitionItemsRepository : JpaRepository<ExhibitionItemDAO, Long>

interface UserRepository : JpaRepository<UserDAO, Long>