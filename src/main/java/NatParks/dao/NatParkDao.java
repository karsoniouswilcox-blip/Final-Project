package NatParks.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import NatParks.entity.NatPark;

public interface NatParkDao extends JpaRepository<NatPark, Long> {

}
