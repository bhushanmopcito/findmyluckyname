# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* Build findmyluckyname
* Use POST API "/api/findmyluckyname"
* POST Body 
Request: 
{
	"name":"Ali",
	"dateOfBirth":"01/11/2001"
}

Expected Response:
{
    "suggestions": "bAli,Abli,Albi",
    "suggestedLetter": "b",
    "dobLuckyNumber": 6,
    "nameLuckyNumber": 4
}

OR use postman collection 
FindMyLuckyName.postman_collection.json from the project.

