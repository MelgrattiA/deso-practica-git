package repository.dao;

import domain.Reservation;
import exception.DataAccessException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoFileImpl implements ReservationDao {
  private static ReservationDaoFileImpl instance;
  private final File file;

  private ReservationDaoFileImpl() {
    this.file = new File("reservations.csv");
  }

  // visible for testing, so tests can point at a temp file instead of the real one
  ReservationDaoFileImpl(File file) {
    this.file = file;
  }

  // singleton
  public static synchronized ReservationDaoFileImpl getInstance() {
    if (instance == null) {
      instance = new ReservationDaoFileImpl();
    }

    return instance;
  }

  @Override
  public void save(Reservation reservation) throws DataAccessException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
      String line =
          String.format(
              "%s,%s,%s,%s\n",
              reservation.getId(),
              reservation.getService().getId(),
              reservation.getOwner().getId(),
              reservation.getDate());
      bw.write(line);
    } catch (IOException e) {
      throw new DataAccessException("Error al guardar la reserva", e);
    }
  }

  // Ejercicio 3 (Parte A)
  @Override
  public List<Reservation> findByDateRange(Instant from, Instant to) throws DataAccessException {
        List<Reservation> reservas = new ArrayList<Reservation>();

        if(!(file.exists()))
        return reservas;

        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String linea;
            String[] campo;
            Instant fecha;
            Reservation reserva;

            while((linea = br.readLine()) != null )
            {
                campo = linea.split(",");
                if(campo.length >=4)
                {
                    fecha = Instant.parse(campo[3]);

                    if( ( fecha.equals(from) ) || ( fecha.equals(to) ) || ( fecha.isBefore(to) && fecha.isAfter(from) ) )
                    {
                        reserva = new Reservation();
                        reserva.setId(Long.parseLong(campo[0]));
                        reserva.setDate(fecha);
                        reservas.add(reserva);
                    }
                }
              }

          }
          catch (IOException e)
          {
                  throw new DataAccessException("Error al intentar leer las reservar", e);
          }

          return reservas;
      }

}
