package repository.dao;

import domain.Reservation;
import exception.DataAccessException;

import java.io.File;
import java.util.List;

public class ReservationExportDaoImpl implements ReservationExportDao {
  private final File file;

  public ReservationExportDaoImpl() {
    this.file = new File("reservations-export.csv");
  }

  // visible for testing, so tests can point at a temp file instead of the real
  // one
  ReservationExportDaoImpl(File file) {
    this.file = file;
  }

  // Ejercicio 10 (Parte A)
  @Override
  public void exportAll(List<Reservation> reservations) throws DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 10: implement exportAll");
  }
}
