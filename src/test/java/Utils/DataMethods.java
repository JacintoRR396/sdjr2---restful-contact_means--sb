package Utils;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;

public class DataMethods {

	public static AddressDTO getAddressDTO () {
		return AddressDTO.builder()
				.id( 2L )
				.street( "Corredera" )
				.number( 155 )
				.letter( "A" )
				.town( "El Viso del Alcor" )
				.city( "Sevilla" )
				.country( "España" )
				.postalCode( 41520 )
				.longitude( "-5.71993" )
				.latitude( "37.39106" )
				.additionalInfo( "Vivo actualmente en el Piso" )
				.build();
	}
}
