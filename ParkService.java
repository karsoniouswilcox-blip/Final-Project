package NatParks.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import NatParks.controller.model.ContributorData;
import NatParks.controller.model.NatParkData;
import NatParks.dao.AmenityDao;
import NatParks.dao.ContributorDao;
import NatParks.dao.NatParkDao;
import NatParks.entity.Amenity;
import NatParks.entity.Contributor;
import NatParks.entity.NatPark;

@Service
public class ParkService {
	
	@Autowired
	private ContributorDao contributorDao;
	
	@Autowired
	private AmenityDao amenityDao;
	
	@Autowired
	private NatParkDao natParkDao;
	
    @Transactional(readOnly = false) 
	public ContributorData saveContributor(ContributorData contributorData) {
		Long contributorId = contributorData.getContributorId();
		Contributor contributor = findOrCreateContributor(contributorId, contributorData.getContributorEmail());
		
		setFieldsInContributor(contributor, contributorData);
		return new ContributorData(contributorDao.save(contributor));
    }
	private void setFieldsInContributor(Contributor contributor, ContributorData contributorData) {
		contributor.setContributorEmail(contributorData.getContributorEmail());
		contributor.setContributorName(contributorData.getContributorName());
		
	}
	private Contributor findOrCreateContributor(Long contributorId, String contributorEmail) {
		Contributor contributor;
		
		
		if(Objects.isNull(contributorId)) {
			Optional<Contributor> opContrib = contributorDao.findByContributorEmail(contributorEmail);
			
			
			if(opContrib.isPresent()) {
			  throw new DuplicateKeyException("Contributor with email " + contributorEmail + " already exists.");
			}
			
		
			contributor = new Contributor();
		}
		else {
			contributor = findContributorById(contributorId);
		}
		return contributor;
	}
    private Contributor findContributorById(Long contributorId) {
    	return contributorDao.findById(contributorId).orElseThrow(() -> new NoSuchElementException("Contributor with ID=" + contributorId + " was not found."));
    	
    }
    
    @Transactional(readOnly = true)
	public List<ContributorData> retrieveAllContributors() {
		List<Contributor> contributors = contributorDao.findAll();
		List<ContributorData> response = new LinkedList<>();
		
		for(Contributor contributor : contributors) {
			response.add(new ContributorData(contributor));
		}
		    
		return response;
		
		
	   
	}
    @Transactional(readOnly = true)
	public ContributorData retrieveContributorById(Long contributorId) {
	Contributor	contributor = findContributorById(contributorId);
	return new ContributorData(contributor); 
	  }
	
	@Transactional(readOnly = false)
	public void deleteContributorById(Long contributorId) {
		Contributor contributor = findContributorById(contributorId);
		contributorDao.delete(contributor);
	}
	public NatParkData saveNatPark(Long contributorId, NatParkData natParkData) {
		Contributor contributor = findContributorById(contributorId);
		
		Set<Amenity> amenities = amenityDao.findAllByAmenityIn(natParkData.getAmenities());
		
		NatPark natPark = findOrCreateNatPark(natParkData.getNatParkId());
		setNatParkFields(natPark, natParkData);
		
		natPark.setContributor(contributor);
		contributor.getNatParks().add(natPark);
		
		for(Amenity amenity : amenities) {
			amenity.getNatParks().add(natPark);
			natPark.getAmenities().add(amenity);
			
		}
		NatPark dbNatPark = natParkDao.save(natPark);
		return new NatParkData(dbNatPark);
		
	}
	private void setNatParkFields(NatPark natPark, NatParkData natParkData) {
		natPark.setState(natParkData.getState());
		natPark.setDirections(natParkData.getDirections());
		natPark.setGeoLocation(natParkData.getGeoLocation());
		natPark.setParkName(natParkData.getParkName());
		natPark.setNatParkId(natParkData.getNatParkId());
		
	}
	private NatPark findOrCreateNatPark(Long natParkId) {
		NatPark natPark;
		
		if(Objects.isNull(natParkId) ) {
			natPark = new NatPark();
			
		}
		else {
			natPark = findNatParkById(natParkId);
		}
		
		return natPark;
		
		
	}
	private NatPark findNatParkById(Long natParkId) {
		return natParkDao.findById(natParkId).orElseThrow(() -> new NoSuchElementException("National park with ID=" + natParkId + " does not exist."));
	}
	
	@Transactional(readOnly = true)
	public NatParkData retrieveNatParkById(Long contributorId, Long parkId) {
		findContributorById(contributorId);
		NatPark natPark = findNatParkById(parkId);
		
		if(natPark.getContributor().getContributorId() != contributorId) {
			throw new IllegalStateException("National park with ID= {}" + parkId + " is not owned by contributor with ID= {}" + contributorId);
		}
		return new NatParkData(natPark);
	}
	
	}
		
	
	
	
	
	
	
	
	
	
	
	