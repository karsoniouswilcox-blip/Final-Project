package NatParks.entity;

import java.math.BigDecimal;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Embeddable
@Data
@NoArgsConstructor
public class Geolocation {
	private  BigDecimal latitude;
	private  BigDecimal longitude;
	
	public Geolocation(Geolocation geolocation) {
		this.latitude = geolocation.latitude;
		this.longitude = geolocation.longitude;
	}

}
