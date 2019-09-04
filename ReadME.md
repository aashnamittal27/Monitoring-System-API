# Monitoring-System-API

| HTTP Mehtod | API URL        | Description                                                                | Success Response                                 | Failure Response  |
|-------------|----------------|----------------------------------------------------------------------------|--------------------------------------------------|-------------------|
| POST        | "api/icu"      | This adds a new ICU to the system, adds a new ICU to nursing_station Table | 201 status code  URI response = "/api/icu/"+ id  | 400 - Bad Request |
| GET         | "api/icu"      | Fetches list of all ICUs in the system(from nursing_station)               | List of all ICU or Empty List                    | --                |
| GET         | "api/icu/{id}" | Fetches an ICU with the stationID = "id"                                   | 200 'OK' NursingStation Object in response Body  | 404 Not Found     |
| DELETE      | "api/icu/{id}  | Deletes specified record                                                   | 204 No Content                                   | 404 Not Found     |



http://localhost:8080/api/icu/6 recursively fetches all icu -> beds - >icu ....

@RequestMapping(value = "/api/icu/{id}/beds", method = RequestMethod.GET) this also doesnt work

see this first 	@RequestMapping(value="/api/publishers", method=RequestMethod.GET)

{"isAvailable": false,
"station":
{		"stationId": 6,
        "name": "general-ward",
        "level": 2,
        "bedCapacity": 15
        
}
}


{"isAvailable": true,
"station":{
"stationId": 5,
        "name": "childrem-ward",
        "level": 2,
        "bedCapacity": 15
}}


{
	"name": "succhi",
	"age": "14",
	"phoneNumber": "912344325678",
	"bed": {
		"bedId":2,
        "isAvailable": true,
        "station": {
            "stationId": 5,
            "name": "childrem-ward",
            "level": 2,
            "bedCapacity": 15
        }
	}
}