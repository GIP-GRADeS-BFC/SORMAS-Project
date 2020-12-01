/*
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2020 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package de.symeda.sormas.api.event;

import java.io.Serializable;
import java.util.Date;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.utils.Order;
import de.symeda.sormas.api.utils.YesNoUnknown;

public class EventExportDto implements Serializable {

	public static final String I18N_PREFIX = "EventExport";

	private String uuid;
	private String externalId;
	private EventStatus eventStatus;
	private EventInvestigationStatus eventInvestigationStatus;
	private long participantsCount;
	private Disease disease;
	private String diseaseDetails;
	private Date startDate;
	private Date endDate;
	private String eventTitle;
	private String eventDesc;
	private YesNoUnknown nosocomial;
	private String region;
	private String district;
	private String community;
	private String city;
	private String street;
	private String houseNumber;
	private String additionalInformation;
	private EventSourceType srcType;
	private InstitutionalPartnerType srcInstitutionalPartnerType;
	private String srcInstitutionalPartnerTypeDetails;
	private String srcFirstName;
	private String srcLastName;
	private String srcTelNo;
	private String srcEmail;
	private String srcMediaWebsite;
	private String srcMediaName;
	private String srcMediaDetails;
	private Date reportDateTime;

	private EventJurisdictionDto jurisdiction;

	public EventExportDto(
		String uuid,
		String externalId,
		EventStatus eventStatus,
		EventInvestigationStatus eventInvestigationStatus,
		long participantsCount,
		Disease disease,
		String diseaseDetails,
		Date startDate,
		Date endDate,
		String eventTitle,
		String eventDesc,
		YesNoUnknown nosocomial,
		String regionUuid,
		String region,
		String districtUuid,
		String district,
		String communityUuid,
		String community,
		String city,
		String street,
		String houseNumber,
		String additionalInformation,
		EventSourceType srcType,
		InstitutionalPartnerType srcInstitutionalPartnerType,
		String srcInstitutionalPartnerTypeDetails,
		String srcFirstName,
		String srcLastName,
		String srcTelNo,
		String srcEmail,
		String srcMediaWebsite,
		String srcMediaName,
		String srcMediaDetails,
		Date reportDateTime,
		String reportingUserUid,
		String surveillanceOfficerUuid) {
		this.uuid = uuid;
		this.externalId = externalId;
		this.eventStatus = eventStatus;
		this.eventInvestigationStatus = eventInvestigationStatus;
		this.participantsCount = participantsCount;
		this.disease = disease;
		this.diseaseDetails = diseaseDetails;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventTitle = eventTitle;
		this.eventDesc = eventDesc;
		this.nosocomial = nosocomial;
		this.region = region;
		this.district = district;
		this.community = community;
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.additionalInformation = additionalInformation;
		this.srcType = srcType;
		this.srcInstitutionalPartnerType = srcInstitutionalPartnerType;
		this.srcInstitutionalPartnerTypeDetails = srcInstitutionalPartnerTypeDetails;
		this.srcFirstName = srcFirstName;
		this.srcLastName = srcLastName;
		this.srcTelNo = srcTelNo;
		this.srcMediaWebsite = srcMediaWebsite;
		this.srcMediaName = srcMediaName;
		this.srcMediaDetails = srcMediaDetails;
		this.reportDateTime = reportDateTime;

		this.jurisdiction = new EventJurisdictionDto(reportingUserUid, surveillanceOfficerUuid, regionUuid, districtUuid, communityUuid);
	}

	@Order(0)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Order(1)
	public String getExternalId() {
		return externalId;
	}

	@Order(2)
	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	@Order(3)
	public EventInvestigationStatus getEventInvestigationStatus() {
		return eventInvestigationStatus;
	}

	public void setEventInvestigationStatus(EventInvestigationStatus eventInvestigationStatus) {
		this.eventInvestigationStatus = eventInvestigationStatus;
	}

	@Order(4)
	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	@Order(5)
	public String getDiseaseDetails() {
		return diseaseDetails;
	}

	public void setDiseaseDetails(String diseaseDetails) {
		this.diseaseDetails = diseaseDetails;
	}

	@Order(6)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Order(7)
	public Date getEndDate() {
		return endDate;
	}

	@Order(8)
	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	@Order(9)
	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	@Order(10)
	public YesNoUnknown getNosocomial() {
		return nosocomial;
	}

	@Order(11)
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Order(12)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Order(13)
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	@Order(14)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Order(15)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Order(16)
	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Order(17)
	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Order(18)
	public EventSourceType getSrcType() {
		return srcType;
	}

	@Order(19)
	public InstitutionalPartnerType getSrcInstitutionalPartnerType() {
		return srcInstitutionalPartnerType;
	}

	@Order(20)
	public String getSrcInstitutionalPartnerTypeDetails() {
		return srcInstitutionalPartnerTypeDetails;
	}

	@Order(21)
	public String getSrcFirstName() {
		return srcFirstName;
	}

	public void setSrcFirstName(String srcFirstName) {
		this.srcFirstName = srcFirstName;
	}

	@Order(22)
	public String getSrcLastName() {
		return srcLastName;
	}

	public void setSrcLastName(String srcLastName) {
		this.srcLastName = srcLastName;
	}

	@Order(23)
	public String getSrcTelNo() {
		return srcTelNo;
	}

	public void setSrcTelNo(String srcTelNo) {
		this.srcTelNo = srcTelNo;
	}

	@Order(24)
	public String getSrcEmail() {
		return srcEmail;
	}

	@Order(25)
	public String getSrcMediaWebsite() {
		return srcMediaWebsite;
	}

	@Order(26)
	public String getSrcMediaName() {
		return srcMediaName;
	}

	@Order(27)
	public String getSrcMediaDetails() {
		return srcMediaDetails;
	}

	@Order(28)
	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}

	@Order(29)
	public long getParticipantsCount() {
		return participantsCount;
	}

	public void setParticipantsCount(long participantsCount) {
		this.participantsCount = participantsCount;
	}

	public EventJurisdictionDto getJurisdiction() {
		return jurisdiction;
	}
}
