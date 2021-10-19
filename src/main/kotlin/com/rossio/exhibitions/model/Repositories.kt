package com.rossio.exhibitions.model

import com.rossio.exhibitions.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExhibitionsRepository : JpaRepository<ExhibitionDAO, Long> {
    fun findAllByStatusIs( status: Status): List<ExhibitionDAO>
}

interface ExhibitionItemsRepository : JpaRepository<ExhibitionItemDAO, Long>

interface MarkersRepository : JpaRepository<MarkerDAO, Long>


interface SubTextRepository : JpaRepository<SubItemDAO, Long>

interface UserRepository : JpaRepository<UserDAO, Long> {
    fun findUserByUsername(username: String): Optional<UserDAO>
}

interface EditorRepository : JpaRepository<EditorDAO, Long>

interface CollaboratorRepository : JpaRepository<CollaboratorDAO, Long>

interface AdminRepository : JpaRepository<AdminDAO, Long>

interface DigitalResourceRepository : JpaRepository<DigitalResourceDAO, Long>