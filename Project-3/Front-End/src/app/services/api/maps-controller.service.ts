import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { LatLngLiteral } from '@agm/core';
import { RouteInfo } from '../../models/route-info.model';
import { Location } from '../../models/location.model'


/**
 * Enables Components to work with the Mapping Service on the backend
 */
@Injectable()
export class MapsControllerService {

  /**
   * Sets up the mapping service to interact with the server
   * @param {HttpClient} http - provides the Means of communicating with the Server
   */
 public constructor(private http: HttpClient) { }

  /**
  * Sends address as string to location endpoint in Map Service. Map Service returns geographic location
  * in latitude and longitude as a LatLngLiteral. This can be used to create map markers.
  * @returns {Observable<LatLngLiteral>} - Location info with latitude and longitude information
  */

 /**
  * new address object 
  */

  getDistance(address: string): Observable<Location> { // gives back latitude and longitude
    return this.http.get<Location>(environment.apiUrl + '/location', {
      params: { address },
    });
  }

  

  /**
   * Takes in 2 addresses and calculates the distance and travel time between them
   * @param start string address
   * @param end string address
   * @returns {Observable<RouteInfo>} - the Route info generated by the system
   */
  public getRoute(start: string, end: string): Observable<RouteInfo> { // gives back latitude and longitude
    return this.http.get<RouteInfo>(environment.apiUrl + '/route', {
      params: { start, end },
    });
  }
}
