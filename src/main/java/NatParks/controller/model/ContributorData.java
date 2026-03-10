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
public class ContributorData {
	

	 private Long contributorId;	
	 private String contributorName;
	 private String contributorEmail;
	 private Set< NatParkResponse> natParks = new HashSet<>();
	 
	 public ContributorData(Contributor contributor) {
		 contributorId = contributor.getContributorId();
		 contributorName = contributor.getContributorName();
		 contributorEmail = contributor.getContributorEmail();
		 
		 for(NatPark natPark : contributor.getNatParks()) {
			 natParks.add(new NatParkResponse(natPark));
		 }
		 
	 }
	 
	 @Data
	 @NoArgsConstructor
     static class NatParkResponse {
    	 private long natParkId;
    		private String parkName;
    		private String directions;
    		private String state;
    		private Geolocation geolocation;
    		private Set<String> amenities = new HashSet<>();
    		
    		NatParkResponse(NatPark natPark) {
    			natParkId = natPark.getNatParkId();
    			parkName = natPark.getParkName();
    			directions = natPark.getDirections();
    			state = natPark.getState();
    			geolocation = new Geolocation(natPark.getGeolocation());
    			
    			for(Amenity amenity : natPark.getAmenities()) {
    				amenities.add(amenity.getAmenity());
    			}
    			
    		
	  }
	 } 		

}
