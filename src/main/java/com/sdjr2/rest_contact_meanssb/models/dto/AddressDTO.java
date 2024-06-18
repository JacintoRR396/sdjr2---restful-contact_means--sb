package com.sdjr2.rest_contact_meanssb.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjr2.rest_contact_meanssb.utils.UConstants;
import com.sdjr2.rest_contact_meanssb.utils.UJsonAdapter;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Comparator;

/**
 * {@link AddressDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents an Address in the Request / Response.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/06/18
 * @since 23/06/11
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO implements Comparable<AddressDTO> {

  /* VARIABLES */
  @PositiveOrZero
  private Integer id;

  @NotNull
  @Pattern(regexp = UConstants.STREET_REGEX)
  private String street;

  @NotNull
  @Positive
  @Digits(integer = 5, fraction = 0)
  private Integer number;

  @Pattern(regexp = UConstants.LETTER_REGEX)
  private String letter;

  @Pattern(regexp = UConstants.TOWN_REGEX)
  private String town;

  @Pattern(regexp = UConstants.CITY_REGEX)
  private String city;

  @Pattern(regexp = UConstants.COUNTRY_REGEX)
  private String country;

  @NotNull
  @Positive
  @Digits(integer = 5, fraction = 0)
  @JsonProperty("postal_code")
  private Integer postalCode;

  @Pattern(regexp = UConstants.LATITUDE_LONGITUDE_REGEX)
  private String longitude;

  @Pattern(regexp = UConstants.LATITUDE_LONGITUDE_REGEX)
  private String latitude;

  @Pattern(regexp = UConstants.ADDITIONAL_INFO_REGEX)
  @JsonProperty("additional_info")
  private String additionalInfo;

  /* METHODS OF INSTANCE */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.id.hashCode();
    result = prime * result + this.street.hashCode();
    result = prime * result + this.number.hashCode();
    result = prime * result + this.postalCode.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AddressDTO other)) {
      return false;
    }
    return this.id.equals(other.getId())
      && this.street.equals(other.getStreet())
      && this.number.intValue() == other.getNumber().intValue()
      && this.postalCode.equals(other.getPostalCode());
  }

  @Override
  public String toString() {
    final StringBuilder res = new StringBuilder(
      "The DTO '" + this.getClass().getSimpleName() + "' contains the attributes: \n");
    res.append(" - Id » ").append(this.id).append(".\n");
    res.append(" - Street » ").append(this.street).append(".\n");
    res.append(" - Number » ").append(this.number).append(".\n");
    res.append(" - Letter » ").append(this.letter).append(".\n");
    res.append(" - Town » ").append(this.town).append(".\n");
    res.append(" - City » ").append(this.city).append(".\n");
    res.append(" - Country » ").append(this.country).append(".\n");
    res.append(" - Postal Code » ").append(this.postalCode).append(".\n");
    res.append(" - Longitude » ").append(this.longitude).append(".\n");
    res.append(" - Latitude » ").append(this.latitude).append(".\n");
    res.append(" - Additional Info » ").append(this.additionalInfo).append(".\n");

    return res.toString();
  }

  @Override
  public int compareTo(final AddressDTO obj) {
    // Street, Number & PostalCode
    int res = this.getPostalCode().compareTo(obj.getPostalCode());
    if (res == 0) {
      res = this.getStreet().compareTo(obj.getStreet());
      if (res == 0) {
        res = this.getNumber().compareTo(obj.getNumber());
      }
    }

    return res;
  }

  /* METHODS OF CLASSES */
  public static AddressDTO toJsonObj(final String jsonData) {
    return UJsonAdapter.readValue(jsonData, AddressDTO.class);
  }

  public static String toJsonStr(final AddressDTO objWebResp) {
    return UJsonAdapter.writeValueAsString(objWebResp);
  }

  public static AddressDTO valueOf(final AddressDTO obj) {
    return new AddressDTO(obj.getId(), obj.getStreet(), obj.getNumber(), obj.getLetter(), obj.getTown(), obj.getCity(),
      obj.getCountry(), obj.getPostalCode(), obj.getLongitude(), obj.getLatitude(), obj.getAdditionalInfo());
  }

  public static final Comparator<AddressDTO> comparatorTown =
    Comparator.comparing((AddressDTO obj) -> obj.getTown().toUpperCase());
  public static final Comparator<AddressDTO> comparatorCity =
    Comparator.comparing((AddressDTO obj) -> obj.getCity().toUpperCase());
  public static final Comparator<AddressDTO> comparatorPostalCode = Comparator.comparing(AddressDTO::getPostalCode);
}
