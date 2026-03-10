package NatParks.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import NatParks.entity.Amenity;

public interface AmenityDao extends JpaRepository<Amenity, Long> {

	Set<Amenity> findAllByAmenityIn(Set<String> amenities);
	

}
