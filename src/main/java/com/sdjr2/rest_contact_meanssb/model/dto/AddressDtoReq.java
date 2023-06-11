package com.sdjr2.rest_contact_meanssb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjr2.rest_contact_meanssb.model.entity.AddressEntity;
import com.sdjr2.rest_contact_meanssb.utils.UConstants;
import com.sdjr2.rest_contact_meanssb.utils.UJsonAdapter;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Comparator;

/**
 * {@link AddressDtoReq} class.
 * <p>
 * DTO REQ - Represents an Address in the request.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @since 23/06/11
 * @upgrade 23/06/11
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDtoReq implements Comparable<AddressDtoReq> {

    /* VARIABLES */
    @Positive
    @Pattern(regexp = UConstants.INDEX_REGEX)
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
    private Double latitude;

    @Pattern(regexp = UConstants.LATITUDE_LONGITUDE_REGEX)
    private Double longitude;

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
        if (!(obj instanceof AddressEntity other)) {
            return false;
        }
        return this.id.equals(other.getId()) && this.street.equals(other.getStreet())
                && this.number.intValue() == other.getNumber().intValue()
                && this.postalCode.equals(other.getPostalCode());
    }

    @Override
    public String toString() {
        final StringBuilder res = new StringBuilder(
                "The Dto Request '" + this.getClass().getSimpleName() + "' contains the attributes: \n");
        res.append(" - Id » ").append(this.id).append(".\n");
        res.append(" - Street » ").append(this.street).append(".\n");
        res.append(" - Number » ").append(this.number).append(".\n");
        res.append(" - Letter » ").append(this.letter).append(".\n");
        res.append(" - Town » ").append(this.town).append(".\n");
        res.append(" - City » ").append(this.city).append(".\n");
        res.append(" - Country » ").append(this.country).append(".\n");
        res.append(" - Postal Code » ").append(this.postalCode).append(".\n");
        res.append(" - Latitude » ").append(this.latitude).append(".\n");
        res.append(" - Longitude » ").append(this.longitude).append(".\n");
        res.append(" - Additional Info » ").append(this.additionalInfo).append(".\n");
        return res.toString();
    }

    @Override
    public int compareTo(final AddressDtoReq obj) {
        // By PostalCode, Street and Number
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
    public static AddressDtoReq toJsonObj(final String jsonData) {
        return UJsonAdapter.readValue(jsonData, AddressDtoReq.class);
    }

    public static String toJsonStr(final AddressDtoReq objWebResp) {
        return UJsonAdapter.writeValueAsString(objWebResp);
    }

    public static AddressDtoReq valueOf(final AddressDtoReq obj) {
        return new AddressDtoReq(obj.getId(), obj.getStreet(), obj.getNumber(), obj.getLetter(), obj.getTown(), obj.getCity(),
                obj.getCountry(), obj.getPostalCode(), obj.getLatitude(), obj.getLongitude(), obj.getAdditionalInfo());
    }

    public static final Comparator<AddressDtoReq> comparatorTown = Comparator.comparing((AddressDtoReq obj) -> obj.getTown().toUpperCase());
    public static final Comparator<AddressDtoReq> comparatorCity = Comparator.comparing((AddressDtoReq obj) -> obj.getCity().toUpperCase());
    public static final Comparator<AddressDtoReq> comparatorPostalCode = Comparator.comparing(AddressDtoReq::getPostalCode);

}
