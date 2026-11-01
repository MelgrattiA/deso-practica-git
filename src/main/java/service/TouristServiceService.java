package service;

import domain.Flight;
import domain.TouristService;
import exception.FlightNotFoundException;
import exception.ServiceNotFoundException;
import repository.dao.FlightDao;

import java.util.List;

public class TouristServiceService {

  // Ejercicio 2
  public double getAveragePriceByCompany(List<TouristService> services, String company) throws ServiceNotFoundException
  {
    double aux1=0, cont=0;

    for(TouristService servicio : services)
    {
      if(servicio.getCompany().equalsIgnoreCase(company))
      {
        cont++;
        aux1 += servicio.calculatePrice();
      }
    }

    if(cont == 0)
      throw new  ServiceNotFoundException("No existen servicios con esa compania");

    return aux1/cont;
  }

  // Ejercicio 4
  public List<Flight> getFlightsSortedByPriceThenAirline(
      List<String> flightNumbers, FlightDao flightDao) throws FlightNotFoundException {
    throw new UnsupportedOperationException(
        "Ejercicio 4: implement getFlightsSortedByPriceThenAirline");
  }
}
