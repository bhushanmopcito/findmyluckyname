package com.clarion.luckyname.domain.dto;

import java.io.Serializable;

public class UserResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7458096761207002280L;

	private String suggestions;
	private Character suggestedLetter;
	private Integer dobLuckyNumber;
	private Integer nameLuckyNumber;

	public UserResponseDto() {
		super();
	}

	public UserResponseDto(String suggestions, Character suggestedLetter, Integer dobLuckyNumber,
			Integer nameLuckyNumber) {
		super();
		this.suggestions = suggestions;
		this.suggestedLetter = suggestedLetter;
		this.dobLuckyNumber = dobLuckyNumber;
		this.nameLuckyNumber = nameLuckyNumber;
	}

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public Character getSuggestedLetter() {
		return suggestedLetter;
	}

	public void setSuggestedLetter(Character suggestedLetter) {
		this.suggestedLetter = suggestedLetter;
	}

	public Integer getDobLuckyNumber() {
		return dobLuckyNumber;
	}

	public void setDobLuckyNumber(Integer dobLuckyNumber) {
		this.dobLuckyNumber = dobLuckyNumber;
	}

	public Integer getNameLuckyNumber() {
		return nameLuckyNumber;
	}

	public void setNameLuckyNumber(Integer nameLuckyNumber) {
		this.nameLuckyNumber = nameLuckyNumber;
	}

	@Override
	public String toString() {
		return "UserResponseDto [suggestions=" + suggestions + ", suggestedLetter=" + suggestedLetter
				+ ", dobLuckyNumber=" + dobLuckyNumber + ", nameLuckyNumber=" + nameLuckyNumber + "]";
	}

}
