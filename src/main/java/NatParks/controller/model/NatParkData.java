package NatParks.controller.model;


import java.util.HashSet;
import java.util.Set;

import NatParks.entity.Amenity;
import NatParks.entity.Contributor;
import NatParks.entity.Geolocation;
import NatParks.entity.NatPark;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NatParkData {
	private Long natParkId;
	private String parkName;
	private String directions;
	private String state;
	private Geolocation geolocation;
	private NatParkContributor contributor;
	private Set<String> amenities = new HashSet<>();
	
	public NatParkData(NatPark natPark) {
		natParkId = natPark.getNatParkId();
		parkName = natPark.getParkName();
		directions = natPark.getDirections();
		state = natPark.getState();
		geolocation = natPark.getGeolocation();
		contributor = new NatParkContributor(natPark.getContributor());
		
		for(Amenity amenity : natPark.getAmenities()) {
			amenities.add(amenity.getAmenity());
		}
		
		
		
	}
	
	@Data
	@NoArgsConstructor
	public static class NatParkContributor {
		
		private long contributorId;
		private String contributorName;
		private String contributorEmail;
	
	public NatParkContributor(Contributor contributor) {
		contributorId = contributor.getContributorId();
		contributorName = contributor.getContributorName();
		contributorEmail = contributor.getContributorEmail();
		
	}
	
	}
}
