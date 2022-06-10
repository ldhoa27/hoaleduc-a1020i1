import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchFlightService {
  private URl = 'http://localhost:8080/';
  constructor(private  http: HttpClient) { }
  searchFlight(pointOfDeparture, destination, flightDate, passengerType1, passengerType2, orderBy): Observable<any> {
    return this.http.get(this.URl + 'api/search-flight?' + 'pointOfDeparture=' + pointOfDeparture + '&destination=' + destination + '&flightDate=' + flightDate + '&passengerType1=' + passengerType1 + '&passengerType2=' + passengerType2 + '&orderBy=' + orderBy);
  }
  getAllLocations(): Observable<any> {
    return this.http.get(this.URl + 'location/list-location');
  }
  findLocationByCityName(cityName): Observable<any> {
    return this.http.get(this.URl + 'location?cityName=' + cityName);
  }
}
