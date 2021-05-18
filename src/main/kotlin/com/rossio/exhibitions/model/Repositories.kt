package com.rossio.exhibitions.model

import org.springframework.data.jpa.repository.JpaRepository

interface ExhibitionsRepository : JpaRepository<ExhibitionDAO, Long>

interface ExhibitionItemsRepository : JpaRepository<ExhibitionItemDAO, Long>

interface MarkersRepository : JpaRepository<MarkerDAO, Long>

interface SubAboutRepository : JpaRepository<SubAboutDAO, Long>

interface SubTextRepository : JpaRepository<SubTextDAO, Long>

interface UserRepository : JpaRepository<UserDAO, Long>

interface EditorRepository : JpaRepository<EditorDAO, Long>

interface CollaboratorRepository : JpaRepository<CollaboratorDAO, Long>

interface AdminRepository : JpaRepository<AdminDAO, Long>

interface DigitalResourceRepository : JpaRepository<DigitalResourceDAO, Long>