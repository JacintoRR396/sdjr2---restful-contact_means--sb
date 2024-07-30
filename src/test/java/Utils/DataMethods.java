package Utils;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.FilterDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.OrderDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressFilterFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressSortFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.OperatorFilterEnum;
import com.sdjr2.rest_contact_meanssb.repositories.filters.AddressSpecifications;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class DataMethods {

	public static AuditableEntity getAuditableEntity () {
		return new AuditableEntity( LocalDateTime.now(), "test", LocalDateTime.now(), "test" );
	}

	public static AddressEntity getAddressEntity () {
		return new AddressEntity( 2L, "Corredera", "155", "A", "El Viso del Alcor", "Sevilla",
				"España", "41520", "-5.7199300", "37.391060", "Vivo actualmente en el Piso",
				DataMethods.getAuditableEntity() );
	}

	public static Pageable getPageable ( Integer offset, Integer limit, String attrSort ) {
		return ( Objects.isNull( attrSort ) )
				? PageRequest.of( offset, limit )
				: PageRequest.of( offset, limit, Sort.by( attrSort ) );
	}

	public static PageRequest getPageRequest ( Integer offset, Integer limit, String attrSort ) {
		return ( Objects.isNull( attrSort ) )
				? PageRequest.of( offset, limit )
				: PageRequest.of( offset, limit, Sort.by( attrSort ) );
	}

	public static Specification<AddressEntity> getSpecification () {
		return Specification
				.where( AddressSpecifications.hasValuesStr(
						AddressFilterFieldEnum.CITY.name(),
						OperatorFilterEnum.SW,
						List.of( "Sevilla" ) ) );
	}

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

	public static List<OrderDTO> getOrdersDTO () {
		OrderDTO orderDTO = OrderDTO.builder()
				.field( AddressSortFieldEnum.CITY.name() )
				.direction( Sort.Direction.ASC )
				.build();

		return List.of( orderDTO );
	}

	public static List<FilterDTO> getFiltersDTO () {
		FilterDTO filterDTO = FilterDTO.builder()
				.field( AddressFilterFieldEnum.CITY.name() )
				.operatorType( OperatorFilterEnum.EQ )
				.values( List.of( "Sevilla" ) )
				.build();

		return List.of( filterDTO );
	}

	public static SearchBodyDTO getSearchBodyDTO ( Integer offset, Integer limit, List<OrderDTO> sorts,
																								 List<FilterDTO> filters ) {
		return SearchBodyDTO.builder()
				.offset( offset )
				.limit( limit )
				.sorts( sorts )
				.filters( filters )
				.build();
	}
}
