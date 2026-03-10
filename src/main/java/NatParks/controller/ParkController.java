package NatParks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import NatParks.controller.model.ContributorData;
import NatParks.controller.model.NatParkData;
import NatParks.service.ParkService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/nat_park")
@Slf4j
public class ParkController {
	@Autowired
	private ParkService parkService;
	
	@PostMapping("/contributor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContributorData insertContributor(@RequestBody ContributorData contributorData) {
		System.out.println("here");
		log.info("Creating contributor {}", contributorData);
		return parkService.saveContributor(contributorData);
		
	 }
	@GetMapping("/NatParks/contributor")
    public List<ContributorData> retrieveAllContributors() {
		log.info("Retrive all contributors called.");
    	return parkService.retrieveAllContributors();
    }
	
	@PutMapping("/contributor/{contributorId}")
	public ContributorData updateContributor(@PathVariable Long contributorId, @RequestBody ContributorData contributorData) {
		contributorData.setContributorId(contributorId);
		log.info("Updating contributor {}", contributorData);
		return parkService.saveContributor(contributorData);
	}
	
	@GetMapping("/contributor")
	public ContributorData retrieveContributorById(@PathVariable Long contributorId) {
		log.info("Retrieving contributor with ID={}", contributorId);
		return parkService.retrieveContributorById(contributorId);
	}
	@DeleteMapping("/contributor")
	public void deleteAllContributors() {
	   log.info("Attempting to delete all contributors");
	   throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
		
	}
	
	@DeleteMapping("/contributor/{contributorId}")
	public Map<String, String> deleteContributorById(@PathVariable long contributorId) {
		log.info("Deleting contributor with ID= {}", contributorId);
		
		parkService.deleteContributorById(contributorId);
		
		return Map("message", "Deletion of contributor with ID=%s was successful", contributorId);
		
	}
	private Map<String, String> Map(String string, String string2, long contributorId) {
		// TODO Auto-generated method stub
		return null;
	}
	@PostMapping("/contributor/{contributorId}/park")
	@ResponseStatus(code = HttpStatus.CREATED)
	public NatParkData insertNatPark(@PathVariable Long contributorId, @RequestBody NatParkData natParkData, Long parkId) {
		natParkData.setNatParkId(parkId);
		log.info("Creating Park {} for contributor ID={}", contributorId, natParkData);
		
		return parkService.saveNatPark(contributorId, natParkData);
	}
	


@PutMapping("/contributor/{contributorId}/park")
public NatParkData updateNatPark(@PathVariable Long contributorId, @PathVariable Long parkId, @RequestBody NatParkData natParkData) {
	
	log.info("Creating Park {} for contributor ID={}", contributorId, natParkData);
	
	return parkService.saveNatPark(contributorId, natParkData);
}
@GetMapping("/contributor/{contributorId}/park/{parkId}")
public NatParkData  retrieveNatParkById(@PathVariable Long contributorId, @PathVariable Long parkId) {
	log.info("Retrieving national park with ID= {} for contributor with ID= {}", parkId, contributorId);
	
	return parkService.retrieveNatParkById(contributorId, parkId);
	
}







}	
	
	
		
	

 
