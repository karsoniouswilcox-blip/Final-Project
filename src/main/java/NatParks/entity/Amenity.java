package NatParks.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Entity
@Data
public class Amenity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long amenityId;
	
	private String amenity;
	
	@ManyToMany(mappedBy = "amenities")
	private Set<NatPark> natParks = new HashSet<>();

}
