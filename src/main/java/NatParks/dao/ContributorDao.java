package NatParks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import NatParks.entity.Contributor;


public interface ContributorDao extends JpaRepository<Contributor, Long> {

	Optional<Contributor> findByContributorEmail(String contributorEmail);

}
