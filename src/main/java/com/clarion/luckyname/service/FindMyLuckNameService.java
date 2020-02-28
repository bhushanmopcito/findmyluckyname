package com.clarion.luckyname.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.clarion.luckyname.domain.dto.UserRequestDto;
import com.clarion.luckyname.domain.dto.UserResponseDto;

@Service
public class FindMyLuckNameService {

	private static final Logger logger = LoggerFactory.getLogger(FindMyLuckNameService.class);

	/**
	 * Get name suggestion
	 * 
	 * @param request
	 * @return UserResponseDto
	 */
	public UserResponseDto initializeSuggestions(UserRequestDto request) {
		logger.debug("__INIT___ initializeSuggestions {}", request);
		UserResponseDto responseDto = null;
		Integer luckyNumberByDOB = findLuckyNumberByDOB(request.getDateOfBirth());
		Integer nameLuckyNumber = getLuckyNumberByName(request.getName());
		if(luckyNumberByDOB == nameLuckyNumber) {
			responseDto = new UserResponseDto(request.getName(), null, luckyNumberByDOB, nameLuckyNumber);
		} else {
			int differenceLuckyNumber = (luckyNumberByDOB > nameLuckyNumber) ? (luckyNumberByDOB - nameLuckyNumber) : (nameLuckyNumber-luckyNumberByDOB);
			char suggestedLetter = ((char)(differenceLuckyNumber+96));
			String suggestions = insertLetter(suggestedLetter, request.getName());
			responseDto = new UserResponseDto(suggestions, suggestedLetter, luckyNumberByDOB, nameLuckyNumber); 
		}
		logger.debug("__DONE___ initializeSuggestions {}", responseDto); 
		return responseDto;
	}
	
	/**
	 * Insert suggested letter into name
	 *  
	 * @param suggestedLetter char
	 * @param name String
	 * @return String
	 */
	private String insertLetter(char suggestedLetter, String name) {
		logger.debug("__INIT___ insertLetter {}", suggestedLetter, name);
		StringBuilder suggestions = new StringBuilder("");
		for(int index=0; index < name.toCharArray().length; index++) {
			if(index > 0) {
				suggestions = suggestions.append(",");
			}
			suggestions = suggestions.append(insertChar(name, suggestedLetter, index));
		}
		logger.debug("__DONE___ insertLetter response = {}", suggestions);
		return suggestions.toString();
	}
	
	/**
	 * Insert characters 
	 * 
	 * @param word String
	 * @param letter char
	 * @param position int
	 * @return String
	 */
	private static String insertChar(String word, char letter, int position) {
		logger.debug("__INIT___ insertChar {}, {}, {} ", word, letter, position);
        char[] chars = word.toCharArray();
        char[] newchars = new char[word.length() + 1];

        for (int i = 0; i < word.length(); i++) {
            if (i < position)
                newchars[i] = chars[i];
            else
                newchars[i + 1] = chars[i];
        }
        newchars[position] = letter;
        String result = new String(newchars);
        logger.debug("__DONE___ insertChar {}", result);
        return result;
    }
	
	/**
	 * Find lucky number by DOB
	 * 
	 * @param dateOfBirth String
	 * @return Integer
	 */
	private Integer findLuckyNumberByDOB(String dateOfBirth) {
		logger.debug("__INIT___ findLuckyNumberByDOB {}", dateOfBirth);
		int luckyNumber = 0;
		for (int dobIndex = 0; dobIndex < dateOfBirth.length(); dobIndex++) {
			if (dateOfBirth.charAt(dobIndex) != '/') {
				/* Sum of all the digits in the DOB */
				luckyNumber = luckyNumber + Integer.parseInt(String.valueOf(dateOfBirth.charAt(dobIndex)));
			}
		}
		luckyNumber = getOneDigit(luckyNumber);
		logger.debug("__DONE___ findLuckyNumberByDOB respone={}", luckyNumber);
		return luckyNumber;
	}

	/**
	 * Get one digit from calculated DOB and name
	 * 
	 * @param number Integer
	 * @return int
	 */
	private static int getOneDigit(Integer number) {
		logger.debug("__INIT___ getOneDigit {}", number);
		int result = number;
		while (number.toString().length() > 1) {
			result = 0;
			for (int oneDigitIndex = 0; oneDigitIndex < number.toString().length(); oneDigitIndex++) {
				result = result + Integer.parseInt(String.valueOf(number.toString().charAt(oneDigitIndex)));
			}
			number = result;
		}
		logger.debug("__DONE___ getOneDigit response={}", result);
		return result;
	}

	/**
	 * Get lucky number by name 
	 * 
	 * @param name String
	 * @return Integer
	 */
	private Integer getLuckyNumberByName(String name) {
		logger.debug("__INIT___ getLuckyNumberByName {}", name);
		Integer luckyNameNumber = 0;
		for (int nameIndex = 0; nameIndex < name.length(); ++nameIndex) {
		    Character letter = name.charAt(nameIndex);
		    int CountOfLetter = 0;
		    if(Character.isUpperCase(letter)) {
		    	CountOfLetter = (int)letter - (int)'A' + 1;
		    } else {
		    	CountOfLetter = (int)letter - (int)'a' + 1;
		    }
		    luckyNameNumber += CountOfLetter;
		}
		Integer nameOneDigit = getOneDigit(luckyNameNumber);
		logger.debug("__DONE___ getLuckyNumberByName respone={}, {}", luckyNameNumber, ((char)(nameOneDigit.intValue()+96)));
		return nameOneDigit;
	}
}
