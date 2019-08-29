# Monitoring-System-API

### ICU Api requests


| HTTP Mehtod | API URL        | Description                                                                | Success Response                                 | Failure Response  |
|-------------|----------------|----------------------------------------------------------------------------|--------------------------------------------------|-------------------|
| POST        | "api/icu"      | This adds a new ICU to the system, adds a new ICU to nursing_station Table | 201 status code  URI response = "/api/icu/"+ id  | 400 - Bad Request |
| GET         | "api/icu"      | Fetches list of all ICUs in the system(from nursing_station)               | List of all ICU or Empty List                    | --                |
| GET         | "api/icu/{id}" | Fetches an ICU with the stationID = "id"                                   | 200 'OK' NursingStation Object in response Body  | 404 Not Found     |
| DELETE      | "api/icu/{id}  | Deletes specified record                                                   | 204 No Content                                   | 404 Not Found     |
